package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "producto", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"nombre", "presentacion"})}) 
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 3258306168943102469L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "presentacion")
	private String presentacion;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "retornable")
	private boolean retornable;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isRetornable() {
		return retornable;
	}

	public void setRetornable(boolean retornable) {
		this.retornable = retornable;
	}
	
}
