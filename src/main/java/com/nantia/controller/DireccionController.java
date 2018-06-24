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

import com.nantia.model.Direccion;
import com.nantia.service.IDireccionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/direcciones")
public class DireccionController {
	
private final Logger LOG = LoggerFactory.getLogger(DireccionController.class);
	
	@Autowired
	IDireccionService direccionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Direccion>> getAllDirecciones() {
		LOG.info("trayendo todos las direcciones");
        List<Direccion> direccion = direccionService.getAllDirecciones();
        
        if (direccion == null || direccion.isEmpty()){
            LOG.info("no se encontraron direcciones");
            return new ResponseEntity<List<Direccion>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Direccion>>(direccion, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Direccion> getDireccionById(@PathVariable("id") Integer id) {
		LOG.info("trayendo direcciones por id: {}", id);
		Direccion direccion = direccionService.getDireccionById(id);

        if (direccion == null){
            LOG.info("Direccion por id {} no encontrada", id);
            return new ResponseEntity<Direccion>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Direccion>(direccion, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Direccion> addDireccion(@RequestBody Direccion direccion) {
		LOG.info("creando un nuevo direccion: {}", direccion);

        if (direccionService.existe(direccion)){
            LOG.info("el direccion: " + direccion.getDireccion() + " ya existe");
            return new ResponseEntity<Direccion>(HttpStatus.CONFLICT);
        }

        Direccion newDireccion = direccionService.addDireccion(direccion);

        return new ResponseEntity<Direccion>(newDireccion, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Direccion> updateDireccion(@PathVariable int id, @RequestBody Direccion direccion) {
		LOG.info("actualizando direccion: {}", direccion);
		Direccion currentDireccion = direccionService.getDireccionById(id);

        if (currentDireccion == null){
            LOG.info("Direccion con id {} no encontrado", id);
            return new ResponseEntity<Direccion>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentDireccion.getId());
        Direccion direccionUpd = direccionService.updateDireccion(direccion);
        return new ResponseEntity<Direccion>(direccionUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDireccion(@PathVariable("id") int id){
        LOG.info("Eliminando direccion con id: {}", id);
        Direccion direccion = direccionService.getDireccionById(id);

        if (direccion == null){
            LOG.info("No se puede eliminar. Direccion con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        direccionService.deleteDireccion(id);
        LOG.info("Direccion con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
