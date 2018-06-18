package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.nantia.model.ListaPrecio;


public interface ListaDePrecioRepository  extends CrudRepository<ListaPrecio, Long>{
	ListaPrecio findById(long id);
	ListaPrecio findByNombreLista(String nombreLista);
}
