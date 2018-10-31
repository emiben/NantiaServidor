package com.nantia.service;

import java.util.List;

import com.nantia.model.Pago;

public interface IPagoService {
	
	List<Pago> getAllPagos();
	Pago getPagoById(long pagoId);
	Pago addPago(Pago pago);
	Pago updatePago(Pago pago);
	void deletePago(long pagoId);
	boolean existe(Pago pago);

}
