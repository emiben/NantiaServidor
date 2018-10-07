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
import com.nantia.model.Cliente;
import com.nantia.model.Direccion;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.model.EnvasesTipos;
import com.nantia.service.IClienteService;
import com.nantia.service.IEnvasesEnPrestamoService;
import com.nantia.service.IEnvasesTipoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	IClienteService clienteService;
	@Autowired
	IEnvasesEnPrestamoService envasesEnPrestamoService;
	@Autowired
	IEnvasesTipoService envasesTipoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAllClientes() {
		LOG.info("trayendo todos los clientes"); 
        List<Cliente> clientes = clienteService.getAllClientes();
        
        if (clientes == null || clientes.isEmpty()){
            LOG.info("no se encontraron clientes");
            return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
		LOG.info("trayendo cliente por id: {}", id);
		Cliente cliente = clienteService.getClienteById(id);

        if (cliente == null){
            LOG.info("cliente por id {} no encontrado", id);
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
        else
        {
        	LOG.info("cliente: {}", cliente.getId());
        	List<EnvasesEnPrestamo> listEnvasesEnPrestamo = envasesEnPrestamoService.getEnvasesEnPrestamoByCliente(cliente);
        	LOG.info("listEnvasesEnPrestamo2: {}", listEnvasesEnPrestamo.size());
        	if (listEnvasesEnPrestamo == null || listEnvasesEnPrestamo.isEmpty()){
        		LOG.info("el cliente no tiene envases");
        	}
        	else {
        		LOG.info("el cliente tiene {} envases", listEnvasesEnPrestamo.size());
        		
        		for (EnvasesEnPrestamo x : listEnvasesEnPrestamo) {
        			EnvasesTipos envasesTipos = envasesTipoService.getEnvasesTipoById(x.getEnvasetipos().getId());
        			x.setEnvasetipos(envasesTipos);
                    cliente.addEnvasesEnPrestamo(x); 
                    }
        	}
        	
        }	

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
		LOG.info("creando un nuevo cliente: {}", cliente.getNombre1());
	
		if(cliente.getSetEnvasesEnPrestamo() != null)
		{	
			Set<EnvasesEnPrestamo> setEnvasesEnPrestamo = cliente.getSetEnvasesEnPrestamo();						
			Iterator<EnvasesEnPrestamo> iteEnvases = cliente.getSetEnvasesEnPrestamo().iterator();
		    while(iteEnvases.hasNext()) {
		    	EnvasesEnPrestamo envaseEnPrestamo = iteEnvases.next();
		    	envaseEnPrestamo.setClientes(cliente);
		    	setEnvasesEnPrestamo.add(envaseEnPrestamo);
		    }		
			cliente.setSetEnvasesEnPrestamo(setEnvasesEnPrestamo);
			//Direccion direccion = cliente.getDireccion();
			//cliente.setDireccion(direccion);
			
		}
        if (clienteService.existe(cliente)){
            LOG.info("el cliente con documento " + cliente.getNroDocumento() + " ya existe");
            return new ResponseEntity<Cliente>(HttpStatus.CONFLICT);
        }

        Cliente newCliente = clienteService.addCliente(cliente);

        return new ResponseEntity<Cliente>(newCliente, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
		LOG.info("actualizando cliente: {}", cliente);
		Cliente currentCliente = clienteService.getClienteById(id);

        if (currentCliente == null){
            LOG.info("Cliente con id {} no encontrado", id);
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
        
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") int id){
        LOG.info("Eliminando cliente con id: {}", id);
        Cliente cliente = clienteService.getClienteById(id);

        if (cliente == null){
            LOG.info("No se puede eliminar. Cliente con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        clienteService.deleteCliente(id);
        LOG.info("Cliente con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
