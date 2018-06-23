package com.nantia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.nantia.model.Cliente;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.model.EnvasesTipos;

public interface EnvasesEnPrestamoRepository extends CrudRepository<EnvasesEnPrestamo, Long>{

	List<EnvasesEnPrestamo> findByClientes(Cliente cliente);
	EnvasesEnPrestamo findByClientesAndEnvasetipos(Cliente clienteId, EnvasesTipos tipoId);//long clienteId, long tipoId//EnvasesEnPrestamo envasesEnPrestamo
}
