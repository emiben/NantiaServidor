package com.nantia.repo;


import org.springframework.data.repository.CrudRepository;
import com.nantia.model.Fabrica;

public interface FabricaRepository extends CrudRepository<Fabrica, Long>{
	
	Fabrica findByNombre(String nombre);

}
