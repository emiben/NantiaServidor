package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Fabrica;
import com.nantia.model.Vehiculo;
import com.nantia.repo.FabricaRepository;
import com.nantia.repo.VehiculoRepository;

@Service
public class VehiculoService implements IVehiculoService {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	private Vehiculo findByMatricula(String matricula) {
		Vehiculo ve = vehiculoRepository.findByMatricula(matricula);
		return ve;
	}

	@Override
	public List<Vehiculo> getAllVehiculo() {
		List<Vehiculo> list = new ArrayList<>();	
		vehiculoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Vehiculo getVehiculoById(long vehiculoId) {
		Vehiculo ve = vehiculoRepository.findOne(vehiculoId);
		return ve;
	}

	@Override
	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public Vehiculo updateVehiculo(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public void deleteVehiculo(long vehiculoId) {
		vehiculoRepository.delete(getVehiculoById(vehiculoId));

	}

	@Override
	public boolean existe(Vehiculo vehiculo) {
		return findByMatricula(vehiculo.getMatricula()) != null;
	}

}
