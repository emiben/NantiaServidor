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

import com.nantia.model.Usuario;
import com.nantia.service.IUsuarioService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
	
	private final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	IUsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		LOG.info("getting all users");
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        if (usuarios == null || usuarios.isEmpty()){
            LOG.info("no se encontraron usuarios");
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Integer id) {
		LOG.info("getting user with id: {}", id);
        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		LOG.info("creating new user: {}", usuario);

        if (usuarioService.existe(usuario)){
            LOG.info("a user with name " + usuario.getUsuario() + " already exists");
            return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
        }

        Usuario newUsuario = usuarioService.addUsuario(usuario);

        return new ResponseEntity<Usuario>(newUsuario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> updateUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
		LOG.info("updating user: {}", usuario);
        Usuario currentUsuario = usuarioService.getUsuarioById(id);

        if (currentUsuario == null){
            LOG.info("User with id {} not found", id);
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        Usuario usuarioUpd = usuarioService.updateUsuario(usuario);
        return new ResponseEntity<Usuario>(usuarioUpd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") int id){
        LOG.info("deleting user with id: {}", id);
        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        usuarioService.deleteUsuario(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
}


