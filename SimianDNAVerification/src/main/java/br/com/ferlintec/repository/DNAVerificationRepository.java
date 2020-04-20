package br.com.ferlintec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ferlintec.model.DnaVerification;

@Repository
public interface DNAVerificationRepository extends JpaRepository<DnaVerification, Long>{

	DnaVerification findByHashCode(int hashCode);

}