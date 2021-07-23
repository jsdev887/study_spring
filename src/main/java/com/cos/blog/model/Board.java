package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One , 한명의 유저는 여러개의 게시물을 쓸 수 있다.
	@JoinColumn(name = "userId")
	private User user;// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 하나의 게시물은 여러개의 리플이 달릴 수 있기 때문. mappedBy 연관관계의 주인이 아니다. (난 FK가 아니에요)DB에 컬럼을 만들지 마세요.
	// FetchType.LAZY 는 필요할 때 가져옴 (예, 게시글 상세 조회 시 댓글을 처음에 가져오는게 아니라 펼치기를 눌러야 가져올 경우 당장 필요한게 아니기 때문에 lazy. 하지만 펼치기 기능 없이 상세조회 들어가자마자 댓글을 가져오게 되면
	// Eager 전략으로 가야한다. 
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDateTimestamp;
}
