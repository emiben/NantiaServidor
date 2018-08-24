package com.nantia.service;

import java.util.List;

import com.nantia.model.Venta;

public interface IVentaService {
	
	List<Venta> getAllVentas();
	Venta getVentaById(long ventaId);
	Venta addVenta(Venta venta);
	Venta updateVenta(Venta venta);
	void deleteVenta(long ventaId);
	boolean existe(Venta venta);

}
