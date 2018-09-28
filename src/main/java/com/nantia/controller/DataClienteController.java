package com.nantia.controller;

import java.util.Iterator;
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

import com.nantia.model.Cliente;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.service.IClienteService;
import com.nantia.service.IEnvasesEnPrestamoService;
import com.nantia.service.IEnvasesTipoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/dataclientes")
public class DataClienteController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	IClienteService clienteService;

	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
		LOG.info("actualizando cliente: {}", cliente);
		Cliente currentCliente = clienteService.getClienteById(id);

        if (currentCliente == null){
            LOG.info("Cliente con id {} no encontrado", id);
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }

		float saldoOld = currentCliente.getSaldo();
		cliente.setSaldo(saldoOld + cliente.getSaldo());
		
        Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = cliente.getSetEnvasesEnPrestamo();						
		Iterator<EnvasesEnPrestamo> iteEnvases = cliente.getSetEnvasesEnPrestamo().iterator();
	    while(iteEnvases.hasNext()) {
	    	EnvasesEnPrestamo envaseEnPrestamo = iteEnvases.next();
	    	envaseEnPrestamo.setClientes(cliente);
	    	setEnvasesEnPrestamo.add(envaseEnPrestamo);
	    }		
		cliente.setSetEnvasesEnPrestamo(setEnvasesEnPrestamo);
		
        Cliente clienteUpd = clienteService.updateCliente(cliente);
        return new ResponseEntity<Cliente>(clienteUpd, HttpStatus.OK);
	}

}
