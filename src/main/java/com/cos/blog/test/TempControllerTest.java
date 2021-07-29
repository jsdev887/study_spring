package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 파일을 리턴한다. 
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로: src/main/resources/static
		// 리턴명: /home.html
		// 풀네임: src/main/resources/static/home.html
		return "/home.html";
	} 
	
	@GetMapping("/temp/image")
	public String tempImage() {
		return "/testImage.jpeg";
	}
	
	// jsp 파일은 static에 있음에도 읽지 못한다. 왜냐하면 jsp 파일은 동적이기 때문이다 
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views/
		// suffic: .jsp
		// 풀네임: /WEB-INF/views/test.jsp
		return "test";
	}
	
}
