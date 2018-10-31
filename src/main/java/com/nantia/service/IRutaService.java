package com.nantia.service;

import java.util.List;

import com.nantia.model.Ruta;

public interface IRutaService {

	List<Ruta> getAllRuta();
	Ruta getRutaById(long rutaId);
	Ruta addRuta(Ruta ruta);
	Ruta updateRuta(Ruta ruta);
	void deleteRuta(long rutaId);
	boolean existe(Ruta ruta);
}
