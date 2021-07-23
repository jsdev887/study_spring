package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/test/log")
@Slf4j
public class LoggingController {
	
	@GetMapping("")
	public String main(Model model) {
		log.info("INFO");
		log.debug("DEBUG");
		log.warn("WARN");
		log.error("ERROR");
		return "main";
	}
	
}
