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

import com.nantia.model.Producto;
import com.nantia.model.ProductoLista;
import com.nantia.service.IProductoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/productos")
public class ProductoController {
	
	private final Logger LOG = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	IProductoService productoService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Producto>> getAllProductos() {
		LOG.info("getting all products");
        List<Producto> productos = productoService.getAllProductos();

        if (productos == null || productos.isEmpty()){
            LOG.info("no se encontraron productos");
            return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Producto> getProductoById(@PathVariable("id") Integer id) {
		LOG.info("getting product with id: {}", id);
        Producto producto = productoService.getProductoById(id);

        if (producto == null){
            LOG.info("product with id {} not found", id);
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Producto> addProducto(@RequestBody Producto producto) {
		LOG.info("Creando el nuevo producto: {}", producto);
		
        if (productoService.existe(producto)){
            LOG.info("El producto con nombre " + producto.getNombre() + " ya existe");
            return new ResponseEntity<Producto>(HttpStatus.CONFLICT);
        }

        Producto newProducto = productoService.addProducto(producto);

        return new ResponseEntity<Producto>(newProducto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Producto> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
		LOG.info("updating product: {}", producto);
        Producto currentProducto = productoService.getProductoById(id);

        if (currentProducto == null){
            LOG.info("Product with id {} not found", id);
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }

      	    
        Producto productoUpd = productoService.updateProducto(producto);
        return new ResponseEntity<Producto>(productoUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProducto(@PathVariable("id") int id){
        LOG.info("deleting product with id: {}", id);
        Producto producto = productoService.getProductoById(id);

        if (producto == null){
            LOG.info("Unable to delete. Product with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        productoService.deleteProducto(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
}
