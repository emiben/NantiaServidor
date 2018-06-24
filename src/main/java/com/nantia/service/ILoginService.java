package com.nantia.service;

import com.nantia.model.Usuario;

public interface ILoginService {
	
	Usuario getUsuarioByUsrAndPass(String nombreUsuario, String contrasenia);
	
}
