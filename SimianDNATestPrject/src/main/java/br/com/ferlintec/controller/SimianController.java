package br.com.ferlintec.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferlintec.data.SimianVO;
import br.com.ferlintec.exception.NotSimianDNAException;
import br.com.ferlintec.simiantest.SimianDNATest;

@RestController
@RequestMapping("/simian")
public class SimianController {


	@PostMapping
	public boolean isSimian(@RequestBody SimianVO dna) {
		
		SimianDNATest test = new SimianDNATest(); 

		if (!test.isSimian(dna.getDna())) {
			throw new NotSimianDNAException("Não é um Simian.");
		}
		return true;
	}
	
	
}
