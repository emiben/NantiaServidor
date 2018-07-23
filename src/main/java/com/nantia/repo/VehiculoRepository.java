package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;
import com.nantia.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {

	Vehiculo findByMatricula(String matricula);
}
