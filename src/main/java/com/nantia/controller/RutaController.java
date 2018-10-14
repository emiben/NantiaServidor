package com.nantia.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.nantia.model.Ruta;
import com.nantia.model.RutaCliente;
import com.nantia.service.IRutaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/ruta")
public class RutaController {
	
private final Logger LOG = LoggerFactory.getLogger(RutaController.class);
	
	@Autowired
	IRutaService rutaService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Ruta>> getAllRuta() {
		LOG.info("trayendo todas las rutas"); 
        List<Ruta> ruta = rutaService.getAllRuta();
        
        if (ruta == null || ruta.isEmpty()){
            LOG.info("no se encontraron rutas");
            return new ResponseEntity<List<Ruta>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Ruta>>(ruta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Ruta> getRutaById(@PathVariable("id") Integer id) {
		LOG.info("trayendo ruta por id: {}", id);
		Ruta ruta = rutaService.getRutaById(id);

		 if (ruta == null){
	            LOG.info("ruta con id  {} no encontrado", id);
	            return new ResponseEntity<Ruta>(HttpStatus.NOT_FOUND);
	        }
	        
        return new ResponseEntity<Ruta>(ruta, HttpStatus.OK);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Ruta> addRuta(@RequestBody Ruta ruta) {
		LOG.info("creando ruta: {}", ruta);
				
		Set<RutaCliente> setRutaCliente =  ruta.getSetRutaCliente();						
		Iterator<RutaCliente> iteRutCli = ruta.getSetRutaCliente().iterator();
	    while(iteRutCli.hasNext()) {
	    	RutaCliente rutaCliente = iteRutCli.next();
	    	rutaCliente.setRuta(ruta);
	    	setRutaCliente.add(rutaCliente);
	    	//LOG.info("getOrdenVisita {}", rutaCliente.getOrdenVisita());
	    }		
	    ruta.getSetRutaCliente().retainAll(setRutaCliente);
	    ruta.getSetRutaCliente().addAll(setRutaCliente);
	    //ruta.setSetRutaCliente(setRutaCliente);    
	    
		if (rutaService.existe(ruta)){
            LOG.info("La ruta con nombre " + ruta.getNombre() + " ya existe");
            return new ResponseEntity<Ruta>(HttpStatus.CONFLICT);
        }
			
		Ruta newRuta = rutaService.addRuta(ruta);
        
        return new ResponseEntity<Ruta>(newRuta, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Ruta> updateRuta(@PathVariable int id, @RequestBody Ruta ruta) {
		LOG.info("Actualizando ruta: {}", ruta);
		Ruta currentRuta = rutaService.getRutaById(id);

		Set<RutaCliente> setRutaCliente =  ruta.getSetRutaCliente();						
		Iterator<RutaCliente> iteRutCli = ruta.getSetRutaCliente().iterator();
	    while(iteRutCli.hasNext()) {
	    	RutaCliente rutaCliente = iteRutCli.next();
	    	rutaCliente.setRuta(ruta);
	    	setRutaCliente.add(rutaCliente);
	    }		
	    ruta.setSetRutaCliente(setRutaCliente);     
	    
        if (currentRuta == null){
            LOG.info("Ruta con id {} no encontrado", id);
            return new ResponseEntity<Ruta>(HttpStatus.NOT_FOUND);
        }

        Ruta rutaUpd = rutaService.updateRuta(ruta);
        return new ResponseEntity<Ruta>(rutaUpd, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRuta(@PathVariable("id") int id){
        LOG.info("Eliminando ruta con id: {}", id);
        Ruta ruta = rutaService.getRutaById(id);

        if (ruta == null){
            LOG.info("No se puede eliminar. Ruta con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        rutaService.deleteRuta(id);
        LOG.info("Ruta con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
