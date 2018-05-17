package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nantia.model.ListaDePrecios;
import com.nantia.repo.ListaDePreciosRepository;

public class ListaDePreciosService implements IListaDePreciosService{
	
	@Autowired
	private ListaDePreciosRepository listaDePreciosRepository;

	@Override
	public List<ListaDePrecios> getAllListaDePrecios() {
		List<ListaDePrecios> list = new ArrayList<>();
		listaDePreciosRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public ListaDePrecios getListaDePreciosById(long listaDePreciosId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaDePrecios addListaDePrecios(ListaDePrecios listaDePrecios) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaDePrecios updateListaDePrecios(ListaDePrecios listaDePrecios) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteListaDePrecios(long listaDePreciosId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existe(ListaDePrecios listaDePrecios) {
		// TODO Auto-generated method stub
		return false;
	}

}
