package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nantia.model.Pago;
import com.nantia.model.Venta;

public interface PagoRepository  extends CrudRepository<Pago, Long>{
	
	List<Pago> findByVentaId(Long ventaId);
	List<Pago> findByClienteId(Long clienteId);
	Pago findById(Long pagoId);
	
}
