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
		Stock stock = stockService.addStock(dataReparto.getStock());		
		Reparto reparto = new Reparto();
		
		reparto.setDescripcion(dataReparto.getDescripcion());
		reparto.setVendedor1(dataReparto.getVendedor1());
		reparto.setVendedor2(dataReparto.getVendedor2());
		reparto.setVehiculo(dataReparto.getVehiculo());
		reparto.setFecha(dataReparto.getFecha());
		reparto.setEstado(dataReparto.getEstado());
		reparto.setRuta(dataReparto.getRuta());
		
        if (repartoService.existe(reparto)){
            LOG.info("el reparto: " + reparto.getDescripcion() + " ya existe");
            return new ResponseEntity<Reparto>(HttpStatus.CONFLICT);
        }
       
        Fabrica fabricaUpd = actualizarStockFabrica(stock, fabrica, -1);
        reparto.setFabrica(fabricaUpd);
  
        Stock stockUpd = stockService.updateStock(ingresarStockReparto(stock));
        
        reparto.setStock(stockUpd);
	    

        Reparto newReparto = repartoService.addReparto(reparto);
        return new ResponseEntity<Reparto>(newReparto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Reparto> updateReparto(@PathVariable Long id, @RequestBody DataReparto dataReparto) {

		Fabrica fabrica = fabricaService.getFabricaById(dataReparto.getFabricaid());
		Reparto repartoOld = repartoService.getRepartoById(id);
		Stock stockOld = repartoOld.getStock();
		Stock stockNew = dataReparto.getStock();
		
		Fabrica fabricaTmp = actualizarStockFabrica(stockOld, fabrica, 1);
		Stock stockTmp = actualizarStockReparto(stockOld, repartoOld, -1);
		
		repartoOld.setFabrica(fabricaTmp);
		repartoOld.setStock(stockTmp);
		
		Reparto repartoUpd = repartoService.updateReparto(repartoOld);
		
		Fabrica fabricaAct = actualizarStockFabrica(stockNew, fabrica, -1);
		Stock stockAct = actualizarStockReparto(stockNew, repartoOld, 1);
		repartoUpd.setFabrica(fabricaAct);
		repartoUpd.setStock(stockAct);
		
		repartoUpd = repartoService.updateReparto(repartoUpd);
        return new ResponseEntity<Reparto>(repartoUpd, HttpStatus.OK);
       
	}
	
	public Stock ingresarStockReparto(Stock stock) {
		
		
		Set<EnvaseStock> setEnvaseStock =  stock.getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = stock.getSetEnvaseStock().iterator();
		LOG.info("setEnvaseStock.size: {}",setEnvaseStock.size());
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setCantidad(envaseStock.getCantidad());
	    	envaseStock.setStock(stock);
	    	setEnvaseStock.add(envaseStock);
	    }
	    stock.getSetEnvaseStock().retainAll(setEnvaseStock);
	    stock.getSetEnvaseStock().addAll(setEnvaseStock);
	    	    
	    Set<ProductoStock> setProductoStock =  stock.getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = stock.getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	productoStock.setCantidad(productoStock.getCantidad());
	    	productoStock.setStock(stock);
	    	setProductoStock.add(productoStock);
	    }	
	    stock.getSetProductoStock().retainAll(setProductoStock);
	    stock.getSetProductoStock().addAll(setProductoStock);
	    
	    stock.setSetProductoStock(setProductoStock);
	    
	    
		return stock;
	}
	
	public Stock actualizarStockReparto(Stock stock, Reparto dataReparto, int coeficiente) {
		
		//LOG.info("Entro a actualizarStock Reparto********************************");
		 
		String result = "OK"; 
		boolean parar = false;
		boolean encontro = false;
		
		Stock stockReparto = dataReparto.getStock();
		
		Set<EnvaseStock> setEnvaseStockFabrica = stockReparto.getSetEnvaseStock();
		Set<ProductoStock> setProductoStockFabrica = stockReparto.getSetProductoStock();
		
		Set<EnvaseStock> setEnvaseStockReparto = stock.getSetEnvaseStock();
		Set<ProductoStock> setProductoStockReparto = stock.getSetProductoStock();
								
		Iterator<EnvaseStock> iteEnvStkRep = setEnvaseStockReparto.iterator();
		Iterator<EnvaseStock> iteEnvStkFab = null;//stockFabrica.getSetEnvaseStock().iterator();
		
		Long idEnvaseStock;
	    while(iteEnvStkRep.hasNext() && !parar) {
	    	encontro = false;
	    	parar = false;
	    	EnvaseStock envaseStockRep = iteEnvStkRep.next();
	    	EnvasesTipos envasesTiposRep = envaseStockRep.getEnvasesTipos();
	    	
	    	idEnvaseStock = envasesTiposRep.getId();
	    		    	
	    	if(setEnvaseStockFabrica.size() > 0) {
	    		iteEnvStkFab = stockReparto.getSetEnvaseStock().iterator();
		    	while(iteEnvStkFab.hasNext() && !parar && !encontro) {
			    	EnvaseStock envaseStockFab = iteEnvStkFab.next();
			    	EnvasesTipos envasesTiposFab = envaseStockFab.getEnvasesTipos();
			    	
			    	if(envasesTiposFab.getId() == idEnvaseStock)
			    	{
			    		encontro = true;
			    		//LOG.info("Envase: {}, cantidad fabrica: {}, cantidad requerida: {}.", envaseStockFab.getEnvasesTipos().getDescripcion(), envaseStockFab.getCantidad(), envaseStockRep.getCantidad());
			    		//LOG.info("coeficiente: {}", coeficiente);
			    		if(envaseStockFab.getCantidad() >= envaseStockRep.getCantidad() || coeficiente > 0)
			    		{
			    			//LOG.info("setCantidad: {}.", envaseStockFab.getCantidad() + (envaseStockRep.getCantidad() * coeficiente));
			    			envaseStockFab.setCantidad(envaseStockFab.getCantidad() + (envaseStockRep.getCantidad() * coeficiente));
			    			envaseStockFab.setStock(stockReparto);
			    			setEnvaseStockFabrica.add(envaseStockFab);
			    		}
			    		else 
			    		{
			    			result = String.format("No hay suficientes envases en fabrica: [envase={}], Hay {} y necesita {}", envaseStockFab.getEnvasesTipos().getDescripcion(), envaseStockFab.getCantidad(), envaseStockRep.getCantidad());
				    		parar = true;			    		
			    		}		    		
			    	}		    	
		    	}
	    	}
	    	else {
	    		result = String.format("La fabrica no tiene envases de ningun tipo");
	    	}
	    		
	    	if(encontro) {
	    		stockReparto.getSetEnvaseStock().retainAll(setEnvaseStockFabrica);
	    		stockReparto.getSetEnvaseStock().addAll(setEnvaseStockFabrica);
	    	}
	    	else
	    		if(result.substring(0, 2) == "OK"){
	    			result = String.format("No se encontro el envases [envase=%s] en fabrica.", envaseStockRep.getEnvasesTipos().getDescripcion());
	    		}	    		
	    }		
	    
	  //Proceso el Set de productosStock
	    Iterator<ProductoStock> iteProStkRep = setProductoStockReparto.iterator();
		Iterator<ProductoStock> iteProStkFab = null;
	    Long idProductoStock;
	    while(iteProStkRep.hasNext() && !parar && result.substring(0, 2) == "OK") {
	    	encontro = false;
	    	parar = false;
	    	ProductoStock productoStockRep = iteProStkRep.next();
	    	Producto productoRep = productoStockRep.getProducto();
	    	idProductoStock = productoRep.getProductoId();
	    	
	    	if(setProductoStockFabrica.size() > 0) {	 
	    		iteProStkFab = setProductoStockFabrica.iterator();
		    	while(iteProStkFab.hasNext() && !parar && !encontro) {
		    		ProductoStock productoStockFab = iteProStkFab.next();
		    		Producto productoFab = productoStockFab.getProducto();
		    		
			    	if(productoFab.getProductoId() == idProductoStock)
			    	{
			    		encontro = true;
			    		if(productoStockFab.getCantidad() >= productoStockRep.getCantidad() || coeficiente > 0)
			    		{
			    			productoStockFab.setCantidad(productoStockFab.getCantidad() + productoStockRep.getCantidad() * coeficiente);
			    			productoStockFab.setStock(stockReparto);
			    			setProductoStockFabrica.add(productoStockFab);
			    		}
			    		else 
			    		{
			    			result = String.format("No hay suficientes productos en fabrica: [envase=%s], Hay %f y necesita %f", productoStockFab.getProducto().getNombre(), productoStockFab.getCantidad(), productoStockRep.getCantidad());
				    		parar = true;			    		
			    		}		    		
			    	}		    	
		    	}
	    	}
	    	else {
	    		result = String.format("La fabrica no tiene productos de ningun tipo");
	    	}
	    	if(encontro) {
	    		stockReparto.getSetProductoStock().retainAll(setProductoStockFabrica);
	    		stockReparto.getSetProductoStock().addAll(setProductoStockFabrica);
	    	}
	    	else
	    		if(result.substring(0, 2) == "OK"){
	    			result = String.format("No se encontro el producto [%s] en fabrica.", productoStockRep.getProducto().getNombre());
	    		}	    		
	    }		

	    //LOG.info("************************************************");
		return stockReparto;
	}
	
	public Fabrica actualizarStockFabrica(Stock stock, Fabrica fabricaR, int coeficiente) {

		String result = "OK"; 
		boolean parar = false;
		boolean encontro = false;
		
		Fabrica fabrica = fabricaService.getFabricaById(fabricaR.getId());		
		Stock stockFabrica = fabrica.getStock();
		
		Set<EnvaseStock> setEnvaseStockFabrica = stockFabrica.getSetEnvaseStock();
		Set<ProductoStock> setProductoStockFabrica = stockFabrica.getSetProductoStock();
		
		Set<EnvaseStock> setEnvaseStockReparto = stock.getSetEnvaseStock();
		Set<ProductoStock> setProductoStockReparto = stock.getSetProductoStock();
										
		Iterator<EnvaseStock> iteEnvStkRep = setEnvaseStockReparto.iterator();
		Iterator<EnvaseStock> iteEnvStkFab = null;//stockFabrica.getSetEnvaseStock().iterator();
		Long idEnvaseStock;
	    while(iteEnvStkRep.hasNext() && !parar) {
	    	encontro = false;
	    	parar = false;
	    	EnvaseStock envaseStockRep = iteEnvStkRep.next();
	    	EnvasesTipos envasesTiposRep = envaseStockRep.getEnvasesTipos();
	    	
	    	idEnvaseStock = envasesTiposRep.getId();	    	
	    	
	    	if(setEnvaseStockFabrica.size() > 0) {
	    		iteEnvStkFab = setEnvaseStockFabrica.iterator();
		    	while(iteEnvStkFab.hasNext() && !parar && !encontro) {
			    	EnvaseStock envaseStockFab = iteEnvStkFab.next();
			    	EnvasesTipos envasesTiposFab = envaseStockFab.getEnvasesTipos();
			    	if(envasesTiposFab.getId() == idEnvaseStock)
			    	{
			    		encontro = true;
			    		//LOG.info("Envase; {}, cantidad fabrica: {}, cantidad requerida: {}.", envaseStockFab.getEnvasesTipos().getDescripcion(), envaseStockFab.getCantidad(), envaseStockRep.getCantidad());
			    		if(envaseStockFab.getCantidad() >= envaseStockRep.getCantidad() || coeficiente > 0)
			    		{
			    			//LOG.info("setCantidad: {}.", envaseStockFab.getCantidad() + (envaseStockRep.getCantidad() * coeficiente));
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
		Iterator<ProductoStock> iteProStkFab = null;
	    Long idProductoStock;
	    while(iteProStkRep.hasNext() && !parar && result.substring(0, 2) == "OK") {
	    	encontro = false;
	    	parar = false;
	    	ProductoStock productoStockRep = iteProStkRep.next();
	    	Producto productoRep = productoStockRep.getProducto();
	    	idProductoStock = productoRep.getProductoId();
	    	
	    	if(setProductoStockFabrica.size() > 0) {	 
	    		iteProStkFab = setProductoStockFabrica.iterator();
		    	while(iteProStkFab.hasNext() && !parar && !encontro) {
		    		ProductoStock productoStockFab = iteProStkFab.next();
		    		Producto productoFab = productoStockFab.getProducto();

		    		if(productoFab.getProductoId() == idProductoStock)
			    	{
			    		encontro = true;
			    		if(productoStockFab.getCantidad() >= productoStockRep.getCantidad() || coeficiente > 0)
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
	    //LOG.info("************************************************");
		return fabrica;
	}
	
	
	
}
