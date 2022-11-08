package com.example.projekatglavni1.service;

import java.util.List;

import com.example.projekatglavni1.domain.LumEntity;
import com.example.projekatglavni1.exception.LumNameExist;

public interface LumService {
	public List<LumEntity> findAll();
	public LumEntity addLum(String name) throws LumNameExist;
}
