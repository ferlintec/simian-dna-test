package br.com.ferlintec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferlintec.data.SimianVO;
import br.com.ferlintec.exception.NotSimianDNAException;
import br.com.ferlintec.exception.ResourceNotFoundException;
import br.com.ferlintec.model.DNAVerificationHistory;
import br.com.ferlintec.repository.DNAVerificationHistoryRepository;
import br.com.ferlintec.simiantest.SimianDNATest;

@Service
public class SimianServices {

	
	@Autowired
	DNAVerificationHistoryRepository repository;
		
	public DNAVerificationHistory create(DNAVerificationHistory dNAVerificationHistory) {
		return repository.save(dNAVerificationHistory);
	}
	
	
	public boolean isSimian(SimianVO vo) {
		SimianDNATest test = new SimianDNATest(); 

		boolean isSimian = test.isSimian(vo.getDna());
		
		StringBuilder sb = new StringBuilder();
		for (String s : vo.getDna())
			sb.append(s);
		
		DNAVerificationHistory entity = new DNAVerificationHistory(sb.toString(), isSimian, vo.getDna().length);
		entity.setHashCode(entity.hashCode());
		repository.save(entity);
		
		if (!isSimian) {
			throw new NotSimianDNAException("Não é um Simian.");
		}
		
		return true;
	}
	
	public List<DNAVerificationHistory> findAll() {
		return repository.findAll();
	}	
	
	public DNAVerificationHistory findById(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
		
	public DNAVerificationHistory update(DNAVerificationHistory dNAVerificationHistory) {
		DNAVerificationHistory entity = repository.findById(dNAVerificationHistory.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setDnaCode(dNAVerificationHistory.getDnaCode());
		entity.setSimian(dNAVerificationHistory.isSimian());
		entity.setSize(dNAVerificationHistory.getSize());
		entity.setHashCode(dNAVerificationHistory.hashCode());
		
		return repository.save(entity);
	}	
	
	public void delete(Long id) {
		DNAVerificationHistory entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	
}


