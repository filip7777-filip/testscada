package com.example.projekatglavni1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.projekatglavni1.domain.CenterType;


public interface CenterTypeService {
	public List<CenterType> findAll();

	public CenterType add(String name) throws Exception;
	CenterType findCenterTypeByName(String name);
}
