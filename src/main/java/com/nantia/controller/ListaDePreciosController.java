package com.nantia.controller;

import java.util.List;

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

import com.nantia.model.ListaDePrecios;
import com.nantia.service.IListaDePreciosService;

@RestController
@RequestMapping("api/listaDePrecios")
public class ListaDePreciosController {

	private final Logger LOG = LoggerFactory.getLogger(ListaDePreciosController.class);
		
	@Autowired
	IListaDePreciosService listaDePreciosService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ListaDePrecios>> getAllListaDePrecios() {
		LOG.info("Onteniendo todas las listas de precios");
        List<ListaDePrecios> listaDePrecios = listaDePreciosService.getAllListaDePrecios();

        if (listaDePrecios == null || listaDePrecios.isEmpty()){
            LOG.info("No se encontraron listas de precios");
            return new ResponseEntity<List<ListaDePrecios>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<ListaDePrecios>>(listaDePrecios, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ListaDePrecios> getListaDePreciosById(@PathVariable("id") Integer id) {
		LOG.info("getting user with id: {}", id);
		ListaDePrecios listaDePrecios = listaDePreciosService.getListaDePreciosById(id);

        if (listaDePrecios == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<ListaDePrecios>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ListaDePrecios>(listaDePrecios, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ListaDePrecios> addListaDePrecios(@RequestBody ListaDePrecios listaDePrecios) {
		LOG.info("creating new user: {}", listaDePrecios);

        if (listaDePreciosService.existe(listaDePrecios)){
            LOG.info("a ListaDePrecios with name " + listaDePrecios.getNombre() + " already exists");
            return new ResponseEntity<ListaDePrecios>(HttpStatus.CONFLICT);
        }

        ListaDePrecios newListaDePrecios = listaDePreciosService.addListaDePrecios(listaDePrecios);

        return new ResponseEntity<ListaDePrecios>(newListaDePrecios, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<ListaDePrecios> updateListaDePrecios(@PathVariable int id, @RequestBody ListaDePrecios listaDePrecios) {
		LOG.info("updating user: {}", listaDePrecios);
		ListaDePrecios currentListaDePrecios = listaDePreciosService.getListaDePreciosById(id);

        if (currentListaDePrecios == null){
            LOG.info("ListaDePrecios with id {} not found", id);
            return new ResponseEntity<ListaDePrecios>(HttpStatus.NOT_FOUND);
        }

        ListaDePrecios listaDePreciosUpd = listaDePreciosService.updateListaDePrecios(listaDePrecios);
        return new ResponseEntity<ListaDePrecios>(listaDePreciosUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteListaDePrecios(@PathVariable("id") int id){
        LOG.info("deleting ListaDePrecios with id: {}", id);
        ListaDePrecios listaDePrecios = listaDePreciosService.getListaDePreciosById(id);

        if (listaDePrecios == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        listaDePreciosService.deleteListaDePrecios(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
}
