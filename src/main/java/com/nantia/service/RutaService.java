package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Fabrica;
import com.nantia.model.Ruta;
import com.nantia.repo.RutaRepository;

@Service
public class RutaService implements IRutaService {
	
	@Autowired
	private RutaRepository rutaRepository;
	
	private Ruta findByNombre(String nombre) {
		Ruta ruta = rutaRepository.findByNombre(nombre);
		return ruta;
	}

	@Override
	public List<Ruta> getAllRuta() {
		List<Ruta> list = new ArrayList<>();	
		rutaRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Ruta getRutaById(long rutaId) {
		Ruta ruta = rutaRepository.findOne(rutaId);
		return ruta;
	}

	@Override
	public Ruta addRuta(Ruta ruta) {
		return rutaRepository.save(ruta);
	}

	@Override
	public Ruta updateRuta(Ruta ruta) {
		return rutaRepository.save(ruta);
	}

	@Override
	public void deleteRuta(long rutaId) {
		rutaRepository.delete(getRutaById(rutaId));

	}

	@Override
	public boolean existe(Ruta ruta) {
		return findByNombre(ruta.getNombre()) != null;
	}

}
