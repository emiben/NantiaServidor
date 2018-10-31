package com.nantia.service;

import java.util.List;

import com.nantia.model.VehiculoUbicacion;

public interface IVehiculoUbicacionService {
	
	List<VehiculoUbicacion> getAllVehiculoUbicacion();
	VehiculoUbicacion getVehiculoUbicacionById(long vehiculoUbicacionId);
	VehiculoUbicacion addVehiculoUbicacion(VehiculoUbicacion vehiculoUbicacion);
	VehiculoUbicacion updateVehiculoUbicacion(VehiculoUbicacion vehiculoUbicacion);
	void deleteVehiculoUbicacion(long vehiculoUbicacionId);
	boolean existe(VehiculoUbicacion vehiculoUbicacion);

}
