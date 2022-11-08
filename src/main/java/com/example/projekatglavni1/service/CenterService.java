package com.example.projekatglavni1.service;

import java.util.List;

import com.example.projekatglavni1.domain.CenterEntity;
import com.example.projekatglavni1.dto.CentarDTO;

public interface CenterService {
	
	CenterEntity findCenterByName(String name);
	List<CenterEntity> findAll();
	List<CentarDTO> findAlll();
}
