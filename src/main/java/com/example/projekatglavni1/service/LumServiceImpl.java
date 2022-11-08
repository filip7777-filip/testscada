package com.example.projekatglavni1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.domain.HttpResponse;
import com.example.projekatglavni1.domain.LumEntity;
import com.example.projekatglavni1.exception.LumNameExist;
import com.example.projekatglavni1.repository.LumRepository;

@Service
@Transactional
public class LumServiceImpl implements LumService{
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private LumRepository lumRepository;
	
	
	@Autowired
	public LumServiceImpl(LumRepository lumRepository) {
		super();
		this.lumRepository = lumRepository;
	}

	@Override
	public List<LumEntity> findAll() {
		List<LumEntity> list = this.lumRepository.findAll();
		return list;
	}

	
	@Override
	public LumEntity addLum(String name) throws LumNameExist {
		checkLumName(StringUtils.EMPTY, name);
		LumEntity newLum = new LumEntity();
		newLum.setName(name);
		this.lumRepository.save(newLum);
		LOGGER.info("Uspesno sacuvan novi LUM pod nazivom:"+name);
		return newLum;
	}

	private void checkLumName(String empty, String name) throws LumNameExist {
		List<LumEntity> all = this.lumRepository.findAll();
		for(LumEntity lum : all) {
			if (lum.getName().equals(empty)) {
				throw new LumNameExist("Ime postoji!");
			}
		}
		for(LumEntity lum : all) {
			if (lum.getName().equals(name)) {
				throw new LumNameExist("Ime postoji!");
			}
		}
		
	}
	
}
