package com.nantia.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Cliente;
import com.nantia.model.DataReparto;
import com.nantia.model.Reparto;
import com.nantia.service.IClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/dataclientespordia")
public class DataClientePorDiaController {

	
	private final Logger LOG = LoggerFactory.getLogger(DataRepartoController.class);
	
	@Autowired
	IClienteService clienteService;

	/*
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAllataClientePorDia() {
		LOG.info("trayendo todos los DataReparto");

		List<Cliente> listCliente = clienteService.getAllClientes();
        List<DataReparto> listDataReparto = new ArrayList<DataReparto>();
        
        DataReparto dataReparto;
        
        if (listReparto == null || listReparto.isEmpty()){
            LOG.info("no se encontraron repartos");
            return new ResponseEntity<List<DataReparto>>(HttpStatus.NO_CONTENT);
        }
                				
		Iterator<Reparto> iteRep = listReparto.iterator();
	    while(iteRep.hasNext()) {
	    	Reparto reparto = iteRep.next();
	    	dataReparto = new DataReparto();
	    	
	    	dataReparto.setIdReparto(reparto.getId());
	    	dataReparto.setDescripcion(reparto.getDescripcion());
	    	dataReparto.setDescripcionVehiculo(reparto.getVehiculo().getDescripcion());
	    	dataReparto.setDias(reparto.getRuta().getDias());
	    	
	    	listDataReparto.add(dataReparto);
	    }		
		
        return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
*/
}
