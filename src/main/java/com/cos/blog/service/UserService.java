package com.cos.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.error.EmailDuplicateException;
import com.cos.blog.error.ErrorCode;
import com.cos.blog.model.TempUser;
import com.cos.blog.respository.TempUserRepository;

@Service
public class UserService {

	TempUserRepository repository;

    @Autowired
    public UserService(TempUserRepository repository){
        this.repository = repository;
    }

    public void save(TempUser user) {
        Optional<TempUser> alreadyUser = repository.findByEmail(user.getEmail());
       
        if( alreadyUser.isPresent()) {
           throw new EmailDuplicateException("email duplicated",ErrorCode.EMAIL_DUPLICATION);        	
        }
     
        repository.save(user);
    }

//    public Optional<TempUser> findUserByEmail(String email){
//        Optional<TempUser> alreadyUser = repository.findByEmail(email);
//        return alreadyUser;
//    }
}
