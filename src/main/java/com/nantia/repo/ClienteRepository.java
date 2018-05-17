package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByCodigo(int codigo);
	List<Cliente> findByNombre(String nombre);
	List<Cliente> findByApellido(String apellido);
	List<Cliente> findByDireccion(String direccion);
	List<Cliente> findByTelefono(String telefono);

}
