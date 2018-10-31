package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.EnvaseStock;
import com.nantia.model.Stock;
import com.nantia.repo.EnvaseStockRepository;
import com.nantia.repo.StockRepository;

@Service
public class EnvaseStockService implements IEnvaseStockService {
	
	@Autowired
	private EnvaseStockRepository envaseStockRepository;
	
	private EnvaseStock findById(long id) {
		EnvaseStock envaseStock = envaseStockRepository.findById(id);
		return envaseStock;
	}

	@Override
	public List<EnvaseStock> getAllEnvaseStock() {
		List<EnvaseStock> list = new ArrayList<>();	
		envaseStockRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public EnvaseStock getEnvaseStockById(long envaseStockId) {
		EnvaseStock envaseStock = envaseStockRepository.findOne(envaseStockId);
		return envaseStock;
	}

	@Override
	public EnvaseStock addEnvaseStock(EnvaseStock envaseStock) {
		return envaseStockRepository.save(envaseStock);
	}

	@Override
	public EnvaseStock updateEnvaseStock(EnvaseStock envaseStock) {
		return envaseStockRepository.save(envaseStock);
	}

	@Override
	public void deleteEnvaseStock(long envaseStockId) {
		envaseStockRepository.delete(getEnvaseStockById(envaseStockId));

	}

	@Override
	public boolean existe(EnvaseStock envaseStock) {
		return findById(envaseStock.getId()) != null;
	}

}
