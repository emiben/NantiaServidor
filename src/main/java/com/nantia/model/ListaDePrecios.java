package com.nantia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ListaDePrecios")
public class ListaDePrecios implements Serializable{

private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "Nombre", unique=true)
	private String nombre;

	@Column(name = "FechaAlta")
	private Date fechaAlta;
	
	protected ListaDePrecios() {
	}

	public ListaDePrecios(String nombre, Date fechaAlta) {
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return String.format("ListaDePrecios[id=%d, fechaAlta='%s', nombre='%s']",
				id, fechaAlta, nombre);
	}
	
	
}
