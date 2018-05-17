package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.ListaDePrecios;


public interface ListaDePreciosRepository extends CrudRepository<ListaDePrecios, Long>{

	ListaDePrecios findByNombre(String nombre);

}
