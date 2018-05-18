package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.controller.ClienteController;
import com.nantia.model.Cliente;
import com.nantia.repo.ClienteRepository;

@Service
public class ClienteService implements IClienteService{
	
	//private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	private Cliente findByTipoDocumento(String tipoDocumento) {
		Cliente cli = clienteRepository.findByTipoDocumento(tipoDocumento);
		return cli;
	}

	@Override
	public List<Cliente> getAllClientes() {
		//LOG.info("getAllClientes");
		List<Cliente> list = new ArrayList<>();		
		clienteRepository.findAll().forEach(e -> list.add(e));
		//LOG.info("size: " + list.size());
		return list;
	}

	@Override
	public Cliente getClienteById(long clienteId) {
		Cliente cli = clienteRepository.findOne(clienteId);
		return cli;
	}

	@Override
	public Cliente addCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deleteCliente(long clienteId) {
		clienteRepository.delete(getClienteById(clienteId));		
	}

	@Override
	public boolean existe(Cliente cliente) {
		return findByTipoDocumento(cliente.getTipoDocumento()) != null;
	}

}
