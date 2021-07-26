package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.TempUser;
import com.cos.blog.model.User;
import com.cos.blog.respository.UserRepository;
import com.cos.blog.service.UserService;

@RestController
public class DummyControllerTest {
	
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
