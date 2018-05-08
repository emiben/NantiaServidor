package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Usuario findByUsuario(String usuario);
	List<Usuario> findByNombre(String nombre);
	List<Usuario> findByApellido(String apellido);
	List<Usuario> findByRol(String rol);
	Usuario findByUsuarioAndContrasenia(String nombreUsuario, String contrasenia);
}
