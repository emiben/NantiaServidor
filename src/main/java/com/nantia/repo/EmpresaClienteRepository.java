package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Empresa;

public interface EmpresaClienteRepository extends CrudRepository<Empresa, Long>{
	Empresa findByNroDocumento(String nroDocumento);
	List<Empresa> findByMail(String mail);

}
