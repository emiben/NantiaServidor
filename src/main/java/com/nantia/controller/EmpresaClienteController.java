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

import com.nantia.model.Empresa;
import com.nantia.service.IEmpresaClienteService;

@RestController
@RequestMapping("api/empresas")
public class EmpresaClienteController {
	
	private final Logger LOG = LoggerFactory.getLogger(EmpresaClienteController.class);
	
	@Autowired
	IEmpresaClienteService empresaClienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Empresa>> getAllEmpresas() {
		LOG.info("trayendo todos las empresas");
        List<Empresa> empresas = empresaClienteService.getAllEmpresas();
        
        if (empresas == null || empresas.isEmpty()){
            LOG.info("no se encontraron empresas");
            return new ResponseEntity<List<Empresa>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") Integer id) {
		LOG.info("trayendo empresa por id: {}", id);
		Empresa empresa = empresaClienteService.getEmpresaById(id);

        if (empresa == null){
            LOG.info("cliente por id {} no encontrado", id);
            return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Empresa> addEmpresa(@RequestBody Empresa empresa) {
		LOG.info("creando un nuevo empresa: {}", empresa);

        if (empresaClienteService.existe(empresa)){
            LOG.info("el cliente con nombre " + empresa.getTipoDocumento() + " ya existe");
            return new ResponseEntity<Empresa>(HttpStatus.CONFLICT);
        }

        Empresa newEmpresa = empresaClienteService.addEmpresa(empresa);

        return new ResponseEntity<Empresa>(newEmpresa, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Empresa> updateEmpresa(@PathVariable int id, @RequestBody Empresa empresa) {
		LOG.info("actualizando empresa: {}", empresa);
		Empresa currentEmpresa = empresaClienteService.getEmpresaById(id);

        if (currentEmpresa == null){
            LOG.info("Empresa con id {} no encontrado", id);
            return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
        }
        LOG.info("Id: ()", currentEmpresa.getId());
        Empresa empresaUpd = empresaClienteService.updateEmpresa(empresa);
        return new ResponseEntity<Empresa>(empresaUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmpresa(@PathVariable("id") int id){
        LOG.info("Eliminando empresa con id: {}", id);
        Empresa empresa = empresaClienteService.getEmpresaById(id);

        if (empresa == null){
            LOG.info("No se puede eliminar. Empresa con id {} no encontrado", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        empresaClienteService.deleteEmpresa(id);
        LOG.info("Empresa con id: {} eliminando con áxito", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
