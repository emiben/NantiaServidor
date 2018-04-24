package com.nantia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	List<Usuario> findByApellido(String apellido);
}
