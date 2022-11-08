package com.example.projekatglavni1.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.projekatglavni1.domain.OdgovornoLice;

class OdgovornoLiceRepositoryTest {
	@Autowired
	private OdgovornoLiceRepository underTest;
	
	@Test
	void testFindByEmail() {
		//given
		String email = "filip@filip";
		OdgovornoLice odgLice = new OdgovornoLice(1, "Filip", "Djuric", "0", email, "bla");
		underTest.save(odgLice);
		//when
		OdgovornoLice exist = underTest.findByEmail(email);
		//then
		 AssertionsForClassTypes.assertThat(exist).isEqualTo(odgLice);
	}

}
