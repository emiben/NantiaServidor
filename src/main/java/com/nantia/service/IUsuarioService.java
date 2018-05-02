package com.nantia.service;

import java.util.List;
import com.nantia.model.Usuario;

public interface IUsuarioService {
	List<Usuario> getAllUsuarios();
	Usuario getUsuarioById(long usuarioId);
	Usuario addUsuario(Usuario usuario);
	Usuario updateUsuario(Usuario usuario);
	void deleteUsuario(long usuarioId);
	boolean existe(Usuario usuario);
}
