package com.example.projekatglavni1.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.projekatglavni1.converter.CentarConverter;
import com.example.projekatglavni1.domain.CenterEntity;
import com.example.projekatglavni1.dto.CentarDTO;
import com.example.projekatglavni1.repository.CenterRepository;

@Service
@Transactional
public class CenterServiceImpl implements CenterService{
	 
	private CenterRepository centerRepository;
	private CentarConverter centerConverter;
	
	@Autowired
	public CenterServiceImpl(CenterRepository centerRepository,CentarConverter centerConverter) {
		super();
		this.centerRepository = centerRepository;
		this.centerConverter=centerConverter;
	}

	@Override
	public CenterEntity findCenterByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CenterEntity> findAll() {
		return centerRepository.findAll();
	}

	@Override
	public List<CentarDTO> findAlll() {
		try {
			List<CentarDTO> dtos = new ArrayList<>();
			List<CenterEntity> listcenter = this.centerRepository.findAll(Sort.by("name"));
			if(!listcenter.isEmpty()) {
				dtos = this.centerConverter.toCentarDTOs(listcenter);
			}
			return dtos;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
