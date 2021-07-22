package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
//@Controller
// 사용자가 요청 -> 응답(Data)
@RestController // 여기서는 String 그 자체를 리턴함 
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest";
	
	// http://localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//	Member m = new Member(10, "ssar", "dddd", "");
		// 빌더 장점 1. 생성자가 틀어질 경우(순서가 바뀌거나 할 경우) 코드가 틀어질 수 있는데 빌더쓰면 알아서 잘 해줌 .
		Member m = Member.builder().password("builder1234").username("iserrrr").email("ttttt.@n.co,").build();

		System.out.println(TAG+"getter:" + m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter:" + m.getUsername());		
		return "lombok test complete"; 
	}
	
	//터넷 브라우저 요청은 무조건 get요청밖에 할 수 없
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {//id=1&username=ssar&password=1234&email=ssar@nate.com		
		return "get 요청:"+m.getId()+"," + m.getUsername()+","+m.getPassword()+","+m.getEmail();	
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { // MessageConverter(스프링부트가 json을 member에 맵핑
		return "post 요청"+m.getId()+"," + m.getUsername()+","+m.getPassword()+","+m.getEmail();	
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put") 
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+"," + m.getUsername()+","+m.getPassword()+","+m.getEmail();	
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
