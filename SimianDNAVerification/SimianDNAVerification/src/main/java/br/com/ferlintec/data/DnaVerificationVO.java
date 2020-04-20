package br.com.ferlintec.data;

import java.io.Serializable;

public class DnaVerificationVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String[] dna;
	
	private String dnaCode;
	
	private boolean isSimian;
	
	private int dnaSideLength;
	
	private int hashCode;

	
	public DnaVerificationVO() {
		super();
	}
	
	public DnaVerificationVO(String[] dna) {
		super();
		
		this.setDna(dna);
	}
	
	
	public DnaVerificationVO(String[] dna, boolean isSimian) {
		super();
		
		this.setDna(dna);
		this.isSimian = isSimian;
	}

	public void setDnaCode(String[] dna) {
		StringBuilder sb = new StringBuilder();
		for (String s : dna)
			sb.append(s);
		this.setDnaCode(sb.toString());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dnaCode == null) ? 0 : dnaCode.hashCode());
		return result;
	}
	
	public void setDnaSideLength(String[] dnaCode) {
		this.dnaSideLength = dnaCode.length;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDnaCode() {
		return dnaCode;
	}
	

	public void setDnaCode(String dnaCode) {
		this.dnaCode = dnaCode;
	}

	public boolean isSimian() {
		return isSimian;
	}

	public void setSimian(boolean isSimian) {
		this.isSimian = isSimian;
	}

	public int getDnaSideLength() {
		return dnaSideLength;
	}

	public void setDnaSideLength(int dnaSideLength) {
		this.dnaSideLength = dnaSideLength;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
		this.setDnaCode(dna);
		this.setDnaSideLength(dna);
		this.setHashCode(this.hashCode());
	}

}
