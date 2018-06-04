package com.nantia.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.controller.ClienteController;
import com.nantia.controller.EnvasesEnPrestamoControler;
import com.nantia.model.Cliente;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.repo.ClienteRepository;
import com.nantia.repo.EnvasesEnPrestamoRepository;

@Service
public class ClienteService implements IClienteService{
	
	private final Logger LOG = LoggerFactory.getLogger(EnvasesEnPrestamoControler.class);


	@Autowired
	private ClienteRepository clienteRepository;
	

	private Cliente findByNroDocumento(String nroDocumento) {
		Cliente cli = clienteRepository.findByNroDocumento(nroDocumento);
		return cli;
	}

	@Override
	public List<Cliente> getAllClientes() {
		List<Cliente> list = new ArrayList<>();	
		clienteRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Cliente getClienteById(long clienteId) {
		LOG.info("getClienteById: {}", clienteId);
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
		return findByNroDocumento(cliente.getNroDocumento()) != null;
	}

}
