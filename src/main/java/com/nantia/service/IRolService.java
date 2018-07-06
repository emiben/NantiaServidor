package com.nantia.service;

import java.util.List;

import com.nantia.model.Rol;

public interface IRolService {
	List<Rol> getAllRoles();
	Rol getRolById(long rolId);
	Rol addRol(Rol rol);
	Rol updateRol(Rol rol);
	void deleteRol(long rolId);
	boolean existe(Rol rol);
}
