package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Rol;

public interface RolRepository extends CrudRepository<Rol, Long> {
	Rol findByNombreRol(String nombreRol);
}
