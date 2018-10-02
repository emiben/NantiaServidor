package com.nantia.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.nantia.model.Cliente;
import com.nantia.model.DiaSemana;
import com.nantia.model.Vehiculo;

public interface IClienteService {
	List<Cliente> getAllClientes();
	Cliente getClienteById(long clienteId);
	Cliente addCliente(Cliente cliente);
	Cliente updateCliente(Cliente cliente);
	void deleteCliente(long clienteId);
	boolean existe(Cliente cliente);
	List<Cliente> getAllClientesPorDia(String fecha);
}
