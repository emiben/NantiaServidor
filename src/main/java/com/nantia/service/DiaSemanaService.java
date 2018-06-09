package com.nantia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.controller.ClienteController;
import com.nantia.model.Cliente;
import com.nantia.model.DiaSemana;
import com.nantia.repo.ClienteRepository;


@Service
public class DiaSemanaService implements IDiaSemanaService{

	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<DiaSemana> getAllDiaSemana() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaSemana getDiaSemanaById(long diaSemanaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaSemana addDiaSemana(DiaSemana diaSemana, Cliente cliente) {
		//return clienteRepository.addDias(diaSemana, cliente);
		return null;
	}

	@Override
	public DiaSemana updateDiaSemana(DiaSemana diaSemana) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDiaSemana(long clienteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existe(DiaSemana diaSemana) {
		// TODO Auto-generated method stub
		return false;
	}

}
