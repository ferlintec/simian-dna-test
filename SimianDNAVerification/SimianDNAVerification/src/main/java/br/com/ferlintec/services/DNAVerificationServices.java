package br.com.ferlintec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferlintec.data.StatsVO;
import br.com.ferlintec.dna.SimianDNAVerification;
import br.com.ferlintec.model.DnaVerification;
import br.com.ferlintec.repository.DNAVerificationRepository;

@Service
public class DNAVerificationServices {

	
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
	 * @param dna
	 * @return TRUE se for DNA mutante Simian, FALSE se for DNA humano.
	 */
	public boolean isSimian(String[] dna) {

		DnaVerification entitySearch = new DnaVerification(dna);
		
		DnaVerification entity = findByHashCode(entitySearch.hashCode());

		if (entity != null) {
			return entity.isSimian();
		}else {
			
			boolean isSimian = SimianDNAVerification.isSimian(dna);
			
			DnaVerification entityNew = new DnaVerification(dna, isSimian);

			repository.save(entityNew);

			return isSimian;
		}
		
	}
	
	/**
	 * Retorna o total de DNAs mutantes (Simian) e humanos, e a proporção
	 * de mutantes sobre o total de humandos.
	 * 
	 * @return Estatística total de DNAs verficados.
	 */
	public StatsVO getStats() {
	
		int count_mutant_dna = repository.countMutantDna();
		int count_human_dna = repository.countHumanDna();
		double ratio = (double)count_mutant_dna / count_human_dna;
		
		return new StatsVO(count_mutant_dna, count_human_dna, ratio);
	}
	
	/**
	 * Verifica se o DNA já foi verificado, ou seja, se está na base, a partir
	 * de seu hashCode.
	 * 
	 * @param hashCode
	 * @return
	 */
	public DnaVerification findByHashCode(int hashCode) {
		
		return repository.findByHashCode(hashCode);
	}

	
}


