package com.nantia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Usuario;
import com.nantia.repo.UsuarioRepository;

@Service
public class LoginService implements ILoginService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario getUsuarioByUsrAndPass(String nombreUsuario, String contrasenia) {
		return usuarioRepository.findByUsuarioAndContrasenia(nombreUsuario, contrasenia);
	}

}
