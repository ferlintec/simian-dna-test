package br.com.ferlintec.data;

import java.io.Serializable;
import java.util.Arrays;

public class DnaVerificationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int hashCode;
	
	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dna);
		result = prime * result + hashCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DnaVerificationVO other = (DnaVerificationVO) obj;
		if (!Arrays.equals(dna, other.dna))
			return false;
		if (hashCode != other.hashCode)
			return false;
		return true;
	}
	

}
