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

import com.nantia.model.ProductoLista;
import com.nantia.model.Reparto;
import com.nantia.model.Ruta;
import com.nantia.model.RutaCliente;
import com.nantia.service.IRepartoService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/reparto")
public class RepartoController {

private final Logger LOG = LoggerFactory.getLogger(RepartoController.class);
	
	@Autowired
	IRepartoService repartoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reparto>> getAllReparto() {
		LOG.info("trayendo todos los reparto");
        List<Reparto> reparto = repartoService.getAllReparto();
        
        if (reparto == null || reparto.isEmpty()){
            LOG.info("no se encontraron repartos");
            return new ResponseEntity<List<Reparto>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Reparto>>(reparto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Reparto> getRepartoById(@PathVariable("id") Integer id) {
		LOG.info("trayendo repartos por id: {}", id);
		Reparto repartos = repartoService.getRepartoById(id);

        if (repartos == null){
            LOG.info("Reparto por id {} no encontrada", id);
            return new ResponseEntity<Reparto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reparto>(repartos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Reparto> addReparto(@RequestBody Reparto reparto) {
		LOG.info("creando un nuevo reparto: {}", reparto);
		
		//*******
		
		Ruta ruta = reparto.getRuta();
		
		Set<RutaCliente> setRutaCliente =  ruta.getSetRutaCliente();						
		Iterator<RutaCliente> iteRuCli = ruta.getSetRutaCliente().iterator();
	    while(iteRuCli.hasNext()) {
	    	RutaCliente rutaCliente = iteRuCli.next();
	    	rutaCliente.setRuta(ruta);
	    	setRutaCliente.add(rutaCliente);
	    }		
	    ruta.setSetRutaCliente(setRutaCliente);
		reparto.setRuta(ruta);
		
		//*******

        if (repartoService.existe(reparto)){
            LOG.info("el reparto: " + reparto.getDescripcion() + " ya existe");
            return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);
        }

        Reparto newReparto = repartoService.addReparto(reparto);

        return new ResponseEntity<Reparto>(newReparto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Reparto> updateReparto(@PathVariable int id, @RequestBody Reparto reparto) {
		LOG.info("actualizando Reparto: {}", reparto);
		Reparto currentReparto = repartoService.getRepartoById(id);

        if (currentReparto == null){
            LOG.info("Reparto con id {} no encontrado", id);
            return new ResponseEntity<Reparto>(HttpStatus.NOT_FOUND);
        }
        Reparto repartoUpd = repartoService.updateReparto(reparto);
        return new ResponseEntity<Reparto>(repartoUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReparto(@PathVariable("id") int id){
        LOG.info("Eliminando reparto con id: {}", id);
        Reparto reparto = repartoService.getRepartoById(id);

        if (reparto == null){
            LOG.info("No se puede eliminar. Reparto con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        repartoService.deleteReparto(id);
        LOG.info("Reparto con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
