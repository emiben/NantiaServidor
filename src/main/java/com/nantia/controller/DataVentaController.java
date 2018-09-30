package com.nantia.controller;

import java.util.Iterator;
import java.util.Set;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Cliente;
import com.nantia.model.DataVenta;
import com.nantia.model.EnvaseStock;
import com.nantia.model.Fabrica;
import com.nantia.model.Pago;
import com.nantia.model.Producto;
import com.nantia.model.ProductoStock;
import com.nantia.model.ProductoVenta;
import com.nantia.model.Reparto;
import com.nantia.model.Stock;
import com.nantia.model.Vehiculo;
import com.nantia.model.Venta;
import com.nantia.service.IEnvaseStockService;
import com.nantia.service.IFabricaService;
import com.nantia.service.IPagoService;
import com.nantia.service.IRepartoService;
import com.nantia.service.IStockService;
import com.nantia.service.IVehiculoService;
import com.nantia.service.IVentaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/dataventas")
public class DataVentaController {
	
private final Logger LOG = LoggerFactory.getLogger(DataVentaController.class);
	
	@Autowired
	IVentaService ventaService;
	@Autowired
	IFabricaService fabricaService;
	@Autowired
	IStockService stockService;
	@Autowired
	IVehiculoService vehiculoService;
	@Autowired
	IEnvaseStockService envaseStockService;
	@Autowired
	IRepartoService repartoService;
	@Autowired
	IPagoService pagoService;
	

	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Venta> addVenta(@RequestBody DataVenta dataVenta) {
		
		String result = "";
		
		Venta venta = new Venta();
		
		venta.setFecha(dataVenta.getFecha());
		venta.setUsuario(dataVenta.getUsuario());
		//venta.setCliente(dataVenta.getCliente());
		venta.setDescuento(dataVenta.getDescuento());
		venta.setTotalventa(dataVenta.getTotalventa());
		venta.setIvatotal(dataVenta.getIvatotal());
		venta.setPagototal(dataVenta.getPagototal());
		//venta.setFabrica(fabricaService.getFabricaById(dataVenta.getFabricaid()));
		
		Cliente cliente = dataVenta.getCliente();
		
		venta.setCliente(dataVenta.getCliente());
		
		Set<ProductoVenta> setProdDataVenta =  dataVenta.getSetProductoVenta();						
		Iterator<ProductoVenta> itePVenta = dataVenta.getSetProductoVenta().iterator();
	    while(itePVenta.hasNext()) {
	    	ProductoVenta prodVenta = itePVenta.next();
	    	prodVenta.setVenta(venta);
	    	setProdDataVenta.add(prodVenta);	    	
	    }		
	    venta.setSetProductoVenta(setProdDataVenta);
		
		
		Venta newVenta = ventaService.addVenta(venta);

		LOG.info("dataVenta.getFabricaId: {}", dataVenta.getFabricaid()); 
		
        if(dataVenta.getFabricaid() == null)
        {
        	//venta.setReparto(repartoService.getRepartoById(dataVenta.getRepartoid()));
        	//result = actualizarStockRepartoPorVenta(newVenta.getSetProductoVenta(), newVenta.getReparto(), -1);
        }
        else
        {
        	venta.setFabrica(fabricaService.getFabricaById(dataVenta.getFabricaid()));
        	result = actualizarStockFabricaPorVenta(newVenta.getSetProductoVenta(), newVenta.getFabrica(), -1);
        }

        LOG.info("Resultado de actualizar el stock: {}", result);        
        
        Venta newVentaUpd = ventaService.updateVenta(newVenta);   
        

		if(dataVenta.getPago() != null)
		{
			Pago pago = dataVenta.getPago();
			pago.setCliente(dataVenta.getPago().getCliente());
			pago.setFechapago(dataVenta.getPago().getFechapago());
			pago.setMonto(dataVenta.getPago().getMonto());
			pago.setVenta(newVentaUpd);
			Pago pagoUpd = pagoService.addPago(pago);
		}
        
        return new ResponseEntity<Venta>(newVentaUpd, HttpStatus.CREATED);
	}
	
	
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
			    			productoStockFab.setCantidad(productoStockFab.getCantidad() + (productoventa.getCantidad()*coeficiente));
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
	    

	    Set<ProductoStock> setProductoStock =  stockFabrica.getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = stockFabrica.getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	productoStock.setStock(stockFabrica);
	    	setProductoStock.add(productoStock);
	    }		
	    stockFabrica.setSetProductoStock(setProductoStock); 
		//************************************************
		Set<EnvaseStock> setEnvaseStock =  stockFabrica.getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = stockFabrica.getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setStock(stockFabrica);
	    	setEnvaseStock.add(envaseStock);	    	
	    }			      
	    stockFabrica.setSetEnvaseStock(setEnvaseStock);
	    //******************************
	    
	    Stock newStock = stockService.updateStock(stockFabrica); 
	    
	    fabrica.setStock(newStock);
	    
		return result;
	}
		
	public String actualizarStockRepartoPorVenta(Set<ProductoVenta> setProductoVenta, Reparto reparto, int coeficiente) {
		

		
				
		String result = "OK"; 
		boolean parar = false;
		boolean encontro = false;
		Long idProductoStock;
			

		Vehiculo vehiculoRep = reparto.getVehiculo();
		Stock stockReparto = vehiculoRep.getStock();
		Set<ProductoStock> setProductoStockReparto =  vehiculoRep.getStock().getSetProductoStock();		
		Iterator<ProductoStock> iteProStkRep2 = setProductoStockReparto.iterator();
		
	    	    
	    Iterator<ProductoVenta> iteProVenta1 = setProductoVenta.iterator();
	    
	    while(iteProVenta1.hasNext() && !parar && result.substring(0, 2) == "OK") {
	    	encontro = false;
	    	parar = false;
	    	ProductoVenta productoventa = iteProVenta1.next();
	    	Producto productoVta = productoventa.getProducto();
	    	idProductoStock = productoVta.getProductoId();
	    	
	    	if(setProductoStockReparto.size() > 0) {	 
		    	while(iteProStkRep2.hasNext() && !parar && !encontro) {
		    		ProductoStock productoStockRep = iteProStkRep2.next();
		    		Producto productoRep = productoStockRep.getProducto();
		    		LOG.info("productoRep.getId:{}, idProductoStock:{}.",productoRep.getProductoId(), idProductoStock);
			    	if(productoRep.getProductoId() == idProductoStock)
			    	{
			    		LOG.info("productoRep.getProductoId() == idProductoStock.");
			    		encontro = true;
			    		LOG.info("productoStockRep.getCantidad:{}, productoventa.getCantidad:{}.",productoStockRep.getCantidad(), productoventa.getCantidad());
			    		if(productoStockRep.getCantidad() >= productoventa.getCantidad())
			    		{
			    			LOG.info("Hace la cuenta.");
			    			productoStockRep.setCantidad(productoStockRep.getCantidad() + (productoventa.getCantidad()*coeficiente));
			    			//productoStockRep.setStock(stockReparto);
			    			//setProductoStockReparto.add(productoStockRep);
			    		}
			    		else 
			    		{
			    			result = String.format("No hay suficientes productos en fabrica: [envase=%s], Hay %f y necesita %f", productoStockRep.getProducto().getNombre(), productoStockRep.getCantidad(), productoventa.getCantidad());
				    		parar = true;			    		
			    		}		    		
			    	}		    	
		    	}
	    	}
	    	else {
	    		result = String.format("El reparto no tiene productos de ningun tipo");
	    	}
	    	if(encontro)
	    	{
	    		 
	    	}
	    	else
	    		if(result.substring(0, 2) == "OK"){
	    			result = String.format("No se encontro el producto [%s] en el reparto.", productoventa.getProducto().getNombre());
	    		}	    		
	    }		
	    
	    
		//******************************
	    Set<ProductoStock> setProductoStock =  vehiculoRep.getStock().getSetProductoStock();						
		Iterator<ProductoStock> iteProStk = vehiculoRep.getStock().getSetProductoStock().iterator();
	    while(iteProStk.hasNext()) {
	    	ProductoStock productoStock = iteProStk.next();
	    	productoStock.setStock(stockReparto);
	    	setProductoStock.add(productoStock);
	    }		
		stockReparto.setSetProductoStock(setProductoStockReparto); 
		//******************************
		Set<EnvaseStock> setEnvaseStock =  vehiculoRep.getStock().getSetEnvaseStock();						
		Iterator<EnvaseStock> iteEnvStk = vehiculoRep.getStock().getSetEnvaseStock().iterator();
	    while(iteEnvStk.hasNext()) {
	    	EnvaseStock envaseStock = iteEnvStk.next();
	    	envaseStock.setStock(stockReparto);
	    	setEnvaseStock.add(envaseStock);	    	
	    }			      
	    stockReparto.setSetEnvaseStock(setEnvaseStock);
		//******************************
	    
	    Stock newStock = stockService.updateStock(stockReparto); 
	    
	    vehiculoRep.setStock(newStock);
	    reparto.setVehiculo(vehiculoRep);
	    
	    Vehiculo vehiculoUpd = vehiculoService.updateVehiculo(vehiculoRep);
	   
		return result;
	}
		
		
	

}
