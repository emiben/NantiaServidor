package com.nantia.service;

import java.util.List;

import com.nantia.model.Empresa;

public interface IEmpresaClienteService {
	List<Empresa> getAllEmpresas();
	Empresa getEmpresaById(long empresaId);
	Empresa addEmpresa(Empresa empresa);
	Empresa updateEmpresa(Empresa empresa);
	void deleteEmpresa(long empresaId);
	boolean existe(Empresa empresa);

}
