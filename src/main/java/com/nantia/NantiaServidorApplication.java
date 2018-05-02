package com.nantia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nantia.repo.UsuarioRepository;

@SpringBootApplication
public class NantiaServidorApplication{

	@Autowired
	UsuarioRepository repository;
	
	public static void main(String[] args){
		SpringApplication.run(NantiaServidorApplication.class, args);
	}

}
