package com.nantia.service;

import java.util.List;

import com.nantia.model.Direccion;

public interface IDireccionService {
	List<Direccion> getAllDirecciones();
	Direccion getDireccionById(long direccionId);
	Direccion addDireccion(Direccion direccion);
	Direccion updateDireccion(Direccion direccion);
	void deleteDireccion(long direccionId);
	boolean existe(Direccion direccion);

}
