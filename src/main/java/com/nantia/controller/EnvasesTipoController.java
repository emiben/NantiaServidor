package com.nantia.controller;

import java.util.List;

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
import com.nantia.model.EnvasesTipos;
import com.nantia.service.IEnvasesTipoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/envasesTipo")
public class EnvasesTipoController {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	IEnvasesTipoService envasesTiposService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EnvasesTipos>> getAllEnvasesTipos() {
		LOG.info("trayendo todos los tipos de envases");
        List<EnvasesTipos> envasesTipos = envasesTiposService.getAllEnvasesTipo();
        
        if (envasesTipos == null || envasesTipos.isEmpty()){
            LOG.info("no se encontraron tipos de envases");
            return new ResponseEntity<List<EnvasesTipos>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<EnvasesTipos>>(envasesTipos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<EnvasesTipos> getEnvasesTiposById(@PathVariable("id") Integer id) {
		LOG.info("trayendo envases tipo por id: {}", id);
		EnvasesTipos envasesTipos = envasesTiposService.getEnvasesTipoById(id);

        if (envasesTipos == null){
            LOG.info("Tipo de envase por id {} no encontrado", id);
            return new ResponseEntity<EnvasesTipos>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<EnvasesTipos>(envasesTipos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EnvasesTipos> addEnvasesTipos(@RequestBody EnvasesTipos envasesTipos) {
		LOG.info("creando un nuevo tipo de envase: {}", envasesTipos);

        if (envasesTiposService.existe(envasesTipos)){
            LOG.info("el tipo de envase con descripción " + envasesTipos.getDescripcion() + " ya existe");
            return new ResponseEntity<EnvasesTipos>(HttpStatus.CONFLICT);
        }

        EnvasesTipos newEnvasesTipos = envasesTiposService.addEnvasesTipo(envasesTipos);

        return new ResponseEntity<EnvasesTipos>(newEnvasesTipos, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<EnvasesTipos> updateEnvasesTipos(@PathVariable int id, @RequestBody EnvasesTipos envasesTipos) {
		LOG.info("actualizando el tipo de envase: {}", envasesTipos);
		EnvasesTipos currentEnvasesTipos = envasesTiposService.getEnvasesTipoById(id);

        if (currentEnvasesTipos == null){
            LOG.info("Tipo de envase con id {} no encontrado", id);
            return new ResponseEntity<EnvasesTipos>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentEnvasesTipos.getId());
        EnvasesTipos envasesTiposUpd = envasesTiposService.updateEnvasesTipo(envasesTipos);
        return new ResponseEntity<EnvasesTipos>(envasesTiposUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEnvasesTipos(@PathVariable("id") int id){
        LOG.info("Eliminando tipo de envase con id: {}", id);
        EnvasesTipos envasesTipos = envasesTiposService.getEnvasesTipoById(id);

        if (envasesTipos == null){
            LOG.info("No se puede eliminar. Tipo de envase con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        envasesTiposService.deleteEnvasesTipo(id);
        LOG.info("Tipo de envase con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
