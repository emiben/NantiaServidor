package com.nantia.service;

import java.util.List;

import com.nantia.model.Reparto;

public interface IRepartoService {
	
	List<Reparto> getAllReparto();
	Reparto getRepartoById(long repartoId);
	Reparto addReparto(Reparto reparto);
	Reparto updateReparto(Reparto reparto);
	void deleteReparto(long repartoId);
	boolean existe(Reparto reparto);

}
