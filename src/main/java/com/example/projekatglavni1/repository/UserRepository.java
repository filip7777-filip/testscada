package com.example.projekatglavni1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projekatglavni1.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
	public UserEntity findUserByUsername(String username);
	
	public UserEntity findUserByEmail(String email);
}
