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

import com.example.projekatglavni1.domain.CenterType;
import com.example.projekatglavni1.service.CenterTypeService;

@RestController
@RequestMapping("/centertype")
public class CenterTypeController {
			
	private static final Logger LOGGER = LoggerFactory.getLogger(CenterTypeController.class);
	private  CenterTypeService centerTypeService;
	
	@Autowired
	public CenterTypeController(CenterTypeService centerTypeService) {
		super();
		this.centerTypeService = centerTypeService;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll() {
		List<CenterType> listTypes = centerTypeService.findAll();
		return new ResponseEntity(listTypes, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/add")
	public ResponseEntity addCenterType(@RequestBody CenterType type) throws Exception {
		CenterType typeNew = centerTypeService.add(type.getName());
		return new ResponseEntity(type, HttpStatus.OK);
	}
}
