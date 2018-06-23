package com.nantia.service;

import java.util.List;

import com.nantia.model.Cliente;
import com.nantia.model.EnvasesEnPrestamo;;

public interface IEnvasesEnPrestamoService {
	List<EnvasesEnPrestamo> getAllEnvasesEnPrestamo();
	List<EnvasesEnPrestamo> getEnvasesEnPrestamoByCliente(Cliente cliente);
	EnvasesEnPrestamo getEnvasesEnPrestamoById(long envasesEnPrestamoId);
	EnvasesEnPrestamo addEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo);
	EnvasesEnPrestamo updateEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo);
	void deleteEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo);
	boolean existe(EnvasesEnPrestamo envasesEnPrestamo);

}
