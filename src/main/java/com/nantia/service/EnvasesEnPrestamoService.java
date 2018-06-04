package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.controller.ClienteController;
import com.nantia.model.Cliente;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.model.EnvasesTipos;
import com.nantia.repo.EnvasesEnPrestamoRepository;

@Service
public class EnvasesEnPrestamoService implements IEnvasesEnPrestamoService{

	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
	
	
	@Autowired
	private EnvasesEnPrestamoRepository envasesEnPrestamoRepository;
	
	
	private EnvasesEnPrestamo findByClientesAndEnvasetipos(Cliente clienteId, EnvasesTipos tipoId) {
		//long clienteId, long tipoId
		//LOG.info("buscando envases en prestamo por clienteId: {} y tipoId: {}", clienteId, tipoId);
		EnvasesEnPrestamo env = envasesEnPrestamoRepository.findByClientesAndEnvasetipos(clienteId, tipoId);
		LOG.info("ya se busco el envases en prestamo");
		return env;
	}
	
	@Override
	public List<EnvasesEnPrestamo> getAllEnvasesEnPrestamo() {		
		List<EnvasesEnPrestamo> list = new ArrayList<>();	
		envasesEnPrestamoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<EnvasesEnPrestamo> getEnvasesEnPrestamoByCliente(Cliente cliente) {
		LOG.info("getEnvasesEnPrestamoByCliente {}", cliente.getId());
		List<EnvasesEnPrestamo> list = envasesEnPrestamoRepository.findByClientes(cliente);
		return list;
	}

	@Override
	public EnvasesEnPrestamo getEnvasesEnPrestamoById(long envasesEnPrestamoId) {
		EnvasesEnPrestamo env = envasesEnPrestamoRepository.findOne(envasesEnPrestamoId);
		return env;
	}
	
	@Override
	public EnvasesEnPrestamo addEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo) {
		return envasesEnPrestamoRepository.save(envasesEnPrestamo);
	}

	@Override
	public EnvasesEnPrestamo updateEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo) {
		return envasesEnPrestamoRepository.save(envasesEnPrestamo);
	}

	@Override
	public void deleteEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo) {
		//envasesEnPrestamoRepository.delete(getEnvasesEnPrestamoById(envasesEnPrestamo));
		envasesEnPrestamoRepository.delete(envasesEnPrestamo);
		
	}

	@Override
	public boolean existe(EnvasesEnPrestamo envasesEnPrestamo) {
		LOG.info("buscando envases en prestamo por clienteId: {} y tipoId: {}", envasesEnPrestamo.getClientes().getNombre1(), envasesEnPrestamo.getEnvasetipos().getId());
		return findByClientesAndEnvasetipos(envasesEnPrestamo.getClientes(), envasesEnPrestamo.getEnvasetipos()) != null;
		//return findByClientesAndEnvasetipos(envasesEnPrestamo) != null;
	}

}
