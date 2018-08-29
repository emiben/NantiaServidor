package com.nantia.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.nantia.model.EnvaseStock;
import com.nantia.model.ProductoStock;
import com.nantia.model.Stock;
import com.nantia.model.VehiculoUbicacion;
import com.nantia.service.IVehiculoUbicacionService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vehiculoubicacion")
public class VehiculoUbicacionController {
	
	private final Logger LOG = LoggerFactory.getLogger(VehiculoUbicacionController .class);
	
	@Autowired
	IVehiculoUbicacionService vehiculoUbicacionService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoUbicacion>> getAllVehiculoUbicacion() {
		LOG.info("trayendo todas las ubicaciones"); 
        List<VehiculoUbicacion> listaVehiculoUbicacion = vehiculoUbicacionService.getAllVehiculoUbicacion();
        
        if (listaVehiculoUbicacion == null || listaVehiculoUbicacion.isEmpty()){
            LOG.info("no se encontraron lineas de stock");
            return new ResponseEntity<List<VehiculoUbicacion>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<VehiculoUbicacion>>(listaVehiculoUbicacion, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<VehiculoUbicacion> getVehiculoUbicacionById(@PathVariable("id") Long id) {
		LOG.info("trayendo ubicacion por id: {}", id);
		VehiculoUbicacion vehiculoUbicacion = vehiculoUbicacionService.getVehiculoUbicacionById(id);

		 if (vehiculoUbicacion == null){
	            LOG.info("Ubicacion con id  {} no encontrado", id);
	            return new ResponseEntity<VehiculoUbicacion>(HttpStatus.NOT_FOUND);
	        }
		 
		 Calendar fecha = vehiculoUbicacion.getFecha();
		 
		 vehiculoUbicacion.setFecha(fecha);
		 
		 //****
		 /*
		 Date date = fecha.getTime();
		 SimpleDateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
		 String CurrentDate = dfDate.format(date);
		 LOG.info("Fecha: CurrentDate  {} ", CurrentDate);
		*/
		//****
		   
		 
        return new ResponseEntity<VehiculoUbicacion>(vehiculoUbicacion, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<VehiculoUbicacion> addVehiculoUbicacion(@RequestBody VehiculoUbicacion vehiculoUbicacion) {

		
		LOG.info("creando VehiculoUbicacion: {}", vehiculoUbicacion);
		
				
		if (vehiculoUbicacionService.existe(vehiculoUbicacion)){
            LOG.info("la ubicacion con long {} y lat {} ya existe", vehiculoUbicacion.getCoordLon(), vehiculoUbicacion.getCoordLat());
            return new ResponseEntity<VehiculoUbicacion>(HttpStatus.CONFLICT);
        }
        
		VehiculoUbicacion newVehiculoUbicacion = vehiculoUbicacionService.addVehiculoUbicacion(vehiculoUbicacion);        
        return new ResponseEntity<VehiculoUbicacion>(newVehiculoUbicacion, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<VehiculoUbicacion> updateVehiculoUbicacion(@PathVariable int id, @RequestBody VehiculoUbicacion vehiculoUbicacion) {
		LOG.info("Actualizando VehiculoUbicacion con id: {}", vehiculoUbicacion);
		
		VehiculoUbicacion currentVehiculoUbicacion = vehiculoUbicacionService.getVehiculoUbicacionById(id);
		
		if (currentVehiculoUbicacion == null){
            LOG.info("VehiculoUbicacion con id {} no encontrado", id);
            return new ResponseEntity<VehiculoUbicacion>(HttpStatus.NOT_FOUND);
        }
		
		VehiculoUbicacion vehiculoUbicacionUpd = vehiculoUbicacionService.updateVehiculoUbicacion(vehiculoUbicacion);
        LOG.info("Actualizando VehiculoUbicacion con id: {}", vehiculoUbicacionUpd);
        return new ResponseEntity<VehiculoUbicacion>(vehiculoUbicacionUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVehiculoUbicacion(@PathVariable("id") int id){
        LOG.info("Eliminando VehiculoUbicacion con id: {}", id);
        VehiculoUbicacion vehiculoUbicacion = vehiculoUbicacionService.getVehiculoUbicacionById(id);

        if (vehiculoUbicacion == null){
            LOG.info("No se puede eliminar VehiculoUbicacion con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        vehiculoUbicacionService.deleteVehiculoUbicacion(id);
        LOG.info("VehiculoUbicacion con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	

}
