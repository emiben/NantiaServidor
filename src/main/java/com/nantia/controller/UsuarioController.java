package com.nantia.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Usuario;
import com.nantia.repo.UsuarioRepository;

@RestController
public class UsuarioController {
	@Autowired
	UsuarioRepository repository;
	
	@RequestMapping("/save")
	public String process(){
		// save a single Usuario
		repository.save(new Usuario("UsuarioAdmin", "Usuario", "Admin", 1, "pass1234"));
		repository.save(new Usuario("UsuarioAdmin2", "Usuario2", "Admin2", 1, "pass1234"));
		
		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "";
		
		for(Usuario cust : repository.findAll()){
			result += cust.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findOne(id).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("a") String apellido){
		String result = "";
		
		for(Usuario cust: repository.findByApellido(apellido)){
			result += cust.toString() + "<br>"; 
		}
		
		return result;
	}
}


