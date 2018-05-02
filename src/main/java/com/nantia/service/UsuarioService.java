package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Usuario;
import com.nantia.repo.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario findByUsuario(String usuario) {
		Usuario usr = usuarioRepository.findByUsuario(usuario);
		return usr;
	}
	
	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> list = new ArrayList<>();
		usuarioRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Usuario getUsuarioById(long usuarioId) {
		Usuario usr = usuarioRepository.findOne(usuarioId);
		return usr;
	}

	@Override
	public synchronized Usuario addUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deleteUsuario(long usuarioId) {
		usuarioRepository.delete(getUsuarioById(usuarioId));
	}

	@Override
	public boolean existe(Usuario usuario) {
		 return findByUsuario(usuario.getUsuario()) != null;
	}

}
