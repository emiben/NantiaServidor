package com.nantia.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nantia.model.Venta;

public interface IVentaService {
	
	List<Venta> getAllVentas();
	Venta getVentaById(long ventaId);
	Venta addVenta(Venta venta);
	Venta updateVenta(Venta venta);
	void deleteVenta(long ventaId);
	boolean existe(Venta venta);
	List<Venta> getVentasPorPeriodo(String fechaIni, String fechaFin, long cliente);
}
