package com.nantia.controller;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.nantia.model.EnvaseStock;
import com.nantia.model.ProductoStock;
import com.nantia.model.Stock;
import com.nantia.model.Vehiculo;
import com.nantia.service.IStockService;
import com.nantia.service.IVehiculoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vehiculo")
public class VehiculoController {

private final Logger LOG = LoggerFactory.getLogger(VehiculoController.class);
	
	@Autowired
	IVehiculoService vehiculoService;
	
	@Autowired
	IStockService stockService;
	
	
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
        /*
        Stock stock = vehiculo.getStock();
               
        

        if (stock != null){
        	
        	Stock newStock = stockService.addStock(stock);
			Set<EnvaseStock> setEnvaseStock =  stock.getSetEnvaseStock();						
			Iterator<EnvaseStock> iteEnvStk = stock.getSetEnvaseStock().iterator();
		    while(iteEnvStk.hasNext()) {
		    	EnvaseStock envaseStock = iteEnvStk.next();
		    	envaseStock.setStock(newStock);
		    	setEnvaseStock.add(envaseStock);
		    }		
		    newStock.setSetEnvaseStock(setEnvaseStock);    
		    
		    
		    Set<ProductoStock> setProductoStock =  stock.getSetProductoStock();						
			Iterator<ProductoStock> iteProStk = stock.getSetProductoStock().iterator();
		    while(iteProStk.hasNext()) {
		    	ProductoStock productoStock = iteProStk.next();
		    	productoStock.setStock(newStock);
		    	setProductoStock.add(productoStock);
		    }		
		    newStock.setSetProductoStock(setProductoStock); 
		    
		    Stock stockUpd = stockService.updateStock(newStock);
		    newVehiculo.setStock(stockUpd);	    		
			
		}*/
        
        Vehiculo vehiculoUpd = vehiculoService.addVehiculo(newVehiculo);

        return new ResponseEntity<Vehiculo>(vehiculoUpd, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable int id, @RequestBody Vehiculo vehiculo) {
		LOG.info("actualizando vehiculo: {}", vehiculo);
		Vehiculo currentVehiculo = vehiculoService.getVehiculoById(id);

		//****
		/*Stock stock = vehiculo.getStock();
		
		if (stock != null){
			Set<EnvaseStock> setEnvaseStock =  stock.getSetEnvaseStock();						
			Iterator<EnvaseStock> iteEnvStk = stock.getSetEnvaseStock().iterator();
		    while(iteEnvStk.hasNext()) {
		    	EnvaseStock envaseStock = iteEnvStk.next();
		    	envaseStock.setStock(stock);
		    	setEnvaseStock.add(envaseStock);
		    }		
		    stock.setSetEnvaseStock(setEnvaseStock);    
		    
		    
		    Set<ProductoStock> setProductoStock =  stock.getSetProductoStock();						
			Iterator<ProductoStock> iteProStk = stock.getSetProductoStock().iterator();
		    while(iteProStk.hasNext()) {
		    	ProductoStock productoStock = iteProStk.next();
		    	productoStock.setStock(stock);
		    	setProductoStock.add(productoStock);
		    }		
		    stock.setSetProductoStock(setProductoStock); 
			
		    vehiculo.setStock(stock);
		}*/
		//****
		
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
	
	@RequestMapping(value = "/vehiculossinstock/{fecha}", method = RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> getAllVehiculosSinStock(@PathVariable String fecha) {
		
		LOG.info("trayendo todos los vehiculos sin stock para la fecha indicada"); 
		LOG.info("fecha: {}", fecha); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		
		List<Vehiculo> vehiculos;
		Date date = new Date();
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		vehiculos = vehiculoService.getAllVehiculosSinStock(date);
				       				
		       
        if (vehiculos == null || vehiculos.isEmpty()){
            LOG.info("no se encontraron vehiculos sin stock para la fecha indicada");
            return new ResponseEntity<List<Vehiculo>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Vehiculo>>(vehiculos, HttpStatus.OK);		
	}

}
