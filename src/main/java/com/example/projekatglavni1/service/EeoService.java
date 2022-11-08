package com.example.projekatglavni1.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.converter.EeoConverter;
import com.example.projekatglavni1.domain.EeoEntity;
import com.example.projekatglavni1.dto.EeoDTO;
import com.example.projekatglavni1.repository.EeoRepository;

@Service
@Transactional
public class EeoService {

	private EeoRepository eeoRepository;
	private EeoConverter eeoConverter;
	
	@Autowired
	public EeoService(EeoRepository eeoRepository,EeoConverter eeoConverter) {
		this.eeoRepository=eeoRepository;
		this.eeoConverter=eeoConverter;
	}
	
	public List<EeoDTO> findAll() {
		try {
			List<EeoDTO> dtos = new ArrayList<>();
			List<EeoEntity> listAll = this.eeoRepository.findAll();
			if(!listAll.isEmpty()) {
				this.eeoConverter.fromEeoToDto(listAll);
			}
			return dtos;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
