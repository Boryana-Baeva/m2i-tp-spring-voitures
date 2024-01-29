package com.example.demo;

import com.example.demo.model.Voiture;
import com.example.demo.service.VoitureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private VoitureService voitureService;

	@Test
	void testSave() {
		Voiture v = new Voiture("Test Marque", "Test Modele", 2021, "gris", LocalDate.of(2021,12,1), "FC74859612");
		voitureService.save(v);
	}

}
