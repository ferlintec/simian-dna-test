package br.com.ferlintec.data;

import java.io.Serializable;

public class DnaVerificationVO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private String[] dna;
	
	
	public DnaVerificationVO() {
		super();
	}
	
	public DnaVerificationVO(String[] dna) {
		super();
		
		this.setDna(dna);
	}
	

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

}
