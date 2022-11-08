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

import com.example.projekatglavni1.domain.EeoTypeEntity;
import com.example.projekatglavni1.service.EeoTypeService;

@RestController
@RequestMapping("/eeotype")
public class EeoTypeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EeoTypeController.class);
	private EeoTypeService eeoTypeService;
	
	@Autowired
	public EeoTypeController(EeoTypeService eeoTypeService) {
		this.eeoTypeService=eeoTypeService;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll(){
		try {
			List<EeoTypeEntity> list = this.eeoTypeService.findAll();
			if(list.isEmpty()) {
				System.out.println("Ne postoji tip eeo u bazi...!");
			}
			LOGGER.info("Uspesno vraceni svi tipovi eeo...!");
			return new ResponseEntity<>(list,HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("Neocekivana greska...!");
			throw e;
		}
		
	}
	
	@PostMapping("/add")
	public ResponseEntity add (@RequestBody EeoTypeEntity eeoType) throws Exception {
		try {
			EeoTypeEntity newType = this.eeoTypeService.addEeoType(eeoType.getName());
			LOGGER.info("Uspesno...!");
			return new ResponseEntity<>(newType,HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.info("Neocekivana greska...!");
			throw e;
		}
	}
}
