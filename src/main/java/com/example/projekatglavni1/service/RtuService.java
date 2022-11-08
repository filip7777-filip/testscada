package com.example.projekatglavni1.service;

import java.util.List;

import com.example.projekatglavni1.domain.RtuEntity;

public interface RtuService {
	public List<RtuEntity> findAll();
	public RtuEntity addRtu(String naziv);
}
