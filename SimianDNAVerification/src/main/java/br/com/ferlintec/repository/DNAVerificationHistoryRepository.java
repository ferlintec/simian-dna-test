package br.com.ferlintec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ferlintec.model.DnaVerification;

@Repository
public interface DNAVerificationHistoryRepository extends JpaRepository<DnaVerification, Long>{

}