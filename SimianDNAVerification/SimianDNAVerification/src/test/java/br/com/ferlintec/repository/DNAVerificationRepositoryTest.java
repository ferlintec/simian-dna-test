package br.com.ferlintec.repository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ferlintec.model.DnaVerification;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Para testar no próprio banco de dados.
public class DNAVerificationRepositoryTest {

	@Autowired
	private DNAVerificationRepository repository;
	
	//Caso não ocorra a exeção esperada, este objeto acusará o erro.
	@Rule
	public ExpectedException thrown  = ExpectedException.none();
	
	
	@Test
	public void saveAndFindByHashCodeDNASimian() {
		
		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
		DnaVerification entity = new DnaVerification(dna, true);
		
		entity = this.repository.save(entity);
		Assertions.assertThat(entity.getId()).isNotNull();
		
		DnaVerification entity2 = this.repository.findByHashCode(entity.getHashCode()); 

		Assertions.assertThat(entity.getId()).isEqualTo(entity2.getId());
	}
	
	@Test
	public void saveDNAExceptionConstraintUnique() {
		
		thrown.expect(DataIntegrityViolationException.class);
		
		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

		DnaVerification entity = new DnaVerification(dna, true);
		entity = this.repository.save(entity);
			
		DnaVerification entity2 = new DnaVerification(dna, true);
		entity2 = this.repository.save(entity2);
	}
	
	@Test 
	public void deleteDNA() {
		
		String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

		DnaVerification entity = new DnaVerification(dna, true);
		entity = this.repository.save(entity);
			
		this.repository.delete(entity);
		
		entity = this.repository.findByHashCode(entity.getHashCode());
		
		Assertions.assertThat(entity).isNull();

		
	}
	
	@Test
	public void countMutantDna() {

		String [] dna = {"CTCAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCACTA", "TCACTG"};
		DnaVerification entity = new DnaVerification(dna, true);
		this.repository.save(entity);
		
		String [] dna2 = {"AATA", "TGAC", "ATTG", "GATG"};
		DnaVerification entity2 = new DnaVerification(dna2, true);
		this.repository.save(entity2);
		
		int count_mutant_dna = repository.countMutantDna();
		
		Assertions.assertThat(count_mutant_dna).isEqualTo(2);
	}
	
	@Test
	public void countHumanDna() {

		String [] dna = {"CTGAGA", "CTGAGC", "TATTAT", "AGAGCG", "CCCCTA", "TCACTG"};
		DnaVerification entity = new DnaVerification(dna, false);
		this.repository.save(entity);
		
		String [] dna2 = {"AAAA", "TCAC", "ATTA", "CAGG"};
		DnaVerification entity2 = new DnaVerification(dna2, false);
		this.repository.save(entity2);
		
		int count_human_dna = repository.countHumanDna();
		
		Assertions.assertThat(count_human_dna).isEqualTo(2);
	}
	
}
