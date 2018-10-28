package com.nantia.controller;

import com.nantia.service.IListaPrecioService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nantia.model.ListaPrecio;
import com.nantia.model.ProductoLista;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/listaprecio")
public class ListaPrecioController {
	
private final Logger LOG = LoggerFactory.getLogger(ListaPrecioController.class);
	
	@Autowired
	IListaPrecioService listaPrecioService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ListaPrecio>> getAllListaPrecio() {
		LOG.info("trayendo todos las listas de precios"); 
        List<ListaPrecio> listaPrecio = listaPrecioService.getAllListaPrecio();
        
        if (listaPrecio == null || listaPrecio.isEmpty()){
            LOG.info("no se encontraron listas de precios");
            return new ResponseEntity<List<ListaPrecio>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<ListaPrecio>>(listaPrecio, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ListaPrecio> getListaPrecioById(@PathVariable("id") Integer id) {
		LOG.info("trayendo lista de precio por id: {}", id);
		ListaPrecio listaPrecio = listaPrecioService.getListaPrecioById(id);

		 if (listaPrecio == null){
	            LOG.info("lista de precio con id  {} no encontrado", id);
	            return new ResponseEntity<ListaPrecio>(HttpStatus.NOT_FOUND);
	        }
	        
        return new ResponseEntity<ListaPrecio>(listaPrecio, HttpStatus.OK);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ListaPrecio> addListaPrecio(@RequestBody ListaPrecio listaPrecio) {
		LOG.info("creando lista de precios: {}", listaPrecio);
				
		if(listaPrecio.getSetProductoLista() != null)
		{
			Set<ProductoLista> setProductoLista =  listaPrecio.getSetProductoLista();						
			Iterator<ProductoLista> iteProLis = listaPrecio.getSetProductoLista().iterator();
		    while(iteProLis.hasNext()) {
		    	ProductoLista productoLista = iteProLis.next();
		    	productoLista.setListaPrecio(listaPrecio);
		    	setProductoLista.add(productoLista);
		    }		
		    listaPrecio.setProductoLista(setProductoLista);    
		}
		if (listaPrecioService.existe(listaPrecio)){
            LOG.info("La lista de precios con nombre " + listaPrecio.getNombreLista() + " ya existe");
            return new ResponseEntity<ListaPrecio>(HttpStatus.CONFLICT);
        }
			
		ListaPrecio newListaPrecio = listaPrecioService.addListaPrecio(listaPrecio);
        
        return new ResponseEntity<ListaPrecio>(newListaPrecio, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<ListaPrecio> updateListaPrecio(@PathVariable int id, @RequestBody ListaPrecio listaPrecio) {
		LOG.info("Actualizando lista de precios: {}", listaPrecio);
		ListaPrecio currentListaPrecio = listaPrecioService.getListaPrecioById(id);

		Set<ProductoLista> setProductoLista =  listaPrecio.getSetProductoLista();						
		Iterator<ProductoLista> iteProLis = listaPrecio.getSetProductoLista().iterator();
	    while(iteProLis.hasNext()) {
	    	ProductoLista productoLista = iteProLis.next();
	    	productoLista.setListaPrecio(listaPrecio);
	    	setProductoLista.add(productoLista);
	    }		
	    listaPrecio.setProductoLista(setProductoLista);  
	    
        if (currentListaPrecio == null){
            LOG.info("Lista de precio con id {} no encontrado", id);
            return new ResponseEntity<ListaPrecio>(HttpStatus.NOT_FOUND);
        }

        ListaPrecio listaPrecioUpd = listaPrecioService.updateListaPrecio(listaPrecio);
        return new ResponseEntity<ListaPrecio>(listaPrecioUpd, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteListaPrecio(@PathVariable("id") int id){
        LOG.info("Eliminando cliente con id: {}", id);
        ListaPrecio listaPrecio = listaPrecioService.getListaPrecioById(id);

        if (listaPrecio == null){
            LOG.info("No se puede eliminar. Lista de precio con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        listaPrecioService.deleteListaPrecio(id);
        LOG.info("Lista de precio con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
