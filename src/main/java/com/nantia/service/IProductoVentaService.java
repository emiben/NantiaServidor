package com.nantia.service;

import java.util.List;

import com.nantia.model.ProductoVenta;

public interface IProductoVentaService {
	List<ProductoVenta> getAllProductoVenta();
	ProductoVenta getProductoVentaById(long productoVentaId);
	ProductoVenta addProductoVenta(ProductoVenta productoVenta);
	ProductoVenta updateProductoVenta(ProductoVenta productoVenta);
	void deleteProductoVenta(long productoVentaId);
	boolean existe(ProductoVenta productoVenta);

}
