package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Cliente;
import com.nantia.model.Empresa;
import com.nantia.repo.EmpresaClienteRepository;


@Service
public class EmpresaClienteService implements IEmpresaClienteService{

	@Autowired
	private EmpresaClienteRepository empresaClienteRepository;
	
	private Empresa findByNroDocumento(String nroDocumento) {
		Empresa emp = empresaClienteRepository.findByNroDocumento(nroDocumento);
		return emp;
	}
	
	@Override
	public List<Empresa> getAllEmpresas() {
		List<Empresa> list = new ArrayList<>();		
		empresaClienteRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Empresa getEmpresaById(long empresaId) {
		Empresa emp = empresaClienteRepository.findOne(empresaId);
		return emp;
	}

	@Override
	public Empresa addEmpresa(Empresa empresa) {
		return empresaClienteRepository.save(empresa);
	}

	@Override
	public Empresa updateEmpresa(Empresa empresa) {
		return empresaClienteRepository.save(empresa);
	}

	@Override
	public void deleteEmpresa(long empresaId) {
		empresaClienteRepository.delete(getEmpresaById(empresaId));
		
	}

	@Override
	public boolean existe(Empresa empresa) {
		return findByNroDocumento(empresa.getNroDocumento()) != null;
	}

}
