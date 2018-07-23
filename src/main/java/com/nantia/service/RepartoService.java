package com.nantia.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Reparto;
import com.nantia.repo.RepartoRepository;

@Service
public class RepartoService implements IRepartoService {
	
	@Autowired
	private RepartoRepository repartoRepository;
	
	private Reparto findByDescripcion(String descripcion) {
		Reparto rep = repartoRepository.findByDescripcion(descripcion);
		return rep;
	}

	@Override
	public List<Reparto> getAllReparto() {
		List<Reparto> list = new ArrayList<>();	
		repartoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Reparto getRepartoById(long repartoId) {
		Reparto rep = repartoRepository.findOne(repartoId);
		return rep;
	}

	@Override
	public Reparto addReparto(Reparto reparto) {
		return repartoRepository.save(reparto);
	}

	@Override
	public Reparto updateReparto(Reparto reparto) {
		return repartoRepository.save(reparto);
	}

	@Override
	public void deleteReparto(long repartoId) {
		repartoRepository.delete(getRepartoById(repartoId));

	}

	@Override
	public boolean existe(Reparto reparto) {
		return findByDescripcion(reparto.getDescripcion()) != null;
	}

}
