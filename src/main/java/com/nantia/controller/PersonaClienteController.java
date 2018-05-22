package com.nantia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Empresa;
import com.nantia.model.Persona;
import com.nantia.service.IPersonaClienteService;

@RestController
@RequestMapping("api/personas")
public class PersonaClienteController {
	
	private final Logger LOG = LoggerFactory.getLogger(PersonaClienteController.class);
	
	@Autowired
	IPersonaClienteService personaClienteService;
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Persona>> getAllEmpresas() {
		LOG.info("trayendo todos las personas");
        List<Persona> personas = personaClienteService.getAllPersonas();
        
        if (personas == null || personas.isEmpty()){
            LOG.info("no se encontraron personas");
            return new ResponseEntity<List<Persona>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Persona> getPersonaById(@PathVariable("id") Integer id) {
		LOG.info("trayendo persona por id: {}", id);
		Persona persona = personaClienteService.getPersonaById(id);

        if (persona == null){
            LOG.info("persona por id {} no encontrado", id);
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
		LOG.info("creando una nueva persona: {}", persona);

        if (personaClienteService.existe(persona)){
            LOG.info("la persona con nombre " + persona.getTipoDocumento() + " ya existe");
            return new ResponseEntity<Persona>(HttpStatus.CONFLICT);
        }

        Persona newPersona = personaClienteService.addPersona(persona);

        return new ResponseEntity<Persona>(newPersona, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Persona> updatePersona(@PathVariable int id, @RequestBody Persona persona) {
		LOG.info("actualizando persona: {}", persona);
		Persona currentPersona = personaClienteService.getPersonaById(id);

        if (currentPersona == null){
            LOG.info("Persona con id {} no encontrada", id);
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentPersona.getId());
        Persona personaUpd = personaClienteService.updatePersona(persona);
        return new ResponseEntity<Persona>(personaUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePersona(@PathVariable("id") int id){
        LOG.info("Eliminando persona con id: {}", id);
        Persona persona = personaClienteService.getPersonaById(id);

        if (persona == null){
            LOG.info("No se puede eliminar. Persona con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        personaClienteService.deletePersona(id);
        LOG.info("Persona con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
