package com.nantia.service;

import java.util.List;

import com.nantia.model.ProductoStock;

public interface IProductoStockService {
	List<ProductoStock> getAllProductoStock();
	ProductoStock getProductoStockById(long productoStockId);
	ProductoStock addProductoStock(ProductoStock productoStock);
	ProductoStock updateProductoStock(ProductoStock productoStock);
	void deleteProductoStock(long productoStockId);
	boolean existe(ProductoStock productoStock);

}
