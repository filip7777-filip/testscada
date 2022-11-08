package com.example.projekatglavni1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projekatglavni1.domain.RtuEntity;
import com.example.projekatglavni1.service.RtuService;

@RestController
@RequestMapping("/rtu")
public class RtuController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RtuController.class);
	private RtuService rtuService;
	
	@Autowired
	public RtuController(RtuService rtuService) {
		this.rtuService=rtuService;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll(){
		try {
			List<RtuEntity> listAll = this.rtuService.findAll();
			LOGGER.info("Vraceni rtuovi sa pocetnim:"+listAll.get(0));
			return new ResponseEntity<>(listAll, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Doslo je do greske..!");
			throw e;
		}
		
	}
	
	@PostMapping("/add")
	public ResponseEntity addRtu(@RequestBody RtuEntity rtu) {
		try {
			RtuEntity newRtu = this.rtuService.addRtu(rtu.getName());
			return new ResponseEntity<>(newRtu, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
