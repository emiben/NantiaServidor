package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable{
	

	private static final long serialVersionUID = -8107461602428099277L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="idPersona")
	private Persona persona;
	
	@Column(name = "idRuta")
	private long idRuta;
		
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "cordLon")
	private float cordLon;

	@Column(name = "cordLat")
	private float cordLat;
	
	@Column(name = "telefono")
	private String telefono;
		
	@Column(name = "esquina1")
	private String esquina1;
	
	@Column(name = "esquina2")
	private String esquina2;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "principal")
	private Boolean principal;
	
	@Column(name = "dirCobro")
	private Boolean dirCobro;

	@Column(name = "idZona")
	private long idZona;

	protected Direccion() {
	}
	
	public Direccion(Empresa empresa, Persona persona, long idRuta, String direccion, float cordLon, float cordLat, String telefono, String esquina1, String esquina2, String observaciones, Boolean principal, Boolean dirCobro, long idZona) {
		this.empresa = empresa;
		this.persona = persona;
		this.idRuta = idRuta;
		this.direccion = direccion;
		this.cordLon = cordLon;
		this.cordLat = cordLat;		
		this.telefono = telefono;
		this.esquina1 = esquina1;
		this.esquina2 = esquina2;		
		this.observaciones = observaciones;
		this.principal = principal;
		this.dirCobro = dirCobro;
		this.idZona = idZona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(long idRuta) {
		this.idRuta = idRuta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getCordLon() {
		return cordLon;
	}

	public void setCordLon(float cordLon) {
		this.cordLon = cordLon;
	}

	public float getCordLat() {
		return cordLat;
	}

	public void setCordLat(float cordLat) {
		this.cordLat = cordLat;
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

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public Boolean getDirCobro() {
		return dirCobro;
	}

	public void setDirCobro(Boolean dirCobro) {
		this.dirCobro = dirCobro;
	}

	public long getIdZona() {
		return idZona;
	}

	public void setIdZona(long idZona) {
		this.idZona = idZona;
	}
	
	@Override
	public String toString() {
		return String.format("direcciones[id=%d]",
				id);
	}	
}
