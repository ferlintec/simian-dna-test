package br.com.ferlintec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferlintec.data.DnaVerificationVO;
import br.com.ferlintec.dna.SimianDNAVerification;
import br.com.ferlintec.exception.NotSimianDNAException;
import br.com.ferlintec.exception.ResourceNotFoundException;
import br.com.ferlintec.model.DnaVerification;
import br.com.ferlintec.repository.DNAVerificationRepository;

@Service
public class SimianServices {

	
	@Autowired
	DNAVerificationRepository repository;
		

	
	/**
	 * Verifica se  o DNA informado é de um mutante Simian ou de um
	 * humano.
	 * O sistema gera exceção caso a matriz de DNA seja inválida, nos seguintes casos:
	 * 	-Possuir letra diferente de ACTG.
	 *  -Não for quadrada.
	 * 
	 * O mesmo DNA não terá duplicidade na base.
	 * 
	 * @param dnaVerificationVO
	 * @return TRUE se for DNA mutante Simian, FALSE se for DNA humano.
	 */
	public boolean isSimian(DnaVerificationVO dnaVerificationVO) {


		DnaVerification entity = findByHashCode(dnaVerificationVO.getHashCode());

		if (entity != null) {
			return entity.isSimian();
		}else {
			
			var simianDNAVerification = new SimianDNAVerification(); 
			
			boolean isSimian = simianDNAVerification.isSimian(dnaVerificationVO.getDna());
			
			entity = new DnaVerification(dnaVerificationVO.getDna(), isSimian);

			create(entity);

			return isSimian;
		}
		
	}
	
	public DnaVerification findByHashCode(int hashCode) {
		
		return repository.findByHashCode(hashCode);
	}

	public DnaVerification create(DnaVerification dnaVerification) {
		return repository.save(dnaVerification);
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


