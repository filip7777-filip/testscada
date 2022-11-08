package com.example.projekatglavni1.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.projekatglavni1.domain.HttpResponse;

@RestControllerAdvice
public class ExceptionHandling {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String ACCOUNT_LOCKED = "Vas nalog je blokiran. Molimo Vas obratite se adminu.";
	private static final String METHOD_IS_NOT_ALLOWED = "Ovaj zahtev nije dostupan";
	private static final String INTERNAL_SERVER_ERROR_MSG = "Doslo je do greske pri obradi zahteva";
	private static final String INCORRECT_CREDENTIALS = "Korisnicko ime ili lozinka nisu ispravni. Molimo vas pokustajte ponovo";
	private static final String ACCOUNT_DISABLE = "Vas nalog je nedostupan. Obratite se adminu ako je doslo do greske";
	private static final String ERROR_PROCESING_FILE = "Greska pri obradi fajla";
	private static final String NOT_ENOUGH_PERMISSION = "Nemate dozvolu";
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<HttpResponse> accountDisabledException(){
		return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLE);
	}

	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		HttpResponse httpResponse = new HttpResponse(httpStatus.value(), httpStatus, message.toUpperCase(), httpStatus.getReasonPhrase().toUpperCase());
		return new ResponseEntity<>(httpResponse, httpStatus);
	}
}
