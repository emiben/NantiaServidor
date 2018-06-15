package com.nantia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Rol;
import com.nantia.model.Usuario;
import com.nantia.service.IRolService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/roles")
public class RolController {
	
	private final Logger LOG = LoggerFactory.getLogger(RolController.class);
	
	@Autowired
	IRolService rolService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Rol>> getAllRoles() {
		LOG.info("getting all roles");
        List<Rol> roles = rolService.getAllRoles();

        if (roles == null || roles.isEmpty()){
            LOG.info("no se encontraron usuarios");
            return new ResponseEntity<List<Rol>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Rol>>(roles, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Rol> getRolById(@PathVariable("id") Long id) {
		LOG.info("getting rol with id: {}", id);
        Rol rol = rolService.getRolById(id);

        if (rol == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Rol> addRol(@RequestBody Rol rol) {
		LOG.info("creating new rol: {}", rol);

        if (rolService.existe(rol)){
            LOG.info("a user with name " + rol.getNombreRol() + " already exists");
            return new ResponseEntity<Rol>(HttpStatus.CONFLICT);
        }

        Rol newRol = rolService.addRol(rol);

        return new ResponseEntity<Rol>(newRol, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Rol> updateRol(@PathVariable int id, @RequestBody Rol rol) {
		LOG.info("updating rol: {}", rol);
        Rol currentRol = rolService.getRolById(id);

        if (currentRol == null){
            LOG.info("Rol with id {} not found", id);
            return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
        }

        Rol rolUpd = rolService.updateRol(rol);
        return new ResponseEntity<Rol>(rolUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRol(@PathVariable("id") long id){
        LOG.info("deleting rol with id: {}", id);
        Rol rol = rolService.getRolById(id);

        if (rol == null){
            LOG.info("Unable to delete. Rol with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        rolService.deleteRol(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
