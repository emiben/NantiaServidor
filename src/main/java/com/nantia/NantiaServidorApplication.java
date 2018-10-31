package com.nantia;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nantia.model.Rol;
import com.nantia.model.Usuario;
import com.nantia.repo.ClienteRepository;
import com.nantia.service.IRolService;
import com.nantia.service.IUsuarioService;


@SpringBootApplication
public class NantiaServidorApplication implements CommandLineRunner{


	@Autowired
	ClienteRepository repository;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IRolService rolService;

	public static void main(String[] args){
		SpringApplication.run(NantiaServidorApplication.class, args);
      
	}
	
	
	
    @Override
    public void run(String... arg0) throws Exception {

    	List<Usuario> usuarios = usuarioService.getAllUsuarios();

        if (usuarios == null || usuarios.isEmpty()){
        	
        	Rol rolAdm = new Rol();        	
        	rolAdm.setNombreRol("Administarador");
        	
        	if (!rolService.existe(rolAdm)){
        		Rol newRolAdm = rolService.addRol(rolAdm);                              
            }
        	
        	Rol rolVen = new Rol();
        	rolVen.setNombreRol("Vendedor");
        	
        	if (!rolService.existe(rolVen)){
        		Rol newRolVen = rolService.addRol(rolVen);	                
            }
            
            
            Usuario usuAdm = new Usuario();
            usuAdm.setNombre("Administrador");
            usuAdm.setApellido("Administrador");
            usuAdm.setRol(rolAdm);
            usuAdm.setContrasenia("Admin");
            usuAdm.setUsuario("Admin");
            usuAdm.setEsVendedor(true);
            usuAdm.setSaldoCaja(0);
            
            Usuario usuarioUpd = usuarioService.addUsuario(usuAdm);
       
        }
    }
	

}
