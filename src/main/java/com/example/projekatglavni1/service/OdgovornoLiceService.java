package com.example.projekatglavni1.service;

import java.util.List;

import com.example.projekatglavni1.domain.OdgovornoLice;
import com.example.projekatglavni1.exception.EmailExistException;
import com.example.projekatglavni1.exception.EmailNotFoundException;

public interface OdgovornoLiceService {
	List<OdgovornoLice> findAll();
	OdgovornoLice add(String firstName, String lastName, String mobileNumber, String email, String profession) throws EmailExistException, EmailNotFoundException;
	OdgovornoLice findByEmail(String email);
	
}
