package com.nantia.controller;

import java.util.List;

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

import com.nantia.model.Vehiculo;
import com.nantia.service.IFabricaService;
import com.nantia.service.IVehiculoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vehiculo")
public class VehiculoController {

private final Logger LOG = LoggerFactory.getLogger(VehiculoController.class);
	
	@Autowired
	IVehiculoService vehiculoService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
		LOG.info("trayendo todos los vehiculos"); 
        List<Vehiculo> vehiculo = vehiculoService.getAllVehiculo();
        
        if (vehiculo == null || vehiculo.isEmpty()){
            LOG.info("no se encontraron vehiculos");
            return new ResponseEntity<List<Vehiculo>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Vehiculo>>(vehiculo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable("id") Integer id) {
		LOG.info("trayendo vehiculo por id: {}", id);
		Vehiculo vehiculo = vehiculoService.getVehiculoById(id);

        if (vehiculo == null){
            LOG.info("vehiculo por id {} no encontrada", id);
            return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
        } 	

        return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Vehiculo> addVehiculo(@RequestBody Vehiculo vehiculo) {
		LOG.info("creando un nuevo Vehiculo: {}", vehiculo);

        if (vehiculoService.existe(vehiculo)){
            LOG.info("la vehiculo: " + vehiculo.getMatricula() + " ya existe");
            return new ResponseEntity<Vehiculo>(HttpStatus.CONFLICT);
        }

        Vehiculo newVehiculo = vehiculoService.addVehiculo(vehiculo);

        return new ResponseEntity<Vehiculo>(newVehiculo, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable int id, @RequestBody Vehiculo vehiculo) {
		LOG.info("actualizando vehiculo: {}", vehiculo);
		Vehiculo currentVehiculo = vehiculoService.getVehiculoById(id);

        if (currentVehiculo == null){
            LOG.info("Vehiculo con id {} no encontrado", id);
            return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentVehiculo.getId());
        Vehiculo vehiculoUpd = vehiculoService.updateVehiculo(vehiculo);
        return new ResponseEntity<Vehiculo>(vehiculoUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVehiculo(@PathVariable("id") int id){
        LOG.info("Eliminando vehiculo con id: {}", id);
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);

        if (vehiculo == null){
            LOG.info("No se puede eliminar. Vehiculo con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        vehiculoService.deleteVehiculo(id);
        LOG.info("Vehiculo con id: {} eliminanda con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
