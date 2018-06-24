package com.nantia.repo;



import org.springframework.data.repository.CrudRepository;
import com.nantia.model.EnvasesTipos;

public interface EnvasesTipoRepository extends CrudRepository<EnvasesTipos, Long>{
	EnvasesTipos findById(long envasesTipoId);
	EnvasesTipos findByDescripcion(String descripcion);


}
