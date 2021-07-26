package com.cos.blog.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.TempUser;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser,Long> {
    public Optional<TempUser> findByEmail(String email);
}
