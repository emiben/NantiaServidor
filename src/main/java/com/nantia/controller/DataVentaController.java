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

import com.nantia.model.Cliente;
import com.nantia.model.DataPago;
import com.nantia.model.DataVenta;
import com.nantia.model.EnvaseStock;
import com.nantia.model.EnvasesTipos;
import com.nantia.model.Fabrica;
import com.nantia.model.Pago;
import com.nantia.model.Producto;
import com.nantia.model.ProductoStock;
import com.nantia.model.ProductoVenta;
import com.nantia.model.Reparto;
import com.nantia.model.Stock;
import com.nantia.model.Usuario;
import com.nantia.model.Vehiculo;
import com.nantia.model.Venta;
import com.nantia.service.IClienteService;
import com.nantia.service.IEnvaseStockService;
import com.nantia.service.IFabricaService;
import com.nantia.service.IPagoService;
import com.nantia.service.IProductoVentaService;
import com.nantia.service.IRepartoService;
import com.nantia.service.IStockService;
import com.nantia.service.IUsuarioService;
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
	@Autowired
	IProductoVentaService productoVentaService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IUsuarioService usuarioService;

	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Venta> addVenta(@RequestBody DataVenta dataVenta) {
		
		String result = "";
		
		Venta venta = new Venta();
				
		venta.setFecha(dataVenta.getFecha());
		venta.setUsuario(dataVenta.getUsuario());
		venta.setDescuento(dataVenta.getDescuento());
		venta.setTotalventa(dataVenta.getTotalventa());
		venta.setIvatotal(dataVenta.getIvatotal());
		venta.setPagototal(dataVenta.getPagototal());
		venta.setObservaciones(dataVenta.getObservaciones());

		
		Set<ProductoVenta> setProdDataVenta =  dataVenta.getSetProductoVenta();						
		Iterator<ProductoVenta> itePVenta = dataVenta.getSetProductoVenta().iterator();
	    while(itePVenta.hasNext()) {
	    	ProductoVenta prodVenta = itePVenta.next();
	    	prodVenta.setVenta(venta);
	    	setProdDataVenta.add(productoVentaService.addProductoVenta(prodVenta));	    	
	    }		
	    venta.getSetProductoVenta().retainAll(setProdDataVenta);
	    venta.getSetProductoVenta().addAll(setProdDataVenta);
	    
	    Usuario usuario = usuarioService.getUsuarioById(dataVenta.getUsuario().getId()); 
		usuario.setSaldoCaja(dataVenta.getUsuario().getSaldoCaja());
		Usuario usuarioUpd = usuarioService.updateUsuario(usuario);
		venta.setUsuario(usuarioUpd);		
		
        if(dataVenta.getFabricaid() != null)
        {
        	Fabrica fabricaUpd = fabricaService.getFabricaById(dataVenta.getFabricaid());
    		
        	//venta.setFabrica(fabricaService.getFabricaById(dataVenta.getFabricaid()));
        	Fabrica fabricaNew = actualizarStockFabricaPorVenta(venta.getSetProductoVenta(), fabricaUpd, -1); 
        	venta.setFabrica(fabricaUpd);
        }
        else
        {
        	venta.setReparto(repartoService.getRepartoById(dataVenta.getRepartoid()));
        }
        
        
        
		if(dataVenta.getDatapago() != null)
		{
			DataPago datapago = dataVenta.getDatapago();
			Pago pago = new Pago();
			
			Cliente cliente = clienteService.getClienteById(datapago.getClienteid());
			cliente.setSaldo(cliente.getSaldo() - datapago.getDifSaldo());
			Cliente clienteUpd = clienteService.updateCliente(cliente);
						
			pago.setCliente(cliente);
			pago.setFechapago(datapago.getFechapago());
			pago.setMonto(datapago.getMonto());
			pago.setVenta(venta);
			
			Pago pagoUpd = pagoService.addPago(pago);
			venta.setCliente(cliente);

		}
		else {
			venta.setCliente(dataVenta.getCliente());			
		}
		
		
		Venta newVentaUpd = ventaService.updateVenta(venta); 
        return new ResponseEntity<Venta>(newVentaUpd, HttpStatus.CREATED);
	}
	
    public Fabrica actualizarStockFabricaPorVenta(Set<ProductoVenta> setProductoVenta, Fabrica fabrica, int coeficiente) {
		
		
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
	    
		return fabrica;
	}

	@RequestMapping(value = "ventasporperiodo/{fechaIni}/{fechaFin}", method = RequestMethod.GET)
	public ResponseEntity<List<Venta>> getVentasPorPeriodo(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin) {
		
		LOG.info("trayendo todas las ventas para las fechas indicadas"); 
		LOG.info("fechaIni:{}, fechaFin:{}", fechaIni, fechaFin); 
		
		//fechaIni = fechaIni + " 00:00:00";
		//fechaFin = fechaFin + " 00:00:00";
		
		List<Venta> ventas;
		
		ventas = ventaService.getVentasPorPeriodo(fechaIni, fechaFin);
	
	    if (ventas == null || ventas.isEmpty()){
	        LOG.info("no se encontraron ventas para las fechas indicadas");
	        return new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
	    }
	
	    return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);		
	}
		

}
