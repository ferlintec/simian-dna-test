package br.com.ferlintec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferlintec.data.StatsVO;

@RestController
@RequestMapping("/stats")
public class StatsController {

	@GetMapping
	public StatsVO getStats() {
		return new StatsVO(45, 56, 4.5);
	}
}
