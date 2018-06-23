package com.nantia.controller;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nantia.model.Cliente;
import com.nantia.model.DiaSemana;
import com.nantia.service.IClienteService;
import com.nantia.service.IDiaSemanaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/diasemana")
public class DiaSemanaController {
	
	private final Logger LOG = LoggerFactory.getLogger(DiaSemanaController.class);
	
	@Autowired
	IDiaSemanaService diaSemanaService;
	@Autowired
	IClienteService clienteService; 
	
	@RequestMapping(value = "{clienteId}/{diaSemana}", method = RequestMethod.POST)
	public ResponseEntity<DiaSemana> addDiaSemanaCliente(@PathVariable Long clienteId,@PathVariable String diaSemana) {
			
		LOG.info("creando un nuevo DiaSemana en prestamo: {}", diaSemana);
		
		LOG.info("clienteId, diaSemana: - {} - {} -", clienteId, diaSemana);
		
		Cliente cliente = clienteService.getClienteById(clienteId);
				
		
		
		String[] diasSem = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"};		
		boolean diaCorrecto = Arrays.asList(diasSem).contains(diaSemana);
		
        if (!diaCorrecto){
            LOG.info("el día " + diaSemana + " es incorrecto");
            return new ResponseEntity<DiaSemana>(HttpStatus.CONFLICT);
        }
        else
        {        	
        	DiaSemana dia = DiaSemana.getDiaSemana(diaSemana);
        	
            cliente.addDias(dia);
            clienteService.updateCliente(cliente);

        	LOG.info("Se ingresará el dia de la semana {} para el cliente " + cliente.getNombre1());
        	return new ResponseEntity<DiaSemana>(dia, HttpStatus.CREATED);
        }
        
	}

}
