package com.nantia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nantia.repo.ClienteRepository;
import com.nantia.repo.UsuarioRepository;
import com.nantia.repo.ListaDePreciosRepository;

@SpringBootApplication
public class NantiaServidorApplication{


	@Autowired
	ClienteRepository repository;
	
	@Autowired
	ListaDePreciosRepository ListaDePrecios;
	
	public static void main(String[] args){
		SpringApplication.run(NantiaServidorApplication.class, args);
	}

}
