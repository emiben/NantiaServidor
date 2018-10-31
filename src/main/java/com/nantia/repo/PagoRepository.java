package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.nantia.model.Pago;

public interface PagoRepository  extends CrudRepository<Pago, Long>{
	
	List<Pago> findByVentaId(Long ventaId);
	List<Pago> findByClienteId(Long clienteId);
	Pago findById(Long pagoId);

}
