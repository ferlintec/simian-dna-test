package br.com.ferlintec.dna;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import br.com.ferlintec.exception.InvalidDNAException;

public class SimianDNAVerificationTest {
	
	
    List<String[]> dnaList;
    
    @InjectMocks
    SimianDNAVerification simianDNAVerificatio;

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
    	
    	String [] dna1 = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "ATCACTG"},
	    		  dna2 = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA"};
    	
    	try {
    		simianDNAVerificatio.isSimian(dna1);
    		simianDNAVerificatio.isSimian(dna2);
		} catch (InvalidDNAException e) {
			Assert.assertTrue(true);
			return;
		}
    	Assert.assertTrue(false);
    }

    @Test
    public void dnaLetraErradaTest() {
    	
    	String [] dna = {"WCTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    	
    	
    	try {
    		Assert.assertTrue(simianDNAVerificatio.isSimian(dna));
		} catch (InvalidDNAException e) {
			Assert.assertTrue(true);
			return;
		}
    	Assert.assertTrue(false);
    }

}
