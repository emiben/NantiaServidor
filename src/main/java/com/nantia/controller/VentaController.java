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

import com.nantia.model.Venta;
import com.nantia.service.IVentaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/ventas")
public class VentaController {
	
private final Logger LOG = LoggerFactory.getLogger(VentaController.class);
	
	@Autowired
	IVentaService ventaService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Venta>> getAllVentas() {
		LOG.info("trayendo todos los ventas"); 
        List<Venta> ventas = ventaService.getAllVentas();
        
        if (ventas == null || ventas.isEmpty()){
            LOG.info("no se encontraron ventas");
            return new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Venta> getVentaById(@PathVariable("id") Integer id) {
		LOG.info("trayendo venta por id: {}", id);
		Venta venta = ventaService.getVentaById(id);

        if (venta == null){
            LOG.info("venta por id {} no encontrado", id);
            return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
        }
        

        return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Venta> addVenta(@RequestBody Venta venta) {
		LOG.info("creando un nuevo venta: {}", venta.getId());
				
        if (ventaService.existe(venta)){
            LOG.info("el venta con documento " + venta.getId() + " ya existe");
            return new ResponseEntity<Venta>(HttpStatus.CONFLICT);
        }
        
        //if(venta.getFabrica() == null)
        	//actualizarStockReparto();
        //else
        	//actualizarStockFabrica();

        Venta newVenta = ventaService.addVenta(venta);

        return new ResponseEntity<Venta>(newVenta, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Venta> updateVenta(@PathVariable int id, @RequestBody Venta venta) {
		LOG.info("actualizando venta: {}", venta);
		Venta currentVenta = ventaService.getVentaById(id);

        if (currentVenta == null){
            LOG.info("Venta con id {} no encontrado", id);
            return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
        }
       
        Venta ventaUpd = ventaService.updateVenta(venta);
        return new ResponseEntity<Venta>(ventaUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVenta(@PathVariable("id") int id){
        LOG.info("Eliminando venta con id: {}", id);
        Venta venta = ventaService.getVentaById(id);

        if (venta == null){
            LOG.info("No se puede eliminar. Venta con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        ventaService.deleteVenta(id);
        LOG.info("Venta con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
