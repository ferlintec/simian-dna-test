package br.com.ferlintec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ferlintec.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}