package com.example.projekatglavni1.service;

import java.util.List;

import com.example.projekatglavni1.domain.UserEntity;
import com.example.projekatglavni1.exception.EmailExistException;
import com.example.projekatglavni1.exception.UsernameExistException;

public interface UserService {
	public UserEntity register(String firstName, String lastName, String username, String email) throws UsernameExistException, EmailExistException;
	List<UserEntity> getUser();
	UserEntity findUserByUsername(String username);
	UserEntity findUserByEmail(String email);
	
	public List<UserEntity> findAll();
}
