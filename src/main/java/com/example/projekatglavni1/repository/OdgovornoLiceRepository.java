package com.example.projekatglavni1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projekatglavni1.domain.OdgovornoLice;

@Repository
public interface OdgovornoLiceRepository extends JpaRepository<OdgovornoLice, Integer>{

	OdgovornoLice findByEmail(String email);

	

}
