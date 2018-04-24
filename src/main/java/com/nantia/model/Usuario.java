package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "usuario")
	private String usuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "rol")
	private int rol;
	
	@Column(name = "contrasenia")
	private String contrasenia;

	protected Usuario() {
	}

	public Usuario(String usuario, String nombre, String apellido, int rol, String contrasenia) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return String.format("Usuario[id=%d, usuario='%s', nombre='%s', apellido='%s', rol=%d, contrasenia='%s']",
				id, usuario, nombre, apellido, rol, contrasenia);
	}
}