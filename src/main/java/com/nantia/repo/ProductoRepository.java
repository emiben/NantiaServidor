package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

	List<Producto> findByRetornable(boolean retornable);
	Producto findByNombre(String nombre);
}
