package com.nantia.service;

import java.util.List;

import com.nantia.model.Cliente;
import com.nantia.model.DiaSemana;;

public interface IDiaSemanaService {
	List<DiaSemana> getAllDiaSemana();
	DiaSemana getDiaSemanaById(long diaSemanaId);
	DiaSemana addDiaSemana(DiaSemana diaSemana, Cliente cliente);
	DiaSemana updateDiaSemana(DiaSemana diaSemana);
	void deleteDiaSemana(long clienteId);
	boolean existe(DiaSemana diaSemana);

}
