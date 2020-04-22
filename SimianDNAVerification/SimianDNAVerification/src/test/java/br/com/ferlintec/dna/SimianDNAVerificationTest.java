package br.com.ferlintec.dna;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import br.com.ferlintec.exception.InvalidDNAException;

public class SimianDNAVerificationTest {
	
	
    @InjectMocks
    SimianDNAVerification simianDNAVerificatio;

	//Caso não ocorra a exeção esperada, este objeto acusará o erro.
	@Rule
	public ExpectedException thrown  = ExpectedException.none();
	
	
    @Before
    public void setUp() {
    	simianDNAVerificatio = new SimianDNAVerification(); 
    }

    @Test
    public void dnaSimianValidoTest() {
    	
    	String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    	
    	Assert.assertTrue(simianDNAVerificatio.isSimian(dna));
    }
    
    @Test
    public void dnaHumanoTest() {
    	
    	String [] dna = {"ACTG", "TACG", "CTGC", "AGCT"};
    	
    	Assert.assertFalse(simianDNAVerificatio.isSimian(dna));
    }
    
    @Test
    public void dnaMatrizNaoQuadradaTest() {
    	
    	thrown.expect(InvalidDNAException.class);
    	
    	String [] dna1 = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "ATCACTG"},
	    		  dna2 = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA"};
    	
		simianDNAVerificatio.isSimian(dna1);
		simianDNAVerificatio.isSimian(dna2);
    }

    @Test
    public void dnaLetraErradaTest() {
    	
    	thrown.expect(InvalidDNAException.class);
    	
    	String [] dna = {"WCTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    	
    	simianDNAVerificatio.isSimian(dna);
    }

}
