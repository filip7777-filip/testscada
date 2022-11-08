package com.example.projekatglavni1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projekatglavni1.domain.CenterType;
@Repository
public interface CenterTypeRepository extends JpaRepository<CenterType, Integer>{
	public CenterType findByName(String name);
}
