package com.example.projekatglavni1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.domain.OdgovornoLice;
import com.example.projekatglavni1.exception.EmailExistException;
import com.example.projekatglavni1.exception.EmailNotFoundException;
import com.example.projekatglavni1.repository.OdgovornoLiceRepository;

@Service
@Transactional
public class OdgovornoLiceServiceImpl implements OdgovornoLiceService{
	private   Logger LOGGER = LoggerFactory.getLogger(getClass());
	private OdgovornoLiceRepository odgovornoLiceRepository;
	
	@Autowired
	public OdgovornoLiceServiceImpl(OdgovornoLiceRepository odgovornoLiceRepository) {
		super();
		this.odgovornoLiceRepository = odgovornoLiceRepository;
	}
	
	@Override
	public List<OdgovornoLice> findAll() {
		return odgovornoLiceRepository.findAll();
		 
	}

	@Override
	public OdgovornoLice add(String firstName, String lastName, String mobileNumber, String email, String profession) throws EmailExistException, EmailNotFoundException {
		checkEmailAndPhone(StringUtils.EMPTY,email, mobileNumber);
		OdgovornoLice newOdgLice = new OdgovornoLice();
		newOdgLice.setFirstName(firstName);
		newOdgLice.setLastName(lastName);
		newOdgLice.setMobileNumber(mobileNumber);
		newOdgLice.setEmail(email);
		newOdgLice.setProfession(profession);
		odgovornoLiceRepository.save(newOdgLice);
		LOGGER.info("Odgovorno lice sacuvano"+newOdgLice);
		return newOdgLice;
	}

	private OdgovornoLice checkEmailAndPhone(String currentEmail, String newEmail, String mobileNumber) throws EmailExistException, EmailNotFoundException {
		if(StringUtils.isNotBlank(currentEmail)) {
			OdgovornoLice currentPerson = findByEmail(currentEmail);
			if(currentPerson == null) {
				throw new EmailNotFoundException("Ne postoji odgovorno lice sa emailom:"+currentEmail);
			}
			OdgovornoLice newPerson = findByEmail(newEmail);
			if(newPerson != null && currentPerson.getId()!=newPerson.getId()) {
				throw new EmailExistException("Postoji odgovorno lice sa emailom:"+newEmail);
			}
			return currentPerson;
		}else {
			OdgovornoLice newPersonEmail =odgovornoLiceRepository.findByEmail(newEmail);
			if(newPersonEmail != null) {
				throw new EmailExistException("Postoji odgovorno lice sa mailom:"+newEmail);
				
			}
			return null;
		}
		
	}

	@Override
	public OdgovornoLice findByEmail(String email) {
		return odgovornoLiceRepository.findByEmail(email);
	}

	

}
