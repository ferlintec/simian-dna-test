package br.com.ferlintec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ferlintec.model.DnaVerification;

@Repository
public interface DNAVerificationRepository extends JpaRepository<DnaVerification, Long>{

	DnaVerification findByHashCode(int hashCode);

	@Query("SELECT count(p) as count_mutant FROM DnaVerification p WHERE p.isSimian = true")
	int countMutantDna();
	
	@Query("SELECT count(p) as count_human FROM DnaVerification p WHERE p.isSimian = false")
	int countHumanDna();
}