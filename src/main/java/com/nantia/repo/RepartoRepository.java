package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Fabrica;
import com.nantia.model.Reparto;

public interface RepartoRepository extends CrudRepository<Reparto, Long>{
	
	Reparto findByDescripcion(String descripcion);

}
