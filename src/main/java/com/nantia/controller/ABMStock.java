package com.nantia.controller;

import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nantia.model.EnvaseStock;
import com.nantia.model.Fabrica;
import com.nantia.model.Producto;
import com.nantia.model.ProductoStock;
import com.nantia.model.ProductoVenta;
import com.nantia.model.Stock;
import com.nantia.service.IEnvaseStockService;
import com.nantia.service.IFabricaService;
import com.nantia.service.IRepartoService;
import com.nantia.service.IStockService;
import com.nantia.service.IVehiculoService;


public class ABMStock {
	
private final Logger LOG = LoggerFactory.getLogger(ABMStock.class);
	
	@Autowired
	IRepartoService repartoService;
	@Autowired
	IFabricaService fabricaService;
	@Autowired
	IStockService stockService;
	@Autowired
	IVehiculoService vehiculoService;
	@Autowired
	IEnvaseStockService envaseStockService;
	

	public String actualizarStockFabricaPorVenta(Set<ProductoVenta> setProductoVenta, Fabrica fabrica, int coeficiente) {
		
		
		String result = "OK"; 
		boolean parar = false;
		boolean encontro = false;
		Long idProductoStock;
			
	   //Proceso el Set de productosStock FABRICA
		Stock stockFabrica = fabrica.getStock();
		Set<ProductoStock> setProductoStockFabrica =  fabrica.getStock().getSetProductoStock();		
		Iterator<ProductoStock> iteProStkFab2 = setProductoStockFabrica.iterator();
		
	    	    
	    Iterator<ProductoVenta> iteProVenta1 = setProductoVenta.iterator();
	    
	    while(iteProVenta1.hasNext() && !parar && result.substring(0, 2) == "OK") {
	    	encontro = false;
	    	parar = false;
	    	ProductoVenta productoventa = iteProVenta1.next();
	    	Producto productoVta = productoventa.getProducto();
	    	idProductoStock = productoVta.getProductoId();
	    	
	    	if(setProductoStockFabrica.size() > 0) {	 
		    	while(iteProStkFab2.hasNext() && !parar && !encontro) {
		    		ProductoStock productoStockFab = iteProStkFab2.next();
		    		Producto productoFab = productoStockFab.getProducto();
		    		LOG.info("productoFab.getId: {}, idProductoStock:{}.",productoFab.getProductoId(), idProductoStock);
			    	if(productoFab.getProductoId() == idProductoStock)
			    	{
			    		encontro = true;
			    		if(productoStockFab.getCantidad() >= productoventa.getCantidad())
			    		{
			    			productoStockFab.setCantidad(productoStockFab.getCantidad() - productoventa.getCantidad());
			    			productoStockFab.setStock(stockFabrica);
			    			setProductoStockFabrica.add(productoStockFab);
			    		}
			    		else 
			    		{
			    			result = String.format("No hay suficientes productos en fabrica: [envase=%s], Hay %d y necesita %d", productoStockFab.getProducto().getNombre(), productoStockFab.getCantidad(), productoventa.getCantidad());
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
	    			result = String.format("No se encontro el producto [%s] en fabrica.", productoventa.getProducto().getNombre());
	    		}	    		
	    }		
	    fabrica.setStock(stockFabrica);
	    
	    

		Set<EnvaseStock> setEnvaseStock =  fabrica.getStock().getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = fabrica.getStock().getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setStock(stockFabrica);
	    	setEnvaseStock.add(envaseStockService.addEnvaseStock(envaseStock));	    	
	    }		
	    
	    //stock.setSetEnvaseStock(setEnvaseStock);    
	    stockFabrica.setSetEnvaseStock(setEnvaseStock);
	    
	    
	    if(result.substring(0, 2) != "OK"){
	    	LOG.info("Entro 11");
            return result;               
        }
	    else {
	    	LOG.info("Entro 12");
    	
	    	LOG.info("actualizando fabrica: {}", fabrica);
	    	LOG.info("actualizando fabricaid: {}", fabrica.getId());
	    	//LOG.info("actualizando fabrica22: {}", fabricaService.getFabricaById(fabrica.getId()));
			/*Fabrica currentFabrica = fabricaService.getFabricaById(fabrica.getId());
			    
	        if (currentFabrica == null){
	            LOG.info("Fabrica con id {} no encontrado", fabrica.getId());
	        }*/
	        //LOG.info("Id: ()", currentFabrica.getId());
	        Fabrica fabricaUpd = fabricaService.updateFabrica(fabrica);
	        
	    }
				
		return result;
	}
		
	


}
