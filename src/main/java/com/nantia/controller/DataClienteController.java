package com.nantia.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.nantia.model.Cliente;
import com.nantia.model.DataCliente;
import com.nantia.model.DataEnvasesEnPrestamo;
import com.nantia.model.DiaSemana;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.model.Venta;
import com.nantia.service.IClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/dataclientes")
public class DataClienteController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	IClienteService clienteService;

	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody DataCliente dataCliente) {
		LOG.info("actualizando cliente: {}", dataCliente);
		Cliente cliente = clienteService.getClienteById(id);

        if (cliente == null){
            LOG.info("Cliente con id {} no encontrado", id);
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }

		float saldoOld = cliente.getSaldo();
		
		
		cliente.setNombre1(dataCliente.getNombre1());
		cliente.setNombre2(dataCliente.getNombre2());
		cliente.setActivo(dataCliente.getActivo());
		cliente.setCelular(dataCliente.getCelular());
		cliente.setDireccion(dataCliente.getDireccion());
		cliente.setFechaAlta(dataCliente.getFechaAlta());
		cliente.setFechaNacimiento(dataCliente.getFechaNacimiento());
		cliente.setMail(dataCliente.getMail());		
		cliente.setSaldo(saldoOld + dataCliente.getDifSaldo());
		cliente.setTipoDocumento(dataCliente.getTipoDocumento());
		cliente.setNroDocumento(dataCliente.getNroDocumento());
		cliente.setIdLista(dataCliente.getIdLista());
		cliente.setObservaciones(dataCliente.getObservaciones());
		
		cliente.getSetEnvasesEnPrestamo().clear();
		
        Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = dataCliente.getSetEnvasesEnPrestamo();						
		Iterator<EnvasesEnPrestamo> iteEnvases = dataCliente.getSetEnvasesEnPrestamo().iterator();
	    while(iteEnvases.hasNext()) {
	    	EnvasesEnPrestamo envaseEnPrestamo = iteEnvases.next();
	    	envaseEnPrestamo.setClientes(cliente);
	    	setEnvasesEnPrestamo.add(envaseEnPrestamo);
	    }		
	    //dataCliente.getSetEnvasesEnPrestamo().clear();
	    cliente.getSetEnvasesEnPrestamo().retainAll(setEnvasesEnPrestamo);
		cliente.getSetEnvasesEnPrestamo().addAll(setEnvasesEnPrestamo);
		
		cliente.setDias(dataCliente.getDias());

        Cliente clienteUpd = clienteService.updateCliente(cliente);
        return new ResponseEntity<Cliente>(clienteUpd, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/clientespordia/{fecha}", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAllClientesPorDia(@PathVariable String fecha) {
		
		LOG.info("trayendo todos los clientes para la fecha indicada"); 
		LOG.info("fecha: {}", fecha); 
		
		List<Cliente> clientes;
		
		clientes = clienteService.getAllClientesPorDia(fecha);

        if (clientes == null || clientes.isEmpty()){
            LOG.info("no se encontraron clientes para la fecha indicada");
            return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);		
	}

	@RequestMapping(value = "cuentasacobrar/{cliente}", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getCuentasACobrar(@PathVariable("cliente") long cliente) {
		
		LOG.info("trayendo todas los clientes con saldo"); 
		
		List<Cliente> listCliente;
		
		listCliente = clienteService.getCuentasACobrar(cliente);
	
	    if (listCliente == null || listCliente.isEmpty()){
	        LOG.info("no se encontraron clientes con saldo");
	        return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
	    }
	
	    return new ResponseEntity<List<Cliente>>(listCliente, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "envasesenprestamo/{cliente}", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getEnvasesEnPrestamo(@PathVariable("cliente") long cliente) {
		
		LOG.info("trayendo todas los envases en prestamo"); 
		
		List<Cliente> listObject = clienteService.getEnvasesEnPrestamo(cliente);
		
		if (listObject == null || listObject.isEmpty()){
	        LOG.info("no se encontraron envases en prestamo");
	        return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
	    }
		/*
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
		    	envaseEnPrestamo.setNombre2(result[1].toString());
		    	envaseEnPrestamo.setDescripcion(result[2].toString());
		    	envaseEnPrestamo.setCantidad(Integer.parseInt(result[3].toString()));
		    	listEnvases.add(envaseEnPrestamo);
		    }	
	    }
	    */
	    return new ResponseEntity<List<Cliente>>(listObject, HttpStatus.OK);		
	}
}
