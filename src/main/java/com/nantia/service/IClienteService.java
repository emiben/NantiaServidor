package com.nantia.service;

import java.util.List;
import com.nantia.model.Cliente;

public interface IClienteService {
	List<Cliente> getAllClientes();
	Cliente getClienteById(long clienteId);
	Cliente addCliente(Cliente cliente);
	Cliente updateCliente(Cliente cliente);
	void deleteCliente(long clienteId);
	boolean existe(Cliente cliente);

}
