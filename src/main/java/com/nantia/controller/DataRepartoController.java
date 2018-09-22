package com.nantia.controller;

import java.util.ArrayList;
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

import com.nantia.model.DataReparto;
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
import com.nantia.service.IRutaService;
import com.nantia.service.IStockService;
import com.nantia.service.IVehiculoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/datareparto")
public class DataRepartoController {
	
private final Logger LOG = LoggerFactory.getLogger(DataRepartoController.class);
	
	@Autowired
	IRepartoService repartoService;
	@Autowired
	IFabricaService fabricaService;
	@Autowired
	IStockService stockService;
	@Autowired
	IVehiculoService vehiculoService;
	@Autowired
	IRutaService rutaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DataReparto>> getAllDataReparto() {
		LOG.info("trayendo todos los DataReparto");

		List<Reparto> listReparto = repartoService.getAllRepartoCreado();
        List<DataReparto> listDataReparto = new ArrayList<DataReparto>();
        
        DataReparto dataReparto;
        
        if (listReparto == null || listReparto.isEmpty()){
            LOG.info("no se encontraron repartos");
            return new ResponseEntity<List<DataReparto>>(HttpStatus.NO_CONTENT);
        }
                				
		Iterator<Reparto> iteRep = listReparto.iterator();
	    while(iteRep.hasNext()) {
	    	Reparto reparto = iteRep.next();
	    	dataReparto = new DataReparto();
	    	
	    	dataReparto.setIdReparto(reparto.getId());
	    	dataReparto.setDescripcion(reparto.getDescripcion());
	    	dataReparto.setDescripcionVehiculo(reparto.getVehiculo().getDescripcion());
	    	dataReparto.setDias(reparto.getRuta().getDias());
	    	
	    	listDataReparto.add(dataReparto);
	    }		
		
        return new ResponseEntity<List<DataReparto>>(listDataReparto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Reparto> addReparto(@RequestBody DataReparto dataReparto) {
		
		
		Fabrica fabrica = fabricaService.getFabricaById(dataReparto.getFabricaid());
		
		Reparto reparto = new Reparto();
		reparto.setDescripcion(dataReparto.getDescripcion());
		reparto.setEstado(dataReparto.getEstado());
		reparto.setFabrica(fabrica);
		reparto.setFecha(dataReparto.getFecha());
		reparto.setVehiculo(dataReparto.getVehiculo());
		reparto.setVendedor1(dataReparto.getVendedor1());
		reparto.setVendedor2(dataReparto.getVendedor2());
		
		Ruta ruta  = new Ruta();
		if(dataReparto.getRuta().getId() > 0)
			ruta = rutaService.getRutaById(dataReparto.getRuta().getId());// dataReparto.getRuta();		
		else
			ruta = dataReparto.getRuta();
		if(ruta.getSetRutaCliente() != null)
		{
			Set<RutaCliente> setRutaCliente =  ruta.getSetRutaCliente();						
			Iterator<RutaCliente> iteRuCli = ruta.getSetRutaCliente().iterator();
		    while(iteRuCli.hasNext()) {
		    	RutaCliente rutaCliente = iteRuCli.next();
		    	rutaCliente.setRuta(ruta);
		    	setRutaCliente.add(rutaCliente);
		    }		
		    ruta.setSetRutaCliente(setRutaCliente);
			reparto.setRuta(ruta);
		}
		
		
        if (repartoService.existe(reparto)){
            LOG.info("el reparto: " + reparto.getDescripcion() + " ya existe");
            return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);
        }
                      
        Stock nuevoStockVehiculo = stockService.addStock(reparto.getVehiculo().getStock());
        
		String resultado = actualizarStockFabrica(reparto.getVehiculo().getStock(), reparto.getFabrica(), -1);
		
		Vehiculo nuevovehiculo = actualizarStockVehiculo(nuevoStockVehiculo, reparto.getVehiculo(), 1);
        
		reparto.setVehiculo(nuevovehiculo);
		
        if(resultado.substring(0, 2) != "OK"){
        	LOG.info("Resultado: {}", resultado);
        	return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);        	
        }
        
        Reparto newReparto = repartoService.addReparto(reparto);
        return new ResponseEntity<Reparto>(newReparto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Reparto> updateReparto(@PathVariable Long id, @RequestBody DataReparto dataReparto) {

		Fabrica fabrica = fabricaService.getFabricaById(dataReparto.getFabricaid());
		
		Reparto reparto = new Reparto();
		reparto.setDescripcion(dataReparto.getDescripcion());
		reparto.setEstado(dataReparto.getEstado());
		reparto.setFabrica(fabrica);
		reparto.setFecha(dataReparto.getFecha());
		reparto.setVehiculo(dataReparto.getVehiculo());
		reparto.setVendedor1(dataReparto.getVendedor1());
		reparto.setVendedor2(dataReparto.getVendedor2());
		
		Ruta ruta = reparto.getRuta();		
		
		if(ruta.getSetRutaCliente() != null)
		{
			Set<RutaCliente> setRutaCliente =  ruta.getSetRutaCliente();						
			Iterator<RutaCliente> iteRuCli = ruta.getSetRutaCliente().iterator();
		    while(iteRuCli.hasNext()) {
		    	RutaCliente rutaCliente = iteRuCli.next();
		    	rutaCliente.setRuta(ruta);
		    	setRutaCliente.add(rutaCliente);
		    }		
		    ruta.setSetRutaCliente(setRutaCliente);
			reparto.setRuta(ruta);
		}
		
		Reparto repartoOld = repartoService.getRepartoById(id);
		
    	String resultado = actualizarStockFabrica(repartoOld.getVehiculo().getStock(), fabrica, 1);		
		Vehiculo vehiculo = actualizarStockVehiculo(repartoOld.getVehiculo().getStock(), reparto.getVehiculo(), 1);
  		
		Reparto repartoUpd = new Reparto();
		if(resultado.equals("OK"))
		{
			reparto.setVehiculo(vehiculo);
			reparto.setFabrica(fabricaService.getFabricaById(fabrica.getId()));
			repartoUpd = repartoService.updateReparto(reparto);

		resultado = actualizarStockFabrica(repartoUpd.getVehiculo().getStock(), fabrica, -1);		
       
		}
		repartoUpd.setFabrica(fabricaService.getFabricaById(fabrica.getId()));
		Reparto repartoUpd2 = repartoService.updateReparto(repartoUpd);
        return new ResponseEntity<Reparto>(repartoUpd2, HttpStatus.OK);
       
	}
	
	public Vehiculo actualizarStockVehiculo(Stock nuevoStockVehiculo, Vehiculo vehiculo, int coeficiente) {
		
		
		Set<EnvaseStock> setEnvaseStock =  vehiculo.getStock().getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = vehiculo.getStock().getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setStock(nuevoStockVehiculo);
	    	setEnvaseStock.add(envaseStock);
	    }
	    nuevoStockVehiculo.getSetEnvaseStock().retainAll(setEnvaseStock);
	    nuevoStockVehiculo.getSetEnvaseStock().addAll(setEnvaseStock);
	    //nuevoStockVehiculo.setSetEnvaseStock(setEnvaseStock);    
	    	    
	    Set<ProductoStock> setProductoStock =  vehiculo.getStock().getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = vehiculo.getStock().getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	productoStock.setCantidad(productoStock.getCantidad() * coeficiente);
	    	productoStock.setStock(nuevoStockVehiculo);
	    	setProductoStock.add(productoStock);
	    }	
	    nuevoStockVehiculo.getSetProductoStock().retainAll(setProductoStock);
        nuevoStockVehiculo.getSetProductoStock().addAll(setProductoStock);
		//nuevoStockVehiculo.setSetProductoStock(setProductoStock);  	

	    Stock stockUpd = stockService.updateStock(nuevoStockVehiculo); 
	    
	    vehiculo.setStock(stockUpd);
	    Vehiculo nuevovehiculo = vehiculoService.updateVehiculo(vehiculo);
	    
		return nuevovehiculo;
	}
	
	public String actualizarStockFabrica(Stock stock, Fabrica fabricaR, int coeficiente) {
		
		LOG.info("Entro a actualizarStock");
		String result = "OK"; 
		boolean parar = false;
		boolean encontro = false;
		
		
		Fabrica fabrica = fabricaService.getFabricaById(fabricaR.getId());
		
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
			    			LOG.info("setCantidad: {}.", envaseStockFab.getCantidad() + (envaseStockRep.getCantidad() * coeficiente));
			    			envaseStockFab.setCantidad(envaseStockFab.getCantidad() + (envaseStockRep.getCantidad() * coeficiente));
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
	    		
	    	if(encontro) {
	    		stockFabrica.getSetEnvaseStock().retainAll(setEnvaseStockFabrica);
	    		stockFabrica.getSetEnvaseStock().addAll(setEnvaseStockFabrica);
	    	}
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
			    			productoStockFab.setCantidad(productoStockFab.getCantidad() + productoStockRep.getCantidad() * coeficiente);
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
	    	if(encontro) {
	    		stockFabrica.getSetProductoStock().retainAll(setProductoStockFabrica);
	    		stockFabrica.getSetProductoStock().addAll(setProductoStockFabrica);
	    	}
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
    	
	    	LOG.info("actualizando fabrica: {}", fabrica);
			Fabrica currentFabrica = fabricaService.getFabricaById(fabrica.getId());
			    
	        if (currentFabrica == null){
	            LOG.info("Fabrica con id {} no encontrado", fabrica.getId());
	        }
	        LOG.info("Id: ()", currentFabrica.getId());
	        Fabrica fabricaUpd = fabricaService.updateFabrica(fabrica);
	        
	    }
				
		return result;
	}
	
	
	
}
