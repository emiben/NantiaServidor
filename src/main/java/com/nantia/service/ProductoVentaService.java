package com.nantia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.ProductoVenta;
import com.nantia.repo.ProductoVentaRepository;

@Service
public class ProductoVentaService implements IProductoVentaService {
	
	@Autowired
	private ProductoVentaRepository productoVentaRepository;

	@Override
	public List<ProductoVenta> getAllProductoVenta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoVenta getProductoVentaById(long productoVentaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoVenta addProductoVenta(ProductoVenta productoVenta) {
		return productoVentaRepository.save(productoVenta);
	}

	@Override
	public ProductoVenta updateProductoVenta(ProductoVenta productoVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductoVenta(long productoVentaId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existe(ProductoVenta productoVenta) {
		// TODO Auto-generated method stub
		return false;
	}

}
