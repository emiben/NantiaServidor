package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByTipoDocumento(String tipoDocumento);
	List<Cliente> findByMail(String apellido);
	
}
