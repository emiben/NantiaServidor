package com.nantia.service;

import java.util.List;

import com.nantia.model.Persona;

public interface IPersonaClienteService {
	List<Persona> getAllPersonas();
	Persona getPersonaById(long personaId);
	Persona addPersona(Persona persona);
	Persona updatePersona(Persona persona);
	void deletePersona(long personaId);
	boolean existe(Persona persona);

}
