package br.com.ferlintec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dna_verification")
public class DnaVerification implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dna_code", nullable = false, length = 2000)
	private String dnaCode;
	
	@Column(name = "is_simian", nullable = false)
	private boolean isSimian;
	
	@Column(name = "dna_side_length",nullable = false)
	private int dnaSideLength;
	
	
	@Column(name ="hash_code", nullable = false)
	private int hashCode;

	
	public DnaVerification() {
		super();
	}
	
	public DnaVerification(String[] dna) {
		super();
		
		this.setDnaCode(dna);
	}
	
	public DnaVerification(String[] dna, boolean isSimian) {
		super();
		
		this.setDnaCode(dna);
		this.isSimian = isSimian;
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
	
	public void setDnaCode(String[] dna) {
		StringBuilder sb = new StringBuilder();
		for (String s : dna)
			sb.append(s);
		this.setDnaCode(sb.toString());
		this.setDnaSideLength(dna);
		this.setHashCode(this.hashCode());
	}

	public boolean isSimian() {
		return isSimian;
	}

	public void setSimian(boolean isSimian) {
		this.isSimian = isSimian;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public int getDnaSideLength() {
		return dnaSideLength;
	}

	public void setDnaSideLength(int dnaSideLength) {
		this.dnaSideLength = dnaSideLength;
	}

	public void setDnaSideLength(String[] dnaCode) {
		this.dnaSideLength = dnaCode.length;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dnaCode == null) ? 0 : dnaCode.hashCode());
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
		DnaVerification other = (DnaVerification) obj;
		if (dnaCode == null && other.dnaCode != null) {
				return false;
		} else if (!dnaCode.equals(other.dnaCode))
			return false;
		if (dnaSideLength != other.dnaSideLength)
			return false;
		return true;
	}


	
}
