package com.nantia.controller;

import java.util.List;
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
import com.nantia.model.Fabrica;
import com.nantia.service.IFabricaService;;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/fabrica")
public class FabricaController {
	
private final Logger LOG = LoggerFactory.getLogger(FabricaController.class);
	
	@Autowired
	IFabricaService fabricaService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Fabrica>> getAllFabricas() {
		LOG.info("trayendo todos las fabricas"); 
        List<Fabrica> fabricas = fabricaService.getAllFabrica();
        
        if (fabricas == null || fabricas.isEmpty()){
            LOG.info("no se encontraron fabricas");
            return new ResponseEntity<List<Fabrica>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Fabrica>>(fabricas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Fabrica> getFabricaById(@PathVariable("id") Integer id) {
		LOG.info("trayendo fabrica por id: {}", id);
		Fabrica fabrica = fabricaService.getFabricaById(id);

        if (fabrica == null){
            LOG.info("fabrica por id {} no encontrada", id);
            return new ResponseEntity<Fabrica>(HttpStatus.NOT_FOUND);
        } 	

        return new ResponseEntity<Fabrica>(fabrica, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Fabrica> addFabrica(@RequestBody Fabrica fabrica) {
		LOG.info("creando una nueva fabrica: {}", fabrica);

        if (fabricaService.existe(fabrica)){
            LOG.info("la fabrica: " + fabrica.getNombre() + " ya existe");
            return new ResponseEntity<Fabrica>(HttpStatus.CONFLICT);
        }

        Fabrica newFabrica = fabricaService.addFabrica(fabrica);

        return new ResponseEntity<Fabrica>(newFabrica, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Fabrica> updateFabrica(@PathVariable int id, @RequestBody Fabrica fabrica) {
		LOG.info("actualizando fabrica: {}", fabrica);
		Fabrica currentFabrica = fabricaService.getFabricaById(id);

        if (currentFabrica == null){
            LOG.info("Fabrica con id {} no encontrado", id);
            return new ResponseEntity<Fabrica>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentFabrica.getId());
        Fabrica fabricaUpd = fabricaService.updateFabrica(fabrica);
        return new ResponseEntity<Fabrica>(fabricaUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFabrica(@PathVariable("id") int id){
        LOG.info("Eliminando fabrica con id: {}", id);
        Fabrica fabrica = fabricaService.getFabricaById(id);

        if (fabrica == null){
            LOG.info("No se puede eliminar. Fabrica con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        fabricaService.deleteFabrica(id);
        LOG.info("Fabrica con id: {} eliminanda con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
