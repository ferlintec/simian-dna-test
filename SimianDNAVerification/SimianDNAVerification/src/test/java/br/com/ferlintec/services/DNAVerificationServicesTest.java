package br.com.ferlintec.services;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ferlintec.data.DnaVerificationVO;
import br.com.ferlintec.exception.InvalidDNAException;
import br.com.ferlintec.model.DnaVerification;
import br.com.ferlintec.repository.DNAVerificationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DNAVerificationServicesTest {

	@Autowired
	private DNAVerificationServices service;
	
	@MockBean
	private DNAVerificationRepository repository;
	

	//Caso não ocorra a exeção esperada, este objeto acusará o erro.
	@Rule
	public ExpectedException thrown  = ExpectedException.none();

	@Test
	public void isSimianDNASimianValidoJaCadastrado() {

		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
		
		DnaVerification entity = new DnaVerification(dna, true);
		 
		Mockito.when(repository.findByHashCode(Mockito.anyInt())).thenReturn(entity);
		
		DnaVerificationVO dnaVerificationVO = new DnaVerificationVO(dna);
		
		Assertions.assertThat(service.isSimian(dnaVerificationVO)).isTrue();
	}
	
	@Test
	public void isSimianDNASimian() {

		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
		
		Mockito.when(repository.findByHashCode(Mockito.anyInt())).thenReturn(null);
		
		DnaVerificationVO dnaVerificationVO = new DnaVerificationVO(dna);
		
		Assertions.assertThat(service.isSimian(dnaVerificationVO)).isTrue();
	}
	
	@Test
	public void isSimianDNAHumano() {
		String [] dna = {"ACTG", "TACG", "CTGC", "AGCT"};

		Mockito.when(repository.findByHashCode(Mockito.anyInt())).thenReturn(null);
		
		DnaVerificationVO dnaVerificationVO = new DnaVerificationVO(dna);
		
		Assertions.assertThat(service.isSimian(dnaVerificationVO)).isFalse();
	}
	
    @Test
    public void isSimianDnaMatrizNaoQuadradaTest() {
    	
    	thrown.expect(InvalidDNAException.class);
    	
    	Mockito.when(repository.findByHashCode(Mockito.anyInt())).thenReturn(null);
    	
    	String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "AT"};
			
		service.isSimian(new DnaVerificationVO(dna));
    }
    
    @Test
    public void isSimianDnaLetraErradaTest() {
    	
    	thrown.expect(InvalidDNAException.class);
    	
    	Mockito.when(repository.findByHashCode(Mockito.anyInt())).thenReturn(null);
    	
    	String [] dna = {"WWWW", "AAAA", "TTTT", "GGGG"};
			
		service.isSimian(new DnaVerificationVO(dna));
    }
    
    @Test
    public void getStatsOK() {
    	
    	Mockito.when(repository.countMutantDna()).thenReturn(5);
    	Mockito.when(repository.countHumanDna()).thenReturn(2);

    	Assertions.assertThat(service.getStats().getRatio()).isEqualTo(2.5);
    	
    }
}
