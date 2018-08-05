package com.nantia.service;

import java.util.List;

import com.nantia.model.Stock;

public interface IStockService {

	List<Stock> getAllStock();
	Stock getStockById(long stockId);
	Stock addStock(Stock stock);
	Stock updateStock(Stock stock);
	void deleteStock(long stockId);
	boolean existe(Stock stock);
}
