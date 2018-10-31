package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Cliente;
import com.nantia.model.Direccion;
import com.nantia.repo.DireccionRepository;

@Service
public class DireccionService implements IDireccionService {
	
	@Autowired
	private DireccionRepository direccionRepository;
	
	private Direccion findByDireccion(String direccion) {
		Direccion dir = direccionRepository.findByDireccion(direccion);
		return dir;
	}

	@Override
	public List<Direccion> getAllDirecciones() {
		List<Direccion> list = new ArrayList<>();		
		direccionRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Direccion getDireccionById(long direccionId) {
		Direccion dir = direccionRepository.findOne(direccionId);
		return dir;
	}

	@Override
	public Direccion addDireccion(Direccion direccion) {
		return direccionRepository.save(direccion);
	}

	@Override
	public Direccion updateDireccion(Direccion direccion) {
		return direccionRepository.save(direccion);
	}

	@Override
	public void deleteDireccion(long direccionId) {
		direccionRepository.delete(getDireccionById(direccionId));

	}

	@Override
	public boolean existe(Direccion direccion) {
		return findByDireccion(direccion.getDireccion()) != null;
	}

}
