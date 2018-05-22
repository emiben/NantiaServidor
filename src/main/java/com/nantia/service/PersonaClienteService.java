package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Cliente;
import com.nantia.model.Empresa;
import com.nantia.model.Persona;
import com.nantia.repo.PersonaClienteRepository;

@Service
public class PersonaClienteService implements IPersonaClienteService{
	
	@Autowired
	private PersonaClienteRepository personaClienteRepository;

	
	private Persona findByNroDocumento(String nroDocumento) {
		Persona cli = personaClienteRepository.findByNroDocumento(nroDocumento);
		return cli;
	}
	
	
	@Override
	public List<Persona> getAllPersonas() {
		List<Persona> list = new ArrayList<>();		
		personaClienteRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Persona getPersonaById(long personaId) {
		Persona per = personaClienteRepository.findOne(personaId);
		return per;
	}

	@Override
	public Persona addPersona(Persona persona) {
		return personaClienteRepository.save(persona);
	}

	@Override
	public Persona updatePersona(Persona persona) {
		return personaClienteRepository.save(persona);
	}

	@Override
	public void deletePersona(long personaId) {
		personaClienteRepository.delete(getPersonaById(personaId));		
	}

	@Override
	public boolean existe(Persona persona) {
		return findByNroDocumento(persona.getNroDocumento()) != null;
	}

}
