package com.example.projekatglavni1.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.domain.UserEntity;
import com.example.projekatglavni1.domain.UserPrincipal;
import com.example.projekatglavni1.enumeration.Role;
import com.example.projekatglavni1.exception.EmailExistException;
import com.example.projekatglavni1.exception.UserNotFoundException;
import com.example.projekatglavni1.exception.UsernameExistException;
import com.example.projekatglavni1.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	private UserRepository userRepository;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());	
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findUserByUsername(username);
		if(user == null) {
			LOGGER.error("Ne postoji user");
			throw new UsernameNotFoundException("User not found by username"+username);
		}else {
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			UserPrincipal principal = new UserPrincipal(user);
			LOGGER.info("Vracam korisnika sa korisnickim imenom:"+username);
			return principal;
		}
	}

	@Override
	public UserEntity register(String firstName, String lastName, String username, String email) throws UsernameExistException, EmailExistException {
		validateUsernameAndEmail(StringUtils.EMPTY, username, email);
		UserEntity newUser = new UserEntity();
		newUser.setUserId(generateId());
		String password = generatePassword();
		String encodedPassword = encodePassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setUsername(username);
		newUser.setEmail(email);
		newUser.setPassword(encodedPassword);
		newUser.setActive(true);
		newUser.setNotLocked(true);
		newUser.setRole(Role.ROLE_USER.name());
		newUser.setAuthorities(Role.ROLE_USER.getAuthorities());
		userRepository.save(newUser);
		LOGGER.info("Nova lozinka korisnika je:"+password);
		return newUser;
		
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateId() {
		return RandomStringUtils.randomNumeric(10);
	}

	private UserEntity validateUsernameAndEmail(String currentUsername, String newUsername, String email) throws UsernameExistException, EmailExistException {
		if(StringUtils.isNotBlank(currentUsername)) {
			UserEntity current = findUserByUsername(currentUsername);
			if(current == null) {
				throw new UsernameNotFoundException("Nije pronadjeno korisnicko ime:"+currentUsername);
			}
			UserEntity newUser = findUserByUsername(newUsername);
			if(newUser != null && !current.getUserId().equals(newUser.getUserId())) {
				throw new UsernameExistException("Vec postoji korisnik sa korisnickim imenom:"+newUsername);
			}
			UserEntity userByNewEmail = findUserByEmail(email);
			if(userByNewEmail != null && !current.getId().equals(userByNewEmail.getId())) {
				throw new EmailExistException("Vec je kreiran nalog sa ovim emailom!");
			}
			return current;
		}else {
			UserEntity userByUsername = findUserByUsername(newUsername);
			if(userByUsername != null) {
				throw new UsernameExistException("Korisnicko ime ve postoji");
			}
			UserEntity userByEmail = findUserByEmail(email);
			if(userByEmail != null) {
				throw new EmailExistException("Vec je kreiran nalog sa ovim emailom!");
			}
			return null;
		}
		
		
	}

	@Override
	public List<UserEntity> getUser() {
		List<UserEntity> users = userRepository.findAll();
		return users;
	}

	@Override
	public UserEntity findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public UserEntity findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

}
