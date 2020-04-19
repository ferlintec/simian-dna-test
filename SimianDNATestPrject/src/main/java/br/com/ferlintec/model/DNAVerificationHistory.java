package br.com.ferlintec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dna_history_verification")
public class DNAVerificationHistory implements Serializable{
	
	public DNAVerificationHistory() {
		super();
	}
	
	public DNAVerificationHistory(String dnaCode, boolean isSimian, int size) {
		super();
		this.dnaCode = dnaCode;
		this.isSimian = isSimian;
		this.size = size;
		
		this.hashCode = this.hashCode();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dna_code", nullable = false, length = 2000)
	private String dnaCode;
	
	@Column(name = "is_simian", nullable = false)
	private boolean isSimian;
	
	@Column(nullable = false)
	private int size;
	
	@Column(nullable = false)
	private int hashCode;

	
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
		result = prime * result + ((dnaCode == null) ? 0 : dnaCode.hashCode());
		result = prime * result + size;
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
		DNAVerificationHistory other = (DNAVerificationHistory) obj;
		if (dnaCode == null) {
			if (other.dnaCode != null)
				return false;
		} else if (!dnaCode.equals(other.dnaCode))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	
}
