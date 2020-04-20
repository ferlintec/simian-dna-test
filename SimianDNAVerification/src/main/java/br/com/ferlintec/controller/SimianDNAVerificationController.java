package br.com.ferlintec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferlintec.data.DnaVerificationVO;
import br.com.ferlintec.services.DNAVerificationServices;

@RestController
@RequestMapping("/simian")
public class SimianDNAVerificationController {

	@Autowired
	private DNAVerificationServices service;

	
	@PostMapping
	public ResponseEntity<?> isSimian(@RequestBody DnaVerificationVO vo) {
		
		if (service.isSimian(vo))
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		else
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
	}
	
	
}
