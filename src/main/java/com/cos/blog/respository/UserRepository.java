package com.cos.blog.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO(Data access object)
// 자동으로 bean 등록이 된다.
// @Repository // 얘를 넣어주면 메모리에 스프링이 띄워주는데 생략 가능하다. 
public interface UserRepository extends JpaRepository<User, Integer> {}