package br.com.ferlintec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferlintec.data.SimianVO;
import br.com.ferlintec.services.SimianServices;

@RestController
@RequestMapping("/simian")
public class SimianController {

	@Autowired
	private SimianServices service;

	
	@PostMapping
	public boolean isSimian(@RequestBody SimianVO vo) {
		
		return service.isSimian(vo);
	}
	
	
}
