package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	List<Producto> findByNombre(String nombre);
	List<Producto> findByPresentacion(String presentacion);
	List<Producto> findByDescripcion(String descripcion);
	List<Producto> findByRetornable(boolean retornable);
	Producto findByNombreAndPresentacion(String nombre, String presentacion);
}
