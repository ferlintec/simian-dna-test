package br.com.ferlintec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferlintec.data.DnaVerificationVO;
import br.com.ferlintec.dna.SimianDNAVerification;
import br.com.ferlintec.exception.NotSimianDNAException;
import br.com.ferlintec.exception.ResourceNotFoundException;
import br.com.ferlintec.model.DnaVerification;
import br.com.ferlintec.repository.DNAVerificationHistoryRepository;

@Service
public class SimianServices {

	
	@Autowired
	DNAVerificationHistoryRepository repository;
		
	public DnaVerification create(DnaVerification dnaVerification) {
		return repository.save(dnaVerification);
	}
	
	
	public boolean isSimian(DnaVerificationVO vo) {
		SimianDNAVerification test = new SimianDNAVerification(); 

		boolean isSimian = test.isSimian(vo.getDna());
				
		DnaVerification entity = new DnaVerification(vo.getDna(), isSimian);
		entity.setHashCode(entity.hashCode());
		repository.save(entity);
		
		if (!isSimian) {
			throw new NotSimianDNAException("Não é um Simian.");
		}
		
		return true;
	}
	
	public List<DnaVerification> findAll() {
		return repository.findAll();
	}	
	
	public DnaVerification findById(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
		
	public DnaVerification update(DnaVerification dnaVerification) {
		DnaVerification entity = repository.findById(dnaVerification.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setDnaCode(dnaVerification.getDnaCode());
		entity.setSimian(dnaVerification.isSimian());
		entity.setDnaSideLength(dnaVerification.getDnaSideLength());
		entity.setHashCode(dnaVerification.hashCode());
		
		return repository.save(entity);
	}	
	
	public void delete(Long id) {
		DnaVerification entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	
}


