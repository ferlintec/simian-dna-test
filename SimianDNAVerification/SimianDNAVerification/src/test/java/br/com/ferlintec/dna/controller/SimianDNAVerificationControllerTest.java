package br.com.ferlintec.dna.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ferlintec.data.DnaVerificationVO;
import br.com.ferlintec.data.StatsVO;
import br.com.ferlintec.services.DNAVerificationServices;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SimianDNAVerificationControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
	
	@MockBean
	private DNAVerificationServices service;
	

	@Test
	public void getStats() {
		
		
		StatsVO stats = new StatsVO(2, 4, 0.5);
		BDDMockito.when(service.getStats()).thenReturn(stats);		
		
		ResponseEntity<StatsVO> response = restTemplate.getForEntity("/dna-test/stats", StatsVO.class);
	
		Assertions.assertThat(((StatsVO)response.getBody()).getRatio()).isEqualTo(0.5);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualByComparingTo(200);
		
	}
	
	@Test
	public void getSimianTrueAndReturnStatusOk200() {

		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
		DnaVerificationVO dnaVerificationVO = new DnaVerificationVO(dna);
		 
		BDDMockito.when(service.isSimian(Mockito.any())).thenReturn(true);
		
		ResponseEntity<Boolean> response = restTemplate.postForEntity("/dna-test/simian", dnaVerificationVO, Boolean.class);
		
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	
	@Test
	public void getSimianFalseAndReturnStatusForbbiden403() {

		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
		DnaVerificationVO dnaVerificationVO = new DnaVerificationVO(dna);
		 
		BDDMockito.when(service.isSimian(Mockito.any())).thenReturn(false);
		
		ResponseEntity<Boolean> response = restTemplate.postForEntity("/dna-test/simian", dnaVerificationVO, Boolean.class);
		
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}
	
}
