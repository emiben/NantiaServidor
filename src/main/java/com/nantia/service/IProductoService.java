package com.nantia.service;

import java.util.List;

import com.nantia.model.Producto;

public interface IProductoService {
	List<Producto> getAllProductos();
	Producto getProductoById(long productoId);
	Producto addProducto(Producto producto);
	Producto updateProducto(Producto producto);
	void deleteProducto(long productoId);
	boolean existe(Producto producto);
}
