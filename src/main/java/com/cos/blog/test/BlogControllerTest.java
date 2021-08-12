package com.cos.blog.test;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.handler.AppError;
import com.cos.blog.handler.UserException;

// 스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니구
// 특정 어노테이션이 붙어 있는 클래스 파일들을 new해서(IoC) 스프링 컨테이너에 관리해줍니다.
@RestController
public class BlogControllerTest {
	
	//http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
	//	return "<h1>Hello spring boot</h1>";
		throw new UserException();
	}

	@ExceptionHandler(UserException.class)
	public @ResponseBody AppError userError(UserException e) {
		AppError appError = new AppError();
		appError.setMessage(" User 익셉션!!");
		appError.setReason(" I dont know!");
		return appError;		
	}
	
	
}
