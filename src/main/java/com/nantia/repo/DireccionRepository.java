package com.nantia.repo;


import org.springframework.data.repository.CrudRepository;
import com.nantia.model.Direccion;


public interface DireccionRepository extends CrudRepository<Direccion, Long>{
	Direccion findByCoordLonAndCoordLat(float coordLat, float coordLon);
	Direccion findByDireccion(String direccion);
	//List<Direccion> findByIdZona(long idZona);
}
