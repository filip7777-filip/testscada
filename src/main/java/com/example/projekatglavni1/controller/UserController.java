package com.example.projekatglavni1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projekatglavni1.constants.SecurityConstants;
import com.example.projekatglavni1.domain.UserEntity;
import com.example.projekatglavni1.domain.UserPrincipal;
import com.example.projekatglavni1.exception.EmailExistException;
import com.example.projekatglavni1.exception.ExceptionHandling;
import com.example.projekatglavni1.exception.UsernameExistException;
import com.example.projekatglavni1.service.UserService;
import com.example.projekatglavni1.utility.JwtTokenProvider;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionHandling{

	private UserService userService;
	private AuthenticationManager authManager;
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authManager,JwtTokenProvider jwtTokenProvider) {
		super();
		this.userService = userService;
		this.authManager = authManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll(){
		List<UserEntity> list = userService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) throws UsernameExistException, EmailExistException {
		UserEntity newUser = userService.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserEntity> login(@RequestBody UserEntity user){
		authenticate(user.getUsername(), user.getPassword());
		UserEntity loginUser = userService.findUserByUsername(user.getUsername());
		UserPrincipal principal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(principal);
		return new ResponseEntity<UserEntity>(loginUser, jwtHeader,HttpStatus.OK);
	}



	private org.springframework.http.HttpHeaders getJwtHeader(UserPrincipal principal) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstants.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(principal) );
		return headers;
	}

	private void authenticate(String username, String password) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
	
	
	
	
	
	
}
