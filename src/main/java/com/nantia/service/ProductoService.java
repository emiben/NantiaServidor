package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Producto;
import com.nantia.repo.ProductoRepository;

@Service
public class ProductoService implements IProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> getAllProductos() {
		List<Producto> list = new ArrayList<>();
		productoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Producto getProductoById(long productoId) {
		Producto prod = productoRepository.findOne(productoId);
		return prod;
	}

	@Override
	public Producto addProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void deleteProducto(long productoId) {
		productoRepository.delete(productoId);
	}

	@Override
	public boolean existe(Producto producto) {
		return productoRepository.findByNombre(producto.getNombre())
				!= null;
	}

}
