package com.example.projekatglavni1.controller;

import java.util.ArrayList;
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

import com.example.projekatglavni1.domain.LumEntity;
import com.example.projekatglavni1.exception.ExceptionHandling;
import com.example.projekatglavni1.exception.LumNameExist;
import com.example.projekatglavni1.exception.LumNotFoundException;
import com.example.projekatglavni1.service.LumService;

@RestController
@RequestMapping("/lum")
public class LumController extends ExceptionHandling{
	private static Logger LOGGER = LoggerFactory.getLogger(LumController.class);
	private LumService lumService;
	
	@Autowired
	public LumController(LumService lumService) {
		super();
		this.lumService = lumService;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll() throws Exception {
		try {
			List<LumEntity> listLum = new ArrayList<>();
			listLum = this.lumService.findAll();
			if(listLum.isEmpty()) {
				throw new LumNotFoundException("0");
			}
			return new ResponseEntity<>(listLum, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<LumEntity> addLum(@RequestBody LumEntity lum) throws LumNameExist {
		LumEntity newLum = this.lumService.addLum(lum.getName());
		return new ResponseEntity <>(newLum,HttpStatus.OK);
	}
	
}
