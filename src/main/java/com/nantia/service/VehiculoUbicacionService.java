package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nantia.model.VehiculoUbicacion;
import com.nantia.repo.VehiculoUbicacionRepository;

@Service
public class VehiculoUbicacionService implements IVehiculoUbicacionService {

	
	@Autowired
	private VehiculoUbicacionRepository vehiculoUbicacionRepository;
	
	
	private VehiculoUbicacion findByCoordLatCoordLon(String coordLat, String coordLon) {
		VehiculoUbicacion ve = vehiculoUbicacionRepository.findByCoordLonAndCoordLat(coordLat, coordLon);
		return ve;
	}
	
	@Override
	public List<VehiculoUbicacion> getAllVehiculoUbicacion() {
		List<VehiculoUbicacion> list = new ArrayList<>();	
		vehiculoUbicacionRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public VehiculoUbicacion getVehiculoUbicacionById(long vehiculoUbicacionId) {
		VehiculoUbicacion ve = vehiculoUbicacionRepository.findOne(vehiculoUbicacionId);
		return ve;
	}

	@Override
	public VehiculoUbicacion addVehiculoUbicacion(VehiculoUbicacion vehiculoUbicacion) {
		return vehiculoUbicacionRepository.save(vehiculoUbicacion);
	}

	@Override
	public VehiculoUbicacion updateVehiculoUbicacion(VehiculoUbicacion vehiculoUbicacion) {
		return vehiculoUbicacionRepository.save(vehiculoUbicacion);
	}

	@Override
	public void deleteVehiculoUbicacion(long vehiculoUbicacionId) {
		vehiculoUbicacionRepository.delete(getVehiculoUbicacionById(vehiculoUbicacionId));
	}

	@Override
	public boolean existe(VehiculoUbicacion vehiculoUbicacion) {
		return findByCoordLatCoordLon(vehiculoUbicacion.getCoordLat(), vehiculoUbicacion.getCoordLon()) != null;
	}

}
