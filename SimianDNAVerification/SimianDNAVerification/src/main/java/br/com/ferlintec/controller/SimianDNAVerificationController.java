package br.com.ferlintec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferlintec.data.DnaVerificationVO;
import br.com.ferlintec.data.StatsVO;
import br.com.ferlintec.services.DNAVerificationServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "SimianDNAVerificationEndpoint", description = "Verificação de DNA e estatística", tags = { "DnaTestEndpoint" })
@RestController
@RequestMapping("/dna-test")
public class SimianDNAVerificationController {

	@Autowired
	private DNAVerificationServices service;

	/**
	 * Verifica se o DNA informardo pertence a um Simian ou a um Humano.
	 * 
	 * @param dnaVerificationVO Objeto contendo o atributo dna (array de Strings) com os códigos genéticos.
	 * @return Retorna TRUE, com status 200 (OK), se o DNA é Simian, ou FALSE com Status 403 (Forbbiden) se o DNA não for Simian.
	 */
	@ApiOperation(value = "Verifica se o DNA pertence a um Simian") 
	@PostMapping(value = "/simian", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> isSimian(@RequestBody DnaVerificationVO dnaVerificationVO) {
		
		if (service.isSimian(dnaVerificationVO.getDna()))
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		else
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
	}


	
	/**
	 * Retorna a estatistica de DNAs verificados.
	 * Exemplo:
	 * 
	 * 	count_mutant_dna: 4
	 * 	count_human_dna: 2
	 * 	ratio: 2
	 * 
	 * @return Estatística com os totais de DNAs simians e humanos testados, e sua proporção.
	 */
	@ApiOperation(value = "Retorna a estatistica de DNAs verificados, simian vs. humano." )
	@GetMapping(value = "/stats", produces = { "application/json", "application/xml", "application/x-yaml" })
	public StatsVO getStats() {
		
		return service.getStats();
	}
	
}
