package com.nantia.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.nantia.model.ListaPrecio;
import com.nantia.repo.ListaDePrecioRepository;

@Service
public class ListaPrecioService implements IListaPrecioService{
	
	private final Logger LOG = LoggerFactory.getLogger(ListaPrecio.class);


	@Autowired
	private ListaDePrecioRepository listaDePrecioRepository;
	
	private ListaPrecio findByNombre(String nombre) {
		ListaPrecio lPrecio = listaDePrecioRepository.findByNombreLista(nombre);
		return lPrecio;
	}
	

	@Override
	public List<ListaPrecio> getAllListaPrecio() {
		List<ListaPrecio> list = new ArrayList<>();	
		listaDePrecioRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public ListaPrecio getListaPrecioById(long listaPrecioId) {
		ListaPrecio liPrecio = listaDePrecioRepository.findOne(listaPrecioId);
		return liPrecio;
	}

	@Override
	public ListaPrecio addListaPrecio(ListaPrecio listaPrecio) {
		return listaDePrecioRepository.save(listaPrecio);
	}

	@Override
	public ListaPrecio updateListaPrecio(ListaPrecio listaPrecio) {
		return listaDePrecioRepository.save(listaPrecio);
	}

	@Override
	public void deleteListaPrecio(long listaPrecioId) {
		listaDePrecioRepository.delete(getListaPrecioById(listaPrecioId));
	}

	@Override
	public boolean existe(ListaPrecio listaPrecio) {
		return findByNombre(listaPrecio.getNombreLista()) != null;
	}

}
