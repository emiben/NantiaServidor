package com.nantia.service;

import java.util.List;
import com.nantia.model.ListaPrecio;

public interface IListaPrecioService {
	
	List<ListaPrecio> getAllListaPrecio();
	ListaPrecio getListaPrecioById(long listaPrecioId);
	ListaPrecio addListaPrecio(ListaPrecio listaPrecio);
	ListaPrecio updateListaPrecio(ListaPrecio listaPrecio);
	void deleteListaPrecio(long listaPrecioId);
	boolean existe(ListaPrecio listaPrecio);

}
