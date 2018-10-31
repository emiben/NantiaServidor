package com.nantia.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

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
import com.nantia.model.Pago;
import com.nantia.service.IPagoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/pagos")
public class PagoController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	IPagoService pagoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pago>> getAllPagos() {
		LOG.info("trayendo todos los pagos"); 
        List<Pago> pagos = pagoService.getAllPagos();
        
        if (pagos == null || pagos.isEmpty()){
            LOG.info("no se encontraron pagos");
            return new ResponseEntity<List<Pago>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Pago>>(pagos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Pago> getPagoById(@PathVariable("id") Integer id) {
		LOG.info("trayendo pago por id: {}", id);
		Pago pagos = pagoService.getPagoById(id);

        if (pagos == null){
            LOG.info("pago por id {} no encontrado", id);
            return new ResponseEntity<Pago>(HttpStatus.NOT_FOUND);
        }
       
        return new ResponseEntity<Pago>(pagos, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pago> addPago(@RequestBody Pago pago) {
		LOG.info("creando un nuevo pago: {}", pago.getId());
	
	    if (pagoService.existe(pago)){
            LOG.info("el pago con id " + pago.getId() + " ya existe");
            return new ResponseEntity<Pago>(HttpStatus.CONFLICT);
        }

	    Pago newPago = pagoService.addPago(pago);

        return new ResponseEntity<Pago>(newPago, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pago> updatePago(@PathVariable int id, @RequestBody Pago pago) {
		LOG.info("actualizando Pago: {}", pago);
		Pago currentPago = pagoService.getPagoById(id);

        if (currentPago == null){
            LOG.info("Pago con id {} no encontrado", id);
            return new ResponseEntity<Pago>(HttpStatus.NOT_FOUND);
        }
        
       		
        Pago pagoUpd = pagoService.updatePago(pago);
        return new ResponseEntity<Pago>(pagoUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePago(@PathVariable("id") int id){
        LOG.info("Eliminando pago con id: {}", id);
        Pago pago = pagoService.getPagoById(id);

        if (pago == null){
            LOG.info("No se puede eliminar. Pago con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        pagoService.deletePago(id);
        LOG.info("Pago con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
