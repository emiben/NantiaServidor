package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByNroDocumento(String nroDocumento);
	List<Cliente> findByMail(String apellido);
	
}
