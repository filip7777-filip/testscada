package com.example.projekatglavni1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projekatglavni1.domain.CenterEntity;
import com.example.projekatglavni1.dto.CentarDTO;
import com.example.projekatglavni1.service.CenterService;

@RestController
@RequestMapping("/center")
public class CenterController {

	private final  Logger LOGGER = LoggerFactory.getLogger(getClass());
	private CenterService centerService;
	
	@Autowired
	public CenterController(CenterService centerService) {
		super();
		this.centerService = centerService;
	}
	
//	@GetMapping(path="/all")
//	public ResponseEntity findAll(){
//		List<CenterEntity> centerList = centerService.findAll();
//		return new ResponseEntity<>(centerList, HttpStatus.OK);
//	}
	
	@GetMapping(path ="/all")
	public ResponseEntity findAll(){
		try {
			List<CentarDTO> list = this.centerService.findAlll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw e;
		}
	}
	
	@RequestMapping(value ="/find/{id}", method = RequestMethod.GET )
//	@GetMapping(path="find/{id}")
	public ResponseEntity findById(@PathVariable("id") long centerId) {
		List<CenterEntity> list = centerService.findAll();
		CenterEntity centerById = list.stream()
					.filter(c -> centerId == c.getId())
					.findFirst().orElseThrow(() -> new IllegalStateException("Centar:" +centerId) );
		return new ResponseEntity(centerById, HttpStatus.OK);
	}
}
