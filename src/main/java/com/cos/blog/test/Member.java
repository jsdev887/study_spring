package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Data//Getter/Setter 동시에 해준다. 
//@AllArgsConstructor // 생성자.
//@RequiredArgsConstructor //  AllArgsConstructor 대신 근데 요즘에는 final 붙여서 해줌 . 예) private final int id;
//@NoArgsConstructor // 빈생성자 

@Data
@NoArgsConstructor
public class Member {	
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder 
	public Member(int id, String username, String password, String email) {
 		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
