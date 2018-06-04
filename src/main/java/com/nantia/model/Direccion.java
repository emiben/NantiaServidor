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
	private float coordLon;

	@Column(name = "coordLat")
	private float coordLat;
	
	@Column(name = "telefono")
	private String telefono;
		
	@Column(name = "esquina1")
	private String esquina1;
	
	@Column(name = "esquina2")
	private String esquina2;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "departamento")
	private String departamento;
	
	@Column(name = "codPostal")
	private String codPostal;
	
	@Column(name = "observaciones")
	private String observaciones;
	

	protected Direccion() {
	}
	
	public Direccion(String direccion, float coordLon, float coordLat, String telefono, String esquina1, String esquina2, String observaciones, String ciudad, String departamento, String codPostal) {
		this.direccion = direccion;
		this.coordLon = coordLon;
		this.coordLat = coordLat;		
		this.telefono = telefono;
		this.esquina1 = esquina1;
		this.esquina2 = esquina2;	
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.codPostal = codPostal;
		this.observaciones = observaciones;
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

	public float getCoordLon() {
		return coordLon;
	}

	public void setCoordLon(float coordLon) {
		this.coordLon = coordLon;
	}

	public float getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(float coordLat) {
		this.coordLat = coordLat;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEsquina1() {
		return esquina1;
	}

	public void setEsquina1(String esquina1) {
		this.esquina1 = esquina1;
	}

	public String getEsquina2() {
		return esquina2;
	}

	public void setEsquina2(String esquina2) {
		this.esquina2 = esquina2;
	}

	public String getObservaciones() {
		return observaciones;
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

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return String.format("direcciones[id=%d]",
				id);
	}	
}
