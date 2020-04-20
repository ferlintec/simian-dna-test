package br.com.ferlintec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferlintec.data.StatsVO;
import br.com.ferlintec.services.DNAVerificationServices;

@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private DNAVerificationServices service;
	
	@GetMapping
	public StatsVO getStats() {
		
		return service.getStats();
	}
}
