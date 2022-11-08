package com.example.projekatglavni1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projekatglavni1.dto.EeoDTO;
import com.example.projekatglavni1.service.EeoService;

@RestController
@RequestMapping("/eeo")
public class EeoController {
	private EeoService eeoService;
	
	@Autowired
	public EeoController(EeoService eeoService) {
		this.eeoService=eeoService;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll(){
		try {
			List<EeoDTO> listAll = this.eeoService.findAll();
			return new ResponseEntity<>(listAll, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}
}
