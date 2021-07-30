package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.TempUser;
import com.cos.blog.model.User;
import com.cos.blog.respository.UserRepository;
import com.cos.blog.service.UserService;

@RestController
public class DummyControllerTest {

	//얘는 기본적으로 null 근데 @Autowired 를 넣어주면DummyControllerTest가 메모리에 띄워질 때 UserRepository도 같이 띄워진다.
	@Autowired //의존성 주입(DI) 
	private UserRepository userRepository;
	
	// save 함수는 id를 전달하지 않으면 insert를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	@Transactional // 해당 어노테이션을 붙이면 save를 하지 않아도 업데이트가 된다. 함수 종료시 자동commit 이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json으로 받으려면 RequestBody가 필요하다.json 데이터를 요청 => java object(MessageConvter의 jackson 라이브러리가 변환해서 받아줌. ) 
		System.out.println("id: "+id);
		System.out.println("password: "+requestUser.getPassword());
		System.out.println("email: "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정 실패");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);
		
// 
//		requestUser.setId(id);
//		requestUser.setUsername("ssar");
//		userRepository.save(requestUser); // save로 할 경우 기존의 id가 있을 때 해당 데이터를 덮어 씌우는데 위의 코드에서 password와 email은 값이 있지만 나머지는(이름의 경우ssar 로 하드코딩) 없으므로 null 값이 들어간다.
		
		// @Transactional 로 인해 더티체킹
		return user;
	}
	
	// 유저 전체를 다 호출하기 때문에 파라미터(id 값 등)을 따로 받을 필요 없음.
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터를 리턴받아 볼 예정 
	@GetMapping("/dummy/user")
//	public Page<User> pagList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<User> users = userRepository.findAll(pageable);
//		return users;
//	}
	public List<User> pagList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		if(pagingUser.isFirst()) {
			// 이런식으로 예외처리도 가능 
		}
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라메터를 전달받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/4를 찾으면 내가 데이터베이스에서 못찾아 오게 되면 user가 null이 될 것 아냐?
		//그럼 return null이 리턴이 되잖아. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해! 
		//User user = userRepository.findById(id).get(); // .get()은 "나는 절대 null 일리가 없어! 
		
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() { // .orElseGet()은 null이면 너가 객체나 만들어서 그 값을 넣어
//		// 여기서 이 인터페이스가 가지고 있는 get메서드를 오버라이드 해줘야함		
//			@Override
//			public User get() {				
//				return new User();
//			}
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() { // .orElseGet()은 null이면 너가 객체나 만들어서 그 값을 넣어
			// 여기서 이 인터페이스가 가지고 있는 get메서드를 오버라이드 해줘야함		
				@Override
				public IllegalArgumentException get() {				
					return new IllegalArgumentException("해당 유저는 없단다.id: "+id);
				}
			});
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에username, password, email 데이터를 가지고 요청)
	@PostMapping("/dummy/join")
	//public String join(String username, String password, String email ) { // key=value (약속된 규칙)
	public String join(User user) { // key=value (약속된 규칙)	
		System.out.println("유저네임: "+user.getUsername() + "비번: " +user.getPassword() + "이메일: "+user.getEmail());
		System.out.println("id: "+user.getId() + "role: " +user.getRole() + "createDate: "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
	
	@Autowired
    UserService userService;

    @PostMapping("/api/user")
    public void saveUser(TempUser u){    
    	TempUser user = TempUser.builder()
                .email(u.getEmail())
                .password(u.getPassword())
                .name(u.getName())
                .build();
        userService.save(user);
    }
	
}
