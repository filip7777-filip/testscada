package com.example.projekatglavni1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.domain.EeoTypeEntity;
import com.example.projekatglavni1.repository.EeoTypeRepository;

@Service
@Transactional
public class EeoTypeServiceImpl implements EeoTypeService{
	private static final Logger LOGGER = LoggerFactory.getLogger(EeoTypeServiceImpl.class);
	private EeoTypeRepository eeoTypeRepository;
	
	@Autowired
	public EeoTypeServiceImpl(EeoTypeRepository eeoTypeRepository) {
		this.eeoTypeRepository=eeoTypeRepository;
	}

	@Override
	public List<EeoTypeEntity> findAll() {
		List<EeoTypeEntity> listAll = this.eeoTypeRepository.findAll();
		return listAll;
	}

	@Override
	public EeoTypeEntity addEeoType(String name) throws Exception {
		try {
			checkTypeName(StringUtils.EMPTY, name);
			EeoTypeEntity newType = new EeoTypeEntity();
			newType.setName(name);
			this.eeoTypeRepository.save(newType);
			LOGGER.info("Uspesno ime!");
			return newType;
		} catch (Exception e) {
			LOGGER.info("Postoji ime!");
			throw e;
		}
		
	}

	private void checkTypeName(String empty, String name) throws Exception {
		List<EeoTypeEntity> all = this.eeoTypeRepository.findAll();
		for(EeoTypeEntity eeot : all) {
			if(eeot.getName().equals(empty)) {
				throw new Exception();
			}
		}
		for(EeoTypeEntity eeot : all) {
			if(eeot.getName().equals(name)) {
				throw new Exception();
			}
		}
		
	}
	
}
