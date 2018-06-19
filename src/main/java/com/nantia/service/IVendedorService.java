package com.nantia.service;

import java.util.List;

import com.nantia.model.Vendedor;


public interface IVendedorService extends IUsuarioService {
	List<Vendedor> getAllVendedores();
	Vendedor getVendedorById(long vendedorId);
	Vendedor addVendedor(Vendedor vendedor);
	Vendedor updateVendedor(Vendedor vendedor);
	void deleteVendedor(long vendedorId);
	boolean existe(Vendedor vendedor);
}
