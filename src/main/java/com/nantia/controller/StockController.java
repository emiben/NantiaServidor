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

import com.nantia.model.EnvaseStock;
import com.nantia.model.ProductoStock;
import com.nantia.model.Stock;
import com.nantia.service.EnvaseStockService;
import com.nantia.service.IEnvaseStockService;
import com.nantia.service.IProductoStockService;
import com.nantia.service.IStockService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/stock")
public class StockController {
	
	
	private final Logger LOG = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	IStockService stockService;
	@Autowired
	IEnvaseStockService envaseStockService;
	@Autowired
	IProductoStockService productoStockService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Stock>> getAllStock() {
		LOG.info("trayendo todas las lineas de stock"); 
        List<Stock> listaStock = stockService.getAllStock();
        
        if (listaStock == null || listaStock.isEmpty()){
            LOG.info("no se encontraron lineas de stock");
            return new ResponseEntity<List<Stock>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Stock>>(listaStock, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Stock> getStockById(@PathVariable("id") Integer id) {
		LOG.info("trayendo lista de stock por id: {}", id);
		Stock listaStock = stockService .getStockById(id);

		 if (listaStock == null){
	            LOG.info("linea de stock con id  {} no encontrado", id);
	            return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
	        }
	        
        return new ResponseEntity<Stock>(listaStock, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
		LOG.info("creando stock: {}", stock);
				
		 
		if (stockService.existe(stock)){
            LOG.info("El stock con id " + stock.getId() + " ya existe");
            return new ResponseEntity<Stock>(HttpStatus.CONFLICT);
        }
			
		Stock newStock = stockService.addStock(stock);  
		
		//****
		Set<EnvaseStock> setEnvaseStock =  stock.getSetEnvaseStock();
		Iterator<EnvaseStock> iteEnvStk = stock.getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	if(envaseStock.getId()>0) {
		    	envaseStock.setStock(newStock);
		    	setEnvaseStock.add(envaseStock);
	    	}else {
	    		EnvaseStock newEnvaseStock = envaseStockService.addEnvaseStock(envaseStock);
	    		newEnvaseStock.setStock(newStock);
		    	setEnvaseStock.add(newEnvaseStock);
	    	}
	    }		
	    newStock.setSetEnvaseStock(setEnvaseStock);    
	    
	    
	    Set<ProductoStock> setProductoStock =  stock.getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = stock.getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	if(productoStock.getId()>0) {
		    	productoStock.setStock(newStock);
		    	setProductoStock.add(productoStock);
	    	}else {
	    		ProductoStock newProductoStock =  productoStockService.addProductoStock(productoStock);
	    		newProductoStock.setStock(newStock);
	    		setProductoStock.add(newProductoStock);
	    	}
	    }		
	    newStock.setSetProductoStock(setProductoStock);  	    
	      
	    Stock stockUpd = stockService.updateStock(newStock); 
		//****
		
		
        return new ResponseEntity<Stock>(stockUpd, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody Stock stock) {
		LOG.info("Actualizando stock con id: {}", stock);
		Stock currentStock = stockService.getStockById(id);

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
	    
	    
        if (currentStock == null){
            LOG.info("Linea de stock con id {} no encontrado", id);
            return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
        }

        Stock stockUpd = stockService.updateStock(stock);
        LOG.info("Actualizando stock con id: {}", stockUpd);
        return new ResponseEntity<Stock>(stockUpd, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletestock(@PathVariable("id") int id){
        LOG.info("Eliminando stock con id: {}", id);
        Stock stock = stockService.getStockById(id);

        if (stock == null){
            LOG.info("No se puede eliminar. Linea de stock con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        stockService.deleteStock(id);
        LOG.info("Stock con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
