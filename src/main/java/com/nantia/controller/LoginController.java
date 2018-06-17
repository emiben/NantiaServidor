package com.nantia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantia.model.Login;
import com.nantia.model.Usuario;
import com.nantia.service.ILoginService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/login")
public class LoginController {
	
	private final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	ILoginService loginService;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> login(@RequestBody Login login) {
		LOG.info("Autenticando: ", login);
		
		Usuario usuario = loginService.getUsuarioByUsrAndPass(login.getNombreUsuario(), login.getContrasenia());
        if (usuario == null){
            LOG.info("El usuario y la contrasenia no coinciden.");
            return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

}
