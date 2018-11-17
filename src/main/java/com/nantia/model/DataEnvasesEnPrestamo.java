package com.nantia.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DataEnvasesEnPrestamo implements Serializable{
	

	private static final long serialVersionUID = 2274412475721503784L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private String nombre1;
	private String nombre2;
	private String descripcion;
	private int cantidad;

	public DataEnvasesEnPrestamo() {
		super();
	}

	public DataEnvasesEnPrestamo(String nombre1, String nombre2, String descripcion, int cantidad) {
		super();
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}


	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
