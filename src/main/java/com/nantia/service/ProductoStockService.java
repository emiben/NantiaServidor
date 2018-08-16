package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.ProductoStock;
import com.nantia.repo.ProductoStockRepository;

@Service
public class ProductoStockService implements IProductoStockService {
	
	@Autowired
	private ProductoStockRepository productoStockRepository;
	
	private ProductoStock findById(long id) {
		ProductoStock productoStock = productoStockRepository.findById(id);
		return productoStock;
	}

	@Override
	public List<ProductoStock> getAllProductoStock() {
		List<ProductoStock> list = new ArrayList<>();	
		productoStockRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public ProductoStock getProductoStockById(long productoStockId) {
		ProductoStock productoStock = productoStockRepository.findOne(productoStockId);
		return productoStock;
	}

	@Override
	public ProductoStock addProductoStock(ProductoStock productoStock) {
		return productoStockRepository.save(productoStock);
	}

	@Override
	public ProductoStock updateProductoStock(ProductoStock productoStock) {
		return productoStockRepository.save(productoStock);
	}

	@Override
	public void deleteProductoStock(long productoStockId) {
		productoStockRepository.delete(getProductoStockById(productoStockId));

	}

	@Override
	public boolean existe(ProductoStock productoStock) {
		return findById(productoStock.getId()) != null;
	}

}
