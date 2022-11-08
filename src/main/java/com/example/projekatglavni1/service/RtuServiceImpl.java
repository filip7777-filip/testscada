package com.example.projekatglavni1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.controller.RtuController;
import com.example.projekatglavni1.domain.RtuEntity;
import com.example.projekatglavni1.repository.RtuRepository;

@Service
@Transactional
public class RtuServiceImpl implements RtuService{
	private static final Logger LOGGER = LoggerFactory.getLogger(RtuServiceImpl.class);
	private RtuRepository rtuRepository;
	
	@Autowired
	public RtuServiceImpl(RtuRepository rtuRepository) {
		this.rtuRepository=rtuRepository;
	}

	@Override
	public List<RtuEntity> findAll() {
		List<RtuEntity> listOfAll = this.rtuRepository.findAll();
		return listOfAll;
	}

	@Override
	public RtuEntity addRtu(String naziv) {
		RtuEntity newRtu = new RtuEntity();
		newRtu.setName(naziv);
		this.rtuRepository.save(newRtu);
		return newRtu;
	}
}
