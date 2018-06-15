package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 755685454196057512L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nombreRol", unique=true)
	private String nombreRol;

	public Rol(long id, String nombreRol) {
		super();
		this.id = id;
		this.nombreRol = nombreRol;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
