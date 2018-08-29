package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.VehiculoUbicacion;

public interface VehiculoUbicacionRepository extends CrudRepository<VehiculoUbicacion, Long>{
	VehiculoUbicacion findByCoordLonAndCoordLat(String coordLon, String coordLat);
}
