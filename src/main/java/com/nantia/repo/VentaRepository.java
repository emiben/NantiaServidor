package com.nantia.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Cliente;
import com.nantia.model.Venta;

public interface VentaRepository extends CrudRepository<Venta, Long>{
	
	List<Venta> findByFecha(Date fecha);
	List<Venta> findByCliente(Cliente cliente);
	Venta findById(long id);

}
