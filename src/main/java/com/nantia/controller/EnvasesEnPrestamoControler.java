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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Cliente;
import com.nantia.model.EnvasesEnPrestamo;
import com.nantia.model.EnvasesTipos;
import com.nantia.repo.ClienteRepository;
import com.nantia.service.IClienteService;
import com.nantia.service.IEnvasesEnPrestamoService;
import com.nantia.service.IEnvasesTipoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/envasesEnPrestamo")
public class EnvasesEnPrestamoControler {
	
private final Logger LOG = LoggerFactory.getLogger(EnvasesEnPrestamoControler.class);
	
	@Autowired
	IEnvasesEnPrestamoService envasesEnPrestamoService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IEnvasesTipoService envasesTipoService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EnvasesEnPrestamo>> getAllEnvasesEnPrestamo() {
		LOG.info("trayendo todos los envases en prestamo");
        List<EnvasesEnPrestamo> envasesEnPrestamo = envasesEnPrestamoService.getAllEnvasesEnPrestamo();
        
        if (envasesEnPrestamo == null || envasesEnPrestamo.isEmpty()){
            LOG.info("no se encontraron envases en prestamo");
            return new ResponseEntity<List<EnvasesEnPrestamo>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<EnvasesEnPrestamo>>(envasesEnPrestamo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{cliente}", method = RequestMethod.GET)
	public ResponseEntity<List<EnvasesEnPrestamo>> getEnvasesEnPrestamoByCliente(@PathVariable("cliente") Cliente cliente) {
		LOG.info("trayendo envases en prestamo por cliente: {} {}", cliente.getNombre1(), cliente.getNombre2());
		List<EnvasesEnPrestamo> envasesEnPrestamo = envasesEnPrestamoService.getEnvasesEnPrestamoByCliente(cliente);
		
		if (envasesEnPrestamo == null || envasesEnPrestamo.isEmpty()){
            LOG.info("no se encontraron envases en prestamo");
            return new ResponseEntity<List<EnvasesEnPrestamo>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<EnvasesEnPrestamo>>(envasesEnPrestamo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{cantidad}/{clienteId}/{tipoEnvaseId}", method = RequestMethod.POST)
	public ResponseEntity<EnvasesEnPrestamo> addEnvasesEnPrestamo(@PathVariable int cantidad,@PathVariable Long clienteId,@PathVariable Long tipoEnvaseId) {
		LOG.info("creando un nuevo envases en prestamo: {}", cantidad);
		
		LOG.info("clienteId, etipoEnvaseId: - {} - {} -", clienteId, tipoEnvaseId);
		
		Cliente c = clienteService.getClienteById(clienteId);
		EnvasesTipos e = envasesTipoService.getEnvasesTipoById(tipoEnvaseId);
				
		LOG.info("c, e: {} {}", c.getNombre1(), e.getDescripcion());
		LOG.info("envases en prestamo: {} - {} -  - ", c.getId(), e.getId());//, envasesEnPrestamo.getClientes().getId());//, envasesEnPrestamo.getEnvasetipos().getId());
		EnvasesEnPrestamo envasesEnPrestamo = new EnvasesEnPrestamo();
		envasesEnPrestamo.setCantidad(cantidad);
		envasesEnPrestamo.setClientes(c);
		envasesEnPrestamo.setEnvasetipos(e);
		LOG.info("envases en prestamo: {} - {} -  - ", c.getId(), e.getId());
        if (envasesEnPrestamoService.existe(envasesEnPrestamo)){
            LOG.info("el envases en prestamo para el cliente " + envasesEnPrestamo.getClientes()+ " ya existe");
            return new ResponseEntity<EnvasesEnPrestamo>(HttpStatus.CONFLICT);
        }
        LOG.info("Se ingresará el envases en prestamo para el cliente " + envasesEnPrestamo.getClientes());
        EnvasesEnPrestamo newEnvasesEnPrestamo = envasesEnPrestamoService.addEnvasesEnPrestamo(envasesEnPrestamo);

        return new ResponseEntity<EnvasesEnPrestamo>(newEnvasesEnPrestamo, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<EnvasesEnPrestamo> updateEnvasesEnPrestamo(@PathVariable int id, @RequestBody EnvasesEnPrestamo envasesEnPrestamo) {
		LOG.info("actualizando envases en prestamo: {}", envasesEnPrestamo);
		EnvasesEnPrestamo currentEnvasesEnPrestamo = envasesEnPrestamoService.getEnvasesEnPrestamoById(id);

        if (currentEnvasesEnPrestamo == null){
            LOG.info("Cliente con id {} no encontrado", id);
            return new ResponseEntity<EnvasesEnPrestamo>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentEnvasesEnPrestamo.getId());
        envasesEnPrestamo.setClientes(currentEnvasesEnPrestamo.getClientes());
        envasesEnPrestamo.setEnvasetipos(currentEnvasesEnPrestamo.getEnvasetipos());
        EnvasesEnPrestamo envasesEnPrestamoUpd = envasesEnPrestamoService.updateEnvasesEnPrestamo(envasesEnPrestamo);
        return new ResponseEntity<EnvasesEnPrestamo>(envasesEnPrestamoUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEnvasesEnPrestamo(@PathVariable("id") int id){
        LOG.info("Eliminando envase en prestamo con id: {}", id);
        EnvasesEnPrestamo envasesEnPrestamo = envasesEnPrestamoService.getEnvasesEnPrestamoById(id);

        if (envasesEnPrestamo == null){
            LOG.info("No se puede eliminar. envases en prestamo con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        LOG.info("se va a eliminar el envases en prestamo con id: {}.", id);
        envasesEnPrestamoService.deleteEnvasesEnPrestamo(envasesEnPrestamo);
        LOG.info("Envases en prestamo con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
