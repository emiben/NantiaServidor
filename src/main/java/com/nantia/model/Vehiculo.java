package com.nantia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2779860433598897895L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "matricula")
	private String matricula;	
	
	@Column(name = "marca")
	private String marca;	
	
	@Column(name = "modelo")
	private String modelo;	
	
	@Column(name = "descripcion")
	private String descripcion;	
	
	@Column(name = "activo")
	private Boolean activo;
	
	@Column(name = "coordLon")
	private String coordLon;

	@Column(name = "coordLat")
	private String coordLat;
	
	public Vehiculo() {
	}
	
	public Vehiculo(String matricula, String marca, String modelo, String descripcion, Boolean activo, String coordLon, String coordLat) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.descripcion = descripcion;
		this.activo = activo;
		this.coordLon = coordLon;
		this.coordLat = coordLat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
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

	@Override
	public String toString() {
		return String.format("Vehiculo[id=%d, matricula=%s, marca=%s, modelo=%s, descripcion=%s, coordLon=%s, coordLat=%s]", id, matricula, marca, modelo, descripcion, coordLon, coordLat);
	}
	

}
