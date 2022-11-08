package com.example.projekatglavni1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projekatglavni1.domain.OdgovornoLice;
import com.example.projekatglavni1.exception.EmailExistException;
import com.example.projekatglavni1.exception.EmailNotFoundException;
import com.example.projekatglavni1.service.OdgovornoLiceService;

@RestController
@RequestMapping("/responseperson")
public class OdgovornoLiceController {
	private static Logger LOGGER = LoggerFactory.getLogger(OdgovornoLiceController.class);
	private OdgovornoLiceService odgovornoLiceService;
	
	@Autowired
	public OdgovornoLiceController(OdgovornoLiceService odgovornoLiceService) {
		this.odgovornoLiceService = odgovornoLiceService;
	}
	
	@GetMapping("/all")
	public ResponseEntity findAll() {
		List<OdgovornoLice> listOfRespPersons = odgovornoLiceService.findAll();
		return new ResponseEntity(listOfRespPersons, HttpStatus.OK);
	}
	
	@GetMapping("/findemail/{email}")
	public ResponseEntity findByEmail(@PathVariable("email") String email) throws Exception {
		List<OdgovornoLice> byEmail = odgovornoLiceService.findAll();
		OdgovornoLice findByEmail = byEmail.stream()
				.filter(o -> email.equals(o.getEmail()))
				.findFirst().orElseThrow(() -> new IllegalStateException("Odgovorno lice:"+email));
		if(findByEmail == null) {
			throw new Exception("Ne postoji odgovorno lice sa ovim emailom!");
			
		}else {
		return new ResponseEntity<>(findByEmail, HttpStatus.OK);		
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity addResponsePerson(@RequestBody OdgovornoLice odgLice) throws EmailExistException, EmailNotFoundException {
		OdgovornoLice newOdgLice = odgovornoLiceService.add(odgLice.getFirstName(), 
				odgLice.getLastName(),
				odgLice.getEmail(),
				odgLice.getMobileNumber(), 
				odgLice.getProfession());
		return new ResponseEntity<>(newOdgLice,HttpStatus.OK);
	}
	
	@GetMapping("/findnumber/{number}")
	public ResponseEntity findByNumber(@PathVariable("number") String number) {
		List<OdgovornoLice> all = odgovornoLiceService.findAll();
		OdgovornoLice byNumber = all.stream()
				.filter(o -> number.equals(o.getMobileNumber()))
				.findFirst().orElseThrow(() ->  new IllegalStateException("Odgvorono lice:"+number));
		return new ResponseEntity<>(byNumber,HttpStatus.OK);
	}
}
