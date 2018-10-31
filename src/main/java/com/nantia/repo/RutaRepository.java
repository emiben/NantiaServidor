package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Ruta;

public interface RutaRepository extends CrudRepository<Ruta, Long> {

	Ruta findByNombre(String nombre);
}
