package com.nantia.service;

import java.util.List;

import com.nantia.model.Fabrica;

public interface IFabricaService {
	
	List<Fabrica> getAllFabrica();
	Fabrica getFabricaById(long fabricaId);
	Fabrica addFabrica(Fabrica fabrica);
	Fabrica updateFabrica(Fabrica fabrica);
	void deleteFabrica(long fabricaId);
	boolean existe(Fabrica fabrica);

}
