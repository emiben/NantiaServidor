package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Cliente;
import com.nantia.model.DiaSemana;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByNroDocumento(String nroDocumento);
	List<Cliente> findByMail(String mail);
	//DiaSemana addDias(DiaSemana dia, Cliente cliente);
	
}
