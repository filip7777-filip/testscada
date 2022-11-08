package com.example.projekatglavni1.service;

import java.util.List;

import com.example.projekatglavni1.domain.EeoTypeEntity;

public interface EeoTypeService {
	public List<EeoTypeEntity> findAll();
	public EeoTypeEntity addEeoType(String name) throws Exception;
}
