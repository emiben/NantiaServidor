package com.nantia.controller;


import java.util.Calendar;
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

import com.nantia.model.EnvaseStock;
import com.nantia.model.EnvasesTipos;
import com.nantia.model.Fabrica;
import com.nantia.model.Producto;
import com.nantia.model.ProductoStock;
import com.nantia.model.Reparto;
import com.nantia.model.Ruta;
import com.nantia.model.RutaCliente;
import com.nantia.model.Stock;
import com.nantia.model.Vehiculo;
import com.nantia.service.IFabricaService;
import com.nantia.service.IRepartoService;
import com.nantia.service.IStockService;
import com.nantia.service.IVehiculoService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/reparto")
public class RepartoController {

private final Logger LOG = LoggerFactory.getLogger(RepartoController.class);
	
	@Autowired
	IRepartoService repartoService;
	@Autowired
	IFabricaService fabricaService;
	@Autowired
	IStockService stockService;
	@Autowired
	IVehiculoService vehiculoService;


	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reparto>> getAllReparto() {
		LOG.info("trayendo todos los reparto");
        List<Reparto> reparto = repartoService.getAllReparto();
        
        if (reparto == null || reparto.isEmpty()){
            LOG.info("no se encontraron repartos");
            return new ResponseEntity<List<Reparto>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Reparto>>(reparto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Reparto> getRepartoById(@PathVariable("id") Integer id) {
		LOG.info("trayendo repartos por id: {}", id);
		Reparto repartos = repartoService.getRepartoById(id);

        if (repartos == null){
            LOG.info("Reparto por id {} no encontrada", id);
            return new ResponseEntity<Reparto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reparto>(repartos, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "{idFabrica}", method = RequestMethod.POST)
	public ResponseEntity<Reparto> addReparto(@RequestBody Reparto reparto, @PathVariable long idFabrica) {
		
		LOG.info("creando un nuevo reparto: {}", reparto);
			
		Ruta ruta = reparto.getRuta();
		
		Set<RutaCliente> setRutaCliente =  ruta.getSetRutaCliente();						
		Iterator<RutaCliente> iteRuCli = ruta.getSetRutaCliente().iterator();
	    while(iteRuCli.hasNext()) {
	    	RutaCliente rutaCliente = iteRuCli.next();
	    	rutaCliente.setRuta(ruta);
	    	setRutaCliente.add(rutaCliente);
	    }		
	    ruta.setSetRutaCliente(setRutaCliente);
		reparto.setRuta(ruta);
		
        if (repartoService.existe(reparto)){
            LOG.info("el reparto: " + reparto.getDescripcion() + " ya existe");
            return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);
        }
                      
        Stock nuevoStockVehiculo = stockService.addStock(reparto.getVehiculo().getStock());
        
		String resultado = actualizarStockFabrica(reparto.getVehiculo().getStock(), idFabrica);
		
		Vehiculo nuevovehiculo = actualizarStockVehiculo(nuevoStockVehiculo, reparto.getVehiculo());
        
		reparto.setVehiculo(nuevovehiculo);
		
        if(resultado.substring(0, 2) != "OK"){
        	LOG.info("Resultado: {}", resultado);
        	return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);        	
        }
        /*
        Set<EnvaseStock> setEnvaseStock =  reparto.getVehiculo().getStock().getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = reparto.getVehiculo().getStock().getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setStock(nuevoStockVehiculo);
	    	setEnvaseStock.add(envaseStock);
	    }		
	    nuevoStockVehiculo.setSetEnvaseStock(setEnvaseStock);    
	    	    
	    Set<ProductoStock> setProductoStock =  reparto.getVehiculo().getStock().getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = reparto.getVehiculo().getStock().getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	productoStock.setStock(nuevoStockVehiculo);
	    	setProductoStock.add(productoStock);
	    }		
	    nuevoStockVehiculo.setSetProductoStock(setProductoStock);  	

	    Stock stockUpd = stockService.updateStock(nuevoStockVehiculo); 
	    
	    reparto.getVehiculo().setStock(stockUpd);
	    Vehiculo nuevovehiculo = vehiculoService.updateVehiculo(reparto.getVehiculo());
	    reparto.setVehiculo(nuevovehiculo);
	    */
        Reparto newReparto = repartoService.addReparto(reparto);
        return new ResponseEntity<Reparto>(newReparto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}/{idFabrica}", method = RequestMethod.PUT)
	public ResponseEntity<Reparto> updateReparto(@PathVariable Long id, @RequestBody Reparto reparto, @PathVariable Long idFabrica) {

		LOG.info("actualizando fabrica: {}", reparto);
		Reparto currentReparto = repartoService.getRepartoById(id);
		
        if (currentReparto == null){
            LOG.info("Reparto con id {} no encontrado", id);
            return new ResponseEntity<Reparto>(HttpStatus.NOT_FOUND);
        }        
        
        Stock stock;
        Vehiculo vehiculo = reparto.getVehiculo();
        java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        if (vehiculo.getStock() == null){
        	stock = new Stock();
        	stock.setFecha(fecha);
        	LOG.info("El stock del vehiculo era nulo");
        }
        else {
            stock = reparto.getVehiculo().getStock();
            LOG.info("El stock del vehiculo NO era nulo");
        }
        
       
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
	    Stock newStock = stockService.updateStock(stock); 
	    vehiculo.setStock(newStock);
	    reparto.setVehiculo(vehiculo);
        
	    //****//****
	    String resultado = actualizarStockFabrica(reparto.getVehiculo().getStock(), idFabrica);
        
        if(resultado.substring(0, 2) != "OK"){
        	LOG.info("Info: {}.", resultado);
            return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);        	
        }
	    //**

        Reparto repartoUpd = repartoService.updateReparto(reparto);
        return new ResponseEntity<Reparto>(repartoUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReparto(@PathVariable("id") int id){
        LOG.info("Eliminando reparto con id: {}", id);
        Reparto reparto = repartoService.getRepartoById(id);

        if (reparto == null){
            LOG.info("No se puede eliminar. Reparto con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        repartoService.deleteReparto(id);
        LOG.info("Reparto con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	
	public Vehiculo actualizarStockVehiculo(Stock nuevoStockVehiculo, Vehiculo vehiculo) {
		
		
		Set<EnvaseStock> setEnvaseStock =  vehiculo.getStock().getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = vehiculo.getStock().getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setStock(nuevoStockVehiculo);
	    	setEnvaseStock.add(envaseStock);
	    }		
	    nuevoStockVehiculo.setSetEnvaseStock(setEnvaseStock);    
	    	    
	    Set<ProductoStock> setProductoStock =  vehiculo.getStock().getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = vehiculo.getStock().getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	productoStock.setStock(nuevoStockVehiculo);
	    	setProductoStock.add(productoStock);
	    }		
	    nuevoStockVehiculo.setSetProductoStock(setProductoStock);  	

	    Stock stockUpd = stockService.updateStock(nuevoStockVehiculo); 
	    
	    vehiculo.setStock(stockUpd);
	    Vehiculo nuevovehiculo = vehiculoService.updateVehiculo(vehiculo);
	    //reparto.setVehiculo(nuevovehiculo);
	    
		return nuevovehiculo;
	}
	public String actualizarStockFabrica(Stock stock, long idFabrica) {
		
		LOG.info("Entro a actualizarStock");
		LOG.info("stock.getId: {}, idFabrica: {}.", stock.getId(), idFabrica);
		String result = "OK"; 
		boolean parar = false;
		boolean encontro = false;
		
		Fabrica fabrica = fabricaService.getFabricaById(idFabrica);		
		Stock stockFabrica = fabrica.getStock();
		
		
		//Stock en fabrica
		Set<EnvaseStock> setEnvaseStockFabrica = stockFabrica.getSetEnvaseStock();
		Set<ProductoStock> setProductoStockFabrica = stockFabrica.getSetProductoStock();
		
		//Stock requerido para reparto
		Set<EnvaseStock> setEnvaseStockReparto = stock.getSetEnvaseStock();
		Set<ProductoStock> setProductoStockReparto = stock.getSetProductoStock();
		
								
		Iterator<EnvaseStock> iteEnvStkRep = stock.getSetEnvaseStock().iterator();
		Iterator<EnvaseStock> iteEnvStkFab = stockFabrica.getSetEnvaseStock().iterator();
		
		
		LOG.info("tamaño de setEnvaseStockReparto: {}", setEnvaseStockReparto.size());
		LOG.info("tamaño de setProductoStockReparto: {}", setProductoStockReparto.size());
		LOG.info("tamaño de setEnvaseStockFabrica: {}", setEnvaseStockFabrica.size());
		LOG.info("tamaño de setProductoStockFabrica: {}", setProductoStockFabrica.size());
		
		
		//Proceso el Set de encasesStock
		Long idEnvaseStock;
	    while(iteEnvStkRep.hasNext() && !parar) {
	    	encontro = false;
	    	parar = false;
	    	EnvaseStock envaseStockRep = iteEnvStkRep.next();
	    	EnvasesTipos envasesTiposRep = envaseStockRep.getEnvasesTipos();
	    	idEnvaseStock = envasesTiposRep.getId();
	    	
	    	if(setEnvaseStockFabrica.size() > 0) {	    		
		    	while(iteEnvStkFab.hasNext() && !parar && !encontro) {
			    	EnvaseStock envaseStockFab = iteEnvStkFab.next();
			    	EnvasesTipos envasesTiposFab = envaseStockFab.getEnvasesTipos();
			    	LOG.info("envasesTiposFab.getId(): {}, idEnvaseStock: {}", envasesTiposFab.getId(), idEnvaseStock);
			    	if(envasesTiposFab.getId() == idEnvaseStock)
			    	{
			    		LOG.info("Entro al if1");
			    		encontro = true;
			    		LOG.info("Envase; {}, cantidad fabrica: {}, cantidad requerida: {}.", envaseStockFab.getEnvasesTipos().getDescripcion(), envaseStockFab.getCantidad(), envaseStockRep.getCantidad());
			    		if(envaseStockFab.getCantidad() >= envaseStockRep.getCantidad())
			    		{
			    			LOG.info("Entro al if2");
			    			LOG.info("setCantidad: {}.", envaseStockFab.getCantidad() - envaseStockRep.getCantidad());
			    			envaseStockFab.setCantidad(envaseStockFab.getCantidad() - envaseStockRep.getCantidad());
			    			envaseStockFab.setStock(stockFabrica);
			    			setEnvaseStockFabrica.add(envaseStockFab);
			    		}
			    		else 
			    		{
			    			result = String.format("No hay suficientes envases en fabrica: [envase=%s], Hay %d y necesita %d", envaseStockFab.getEnvasesTipos().getDescripcion(), envaseStockFab.getCantidad(), envaseStockRep.getCantidad());
				    		parar = true;			    		
			    		}		    		
			    	}		    	
		    	}
	    	}
	    	else {
	    		result = String.format("La fabrica no tiene envases de ningun tipo");
	    	}
	    		
	    	if(encontro)
	    		stockFabrica.setSetEnvaseStock(setEnvaseStockFabrica);  
	    	else
	    		if(result.substring(0, 2) == "OK"){
	    			result = String.format("No se encontro el envases [envase=%s] en fabrica.", envaseStockRep.getEnvasesTipos().getDescripcion());
	    		}
	    		
	    }		
	    
	  //Proceso el Set de productosStock
	    Iterator<ProductoStock> iteProStkRep = setProductoStockReparto.iterator();
		Iterator<ProductoStock> iteProStkFab = setProductoStockFabrica.iterator();
	    Long idProductoStock;
	    while(iteProStkRep.hasNext() && !parar && result.substring(0, 2) == "OK") {
	    	encontro = false;
	    	parar = false;
	    	ProductoStock productoStockRep = iteProStkRep.next();
	    	Producto productoRep = productoStockRep.getProducto();
	    	idProductoStock = productoRep.getProductoId();
	    	
	    	if(setProductoStockFabrica.size() > 0) {	 
		    	while(iteProStkFab.hasNext() && !parar && !encontro) {
		    		ProductoStock productoStockFab = iteProStkFab.next();
		    		Producto productoFab = productoStockFab.getProducto();
		    		LOG.info("productoFab.getId: {}, idProductoStock:{}.",productoFab.getProductoId(), idProductoStock);
			    	if(productoFab.getProductoId() == idProductoStock)
			    	{
			    		encontro = true;
			    		if(productoStockFab.getCantidad() >= productoStockRep.getCantidad())
			    		{
			    			productoStockFab.setCantidad(productoStockFab.getCantidad() - productoStockRep.getCantidad());
			    			productoStockFab.setStock(stockFabrica);
			    			setProductoStockFabrica.add(productoStockFab);
			    		}
			    		else 
			    		{
			    			result = String.format("No hay suficientes productos en fabrica: [envase=%s], Hay %d y necesita %d", productoStockFab.getProducto().getNombre(), productoStockFab.getCantidad(), productoStockRep.getCantidad());
				    		parar = true;			    		
			    		}		    		
			    	}		    	
		    	}
	    	}
	    	else {
	    		result = String.format("La fabrica no tiene productos de ningun tipo");
	    	}
	    	if(encontro)
	    		stockFabrica.setSetProductoStock(setProductoStockFabrica);  
	    	else
	    		if(result.substring(0, 2) == "OK"){
	    			result = String.format("No se encontro el producto [%s] en fabrica.", productoStockRep.getProducto().getNombre());
	    		}	    		
	    }		
	    fabrica.setStock(stockFabrica);
	    
	    if(result.substring(0, 2) != "OK"){
	    	LOG.info("Entro 11");
            return result;               
        }
	    else {
	    	LOG.info("Entro 12");
		    //Fabrica fabricaUpd =  fabricaService.updateFabrica(fabrica);
	    	
	    	LOG.info("actualizando fabrica: {}", fabrica);
			Fabrica currentFabrica = fabricaService.getFabricaById(fabrica.getId());

			
			//****
					//Stock stock = fabrica.getStock();
			/*		
					Set<EnvaseStock> setEnvaseStock =  stockFabrica.getSetEnvaseStock();						
					Iterator<EnvaseStock> iteEnvStk = stockFabrica.getSetEnvaseStock().iterator();
				    while(iteEnvStk.hasNext()) {
				    	EnvaseStock envaseStock = iteEnvStk.next();
				    	envaseStock.setStock(stockFabrica);
				    	setEnvaseStock.add(envaseStock);
				    }		
				    stockFabrica.setSetEnvaseStock(setEnvaseStock);    
				    
				    
				    Set<ProductoStock> setProductoStock =  stockFabrica.getSetProductoStock();						
					Iterator<ProductoStock> iteProStk = stockFabrica.getSetProductoStock().iterator();
				    while(iteProStk.hasNext()) {
				    	ProductoStock productoStock = iteProStk.next();
				    	productoStock.setStock(stockFabrica);
				    	setProductoStock.add(productoStock);
				    }		
				    stockFabrica.setSetProductoStock(setProductoStock); 
				    fabrica.setStock(stock);
				    ////****
				    Stock stockNuevoVe = agregarStockAReparto(stock);
				    ////****
				    Stock stockNuevoVehiculo = stockService.addStock(stockNuevoVe);
				*/    
					//****
				    
	        if (currentFabrica == null){
	            LOG.info("Fabrica con id {} no encontrado", fabrica.getId());
	            //return new ResponseEntity<Fabrica>(HttpStatus.NOT_FOUND);
	        }
	        LOG.info("Id: ()", currentFabrica.getId());
	        Fabrica fabricaUpd = fabricaService.updateFabrica(fabrica);
	        //return new ResponseEntity<Fabrica>(fabricaUpd, HttpStatus.OK);
	        
	    }
				
		return result;
	}
	
	public Stock agregarStockAReparto(Stock stock) {
		
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
	    Stock newStock = stockService.addStock(stock); 
	    return newStock;
		
	}

}
