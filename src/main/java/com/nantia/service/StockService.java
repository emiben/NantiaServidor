package com.nantia.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nantia.model.Stock;
import com.nantia.repo.StockRepository;

@Service
public class StockService implements IStockService{
	
	@Autowired
	private StockRepository stockRepository;
	
	private Stock findById(long id) {
		Stock stock = stockRepository.findById(id);
		return stock;
	}

	@Override
	public List<Stock> getAllStock() {
		List<Stock> list = new ArrayList<>();	
		stockRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Stock getStockById(long stockId) {
		Stock stock = stockRepository.findOne(stockId);
		return stock;
	}

	@Override
	public Stock addStock(Stock stock) {
		return stockRepository.save(stock);
	}

	@Override
	public Stock updateStock(Stock stock) {
		return stockRepository.save(stock);
	}

	@Override
	public void deleteStock(long stockId) {
		stockRepository.delete(getStockById(stockId));
	}

	@Override
	public boolean existe(Stock stock) {
		return findById(stock.getId()) != null;
	}

}
