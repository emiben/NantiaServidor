package com.nantia.service;

import java.util.List;

import com.nantia.model.ListaDePrecios;

public interface IListaDePreciosService {
	
	List<ListaDePrecios> getAllListaDePrecios();
	ListaDePrecios getListaDePreciosById(long listaDePreciosId);
	ListaDePrecios addListaDePrecios(ListaDePrecios listaDePrecios);
	ListaDePrecios updateListaDePrecios(ListaDePrecios listaDePrecios);
	void deleteListaDePrecios(long listaDePreciosId);
	boolean existe(ListaDePrecios listaDePrecios);

}
