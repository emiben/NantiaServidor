package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Rol;
import com.nantia.repo.RolRepository;

@Service
public class RolService implements IRolService {
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<Rol> getAllRoles() {
		List<Rol> list = new ArrayList<>();
		rolRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Rol getRolById(long rolId) {
		Rol rol = rolRepository.findOne(rolId);
		return rol;
	}

	@Override
	public Rol addRol(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public Rol updateRol(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public void deleteRol(long rolId) {
		rolRepository.delete(rolId);
	}

	@Override
	public boolean existe(Rol rol) {
		return findByNombreRol(rol.getNombreRol()) != null;
	}
	
	private Rol findByNombreRol(String nombreRol) {
		Rol rol = rolRepository.findByNombreRol(nombreRol);
		return rol;
	}

}
