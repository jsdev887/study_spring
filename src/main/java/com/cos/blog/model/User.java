package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity // User 클래스가 mysql 에 테이블이 생성이 된다.
//@DynamicInsert // 데이터를 insert할 때 null 인것은 제외시켜준다.
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 db의 넘버링 전략을 따라간다. 
	private int id; //시퀀스,auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;// 아이디
	
	@Column(nullable = false, length = 100) // 100이나 주는 이유는 나중에 123456같은 비밀번호를 -> 해쉬(비밀번 호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
	// DB는 RoleType이라는게 없으므로 enumerated를 추가해준다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. //Admin, user, manager 도메인이 정해졌다는 것은 범위가 정해진거다. 
	
	@CreationTimestamp//시간이 자동입력 
	private Timestamp createDate;
	
		
}
