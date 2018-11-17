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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.DataEnvasesEnPrestamo;
import com.nantia.service.IFabricaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/datafabrica")
public class DataFabricaController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	IFabricaService fabricaService;

	
	@RequestMapping(value = "envasesenstock/{fabrica}", method = RequestMethod.GET)
	public ResponseEntity<List<DataEnvasesEnPrestamo>> getEnvasesEnPrestamo(@PathVariable("fabrica") long fabrica) {
		
		LOG.info("trayendo todas los envases en prestamo"); 
		
		List<Object> listObject;
		listObject = fabricaService.getEnvasesEnPrestamo(fabrica);
		
		List<DataEnvasesEnPrestamo> listEnvases = new ArrayList<>();		
	
	    if (listObject == null || listObject.isEmpty()){
	        LOG.info("no se encontraron envases en prestamo");
	        return new ResponseEntity<List<DataEnvasesEnPrestamo>>(HttpStatus.NO_CONTENT);
	    }
	    else {
	    	
	    	Iterator<Object> iteEnvases = listObject.iterator();
		    while(iteEnvases.hasNext()) {
		    	
		    	Object[] result= (Object[]) iteEnvases.next();
		    	DataEnvasesEnPrestamo envaseEnPrestamo = new DataEnvasesEnPrestamo();
		    	envaseEnPrestamo.setNombre1(result[0].toString());
		    	//envaseEnPrestamo.setNombre2(result[1].toString());
		    	envaseEnPrestamo.setDescripcion(result[2].toString());
		    	envaseEnPrestamo.setCantidad((int)Double.parseDouble(result[3].toString()));
		    	listEnvases.add(envaseEnPrestamo);
		    }	
	    }
	
	    return new ResponseEntity<List<DataEnvasesEnPrestamo>>(listEnvases, HttpStatus.OK);		
	}
	
	
}
