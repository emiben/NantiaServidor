package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable{
	

	private static final long serialVersionUID = -8107461602428099277L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "coordLon")
	private String coordLon;

	@Column(name = "coordLat")
	private String coordLat;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "departamento")
	private String departamento;
	
	@Column(name = "codPostal")
	private String codPostal;
	

	protected Direccion() {
	}
	
	public Direccion(String direccion, String coordLon, String coordLat, String telefono, String ciudad, String departamento, String codPostal) {
		this.direccion = direccion;
		this.coordLon = coordLon;
		this.coordLat = coordLat;		
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.codPostal = codPostal;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCoordLon() {
		return coordLon;
	}

	public void setCoordLon(String coordLon) {
		this.coordLon = coordLon;
	}

	public String getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(String coordLat) {
		this.coordLat = coordLat;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	@Override
	public String toString() {
		return String.format("direcciones[id=%d]",
				id);
	}	
}
