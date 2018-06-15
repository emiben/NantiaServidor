package com.nantia.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Cliente;
import com.nantia.model.DataCliente;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.service.IClienteService;
import com.nantia.service.IEnvasesEnPrestamoService;

@RestController
@RequestMapping("api/datacliente")
public class DataClienteController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	IClienteService clienteService;
	@Autowired
	IEnvasesEnPrestamoService envasesEnPrestamoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DataCliente>> getAllDataCliente() {
		LOG.info("trayendo todos los dataCliente");
        List<Cliente> clientes = clienteService.getAllClientes();
        List<DataCliente> listDataCliente = new ArrayList<>();
        
        
        
        if (clientes == null || clientes.isEmpty()){
            LOG.info("no se encontraron dataCliente");
            return new ResponseEntity<List<DataCliente>>(HttpStatus.NO_CONTENT);
        }
        else
        {
        	Iterator<Cliente> cli = clientes.iterator();
        	while(cli.hasNext()){
        		
        		
        		Cliente cliente=cli.next();        		
        		DataCliente dataCliente = new DataCliente();
        		
        		dataCliente.setActivo(cliente.getActivo());
        		dataCliente.setCelular(cliente.getCelular());
        		dataCliente.setClienteId(cliente.getClienteId());
        		dataCliente.setDireccion(cliente.getDireccion());
        		dataCliente.setFechaAlta(cliente.getFechaAlta());
        		dataCliente.setFechaNacimiento(cliente.getFechaNacimiento());
        		dataCliente.setIdLista(cliente.getIdLista());
        		dataCliente.setMail(cliente.getMail());
        		dataCliente.setNombre1(cliente.getNombre1());
        		dataCliente.setNombre2(cliente.getNombre2());
        		dataCliente.setNroDocumento(cliente.getNroDocumento());
        		dataCliente.setObservaciones(cliente.getNroDocumento());
        		dataCliente.setSaldo(cliente.getSaldo());
        		dataCliente.setTipoDocumento(cliente.getTipoDocumento());
        		dataCliente.setDias(cliente.getDias());
        		

        		//Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = cliente.getSetEnvasesEnPrestamo();
        		Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = envasesEnPrestamoService.getEnvasesEnPrestamoByCliente(cliente).stream().collect(Collectors.toSet());
        		
        		SortedMap<String, Integer> cantEnvases = new TreeMap<String, Integer>();
        		Iterator<EnvasesEnPrestamo> env = setEnvasesEnPrestamo.iterator();
            	while(env.hasNext()){
            		EnvasesEnPrestamo envasesEnPrestamo = env.next();
            		
            		cantEnvases.put(envasesEnPrestamo.getEnvasetipos().getDescripcion(), envasesEnPrestamo.getCantidad());
            		LOG.info("Descripcion: {}, cantidad: {}", envasesEnPrestamo.getEnvasetipos().getDescripcion(), envasesEnPrestamo.getCantidad());
            		//dataCliente.setEnvases(cantEnvases);
            	}
            	
            	listDataCliente.add(dataCliente);
        	}
        	
        	
        	
        	return new ResponseEntity<List<DataCliente>>(listDataCliente, HttpStatus.OK);
        }

        
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<DataCliente> getDataClienteById(@PathVariable("id") Integer id) {
		LOG.info("trayendo dataCliente por id {}", id);
        Cliente cliente = clienteService.getClienteById(id);
        DataCliente dataCliente = new DataCliente();
        SortedMap<String, Integer> cantEnvases = new TreeMap<String, Integer>();
        
        if (cliente == null){
            LOG.info("no se encontraron dataCliente");
            return new ResponseEntity<DataCliente>(HttpStatus.NO_CONTENT);
        }
        else
        {
    		dataCliente.setActivo(cliente.getActivo());
    		dataCliente.setCelular(cliente.getCelular());
    		dataCliente.setClienteId(cliente.getClienteId());
    		dataCliente.setDireccion(cliente.getDireccion());
    		dataCliente.setFechaAlta(cliente.getFechaAlta());
    		dataCliente.setFechaNacimiento(cliente.getFechaNacimiento());
    		dataCliente.setIdLista(cliente.getIdLista());
    		dataCliente.setMail(cliente.getMail());
    		dataCliente.setNombre1(cliente.getNombre1());
    		dataCliente.setNombre2(cliente.getNombre2());
    		dataCliente.setNroDocumento(cliente.getNroDocumento());
    		dataCliente.setObservaciones(cliente.getObservaciones());
    		dataCliente.setSaldo(cliente.getSaldo());
    		dataCliente.setTipoDocumento(cliente.getTipoDocumento());
    		dataCliente.setDias(cliente.getDias());
    		
    		Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = envasesEnPrestamoService.getEnvasesEnPrestamoByCliente(cliente).stream().collect(Collectors.toSet());
    		//Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = Sets.newHashSet(envasesEnPrestamoService.getEnvasesEnPrestamoByCliente(cliente));
    				//cliente.getSetEnvasesEnPrestamo();
    		
    		Iterator<EnvasesEnPrestamo> env = setEnvasesEnPrestamo.iterator();
        	while(env.hasNext()){
        		EnvasesEnPrestamo envasesEnPrestamo = env.next();
        		cantEnvases.put(envasesEnPrestamo.getEnvasetipos().getDescripcion(), envasesEnPrestamo.getCantidad());
        		LOG.info("Descripcion: {}, cantidad: {}", envasesEnPrestamo.getEnvasetipos().getDescripcion(), envasesEnPrestamo.getCantidad());
        	}
        	dataCliente.setSetEnvases(cantEnvases);
        	
        	return new ResponseEntity<DataCliente>(dataCliente, HttpStatus.OK);
        }
	}

}
