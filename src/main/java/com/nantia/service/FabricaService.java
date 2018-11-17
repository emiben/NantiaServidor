package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.controller.FabricaController;
import com.nantia.model.Fabrica;
import com.nantia.repo.FabricaRepository;

@Service
public class FabricaService implements IFabricaService {
	

	@Autowired
	private FabricaRepository fabricaRepository;
	
	private Fabrica findByNombre(String nombre) {
		Fabrica fab = fabricaRepository.findByNombre(nombre);
		return fab;
	}
	
	@Override
	public List<Fabrica> getAllFabrica() {
		List<Fabrica> list = new ArrayList<>();	
		fabricaRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Fabrica getFabricaById(long fabricaId) {
		
		Fabrica fab = fabricaRepository.findOne(fabricaId);
		
		return fab;
	}

	@Override
	public Fabrica addFabrica(Fabrica fabrica) {
		return fabricaRepository.save(fabrica);
	}

	@Override
	public Fabrica updateFabrica(Fabrica fabrica) {
		return fabricaRepository.save(fabrica);
	}

	@Override
	public void deleteFabrica(long fabricaId) {
		fabricaRepository.delete(getFabricaById(fabricaId));

	}

	@Override
	public boolean existe(Fabrica fabrica) {
		return findByNombre(fabrica.getNombre()) != null;
	}

	@Override
	public List<Object> getEnvasesEnPrestamo(long cliente) {
		
List<Object> list = new ArrayList<>();			
		
		if(cliente == 0)
		{
			fabricaRepository.getEnvasesEnPrestamo().forEach(e -> list.add(e));
		}
		else 
		{
			fabricaRepository.getEnvasesEnPrestamoYCliente(cliente).forEach(e -> list.add(e));
		}
						
		return list;
	}

}
