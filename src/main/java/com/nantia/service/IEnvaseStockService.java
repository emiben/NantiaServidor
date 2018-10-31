package com.nantia.service;

import java.util.List;

import com.nantia.model.EnvaseStock;

public interface IEnvaseStockService {
	
	List<EnvaseStock> getAllEnvaseStock();
	EnvaseStock getEnvaseStockById(long envaseStockId);
	EnvaseStock addEnvaseStock(EnvaseStock envaseStock);
	EnvaseStock updateEnvaseStock(EnvaseStock envaseStock);
	void deleteEnvaseStock(long envaseStockId);
	boolean existe(EnvaseStock envaseStock);

}
