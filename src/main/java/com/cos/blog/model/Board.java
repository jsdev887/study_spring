package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용 데이
	private String content; // 섬머노트 라이브러 <html>태그가 섞여서 디자인이됨. 
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne // Many = Board, User = One , 한명의 유저는 여러개의 게시물을 쓸 수 있다.
	@JoinColumn(name = "userId")
	private User user;// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 
	
	@CreationTimestamp
	private Timestamp createDateTimestamp;
}
