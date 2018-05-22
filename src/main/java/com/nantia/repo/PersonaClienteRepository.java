package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Persona;

public interface PersonaClienteRepository extends CrudRepository<Persona, Long>{
	Persona findByNroDocumento(String nroDocumento);
	List<Persona> findByMail(String mail);

}
