package com.nantia.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "empresas")
public class Empresa extends Cliente{

	private static final long serialVersionUID = -2457234739826937187L;

	@Column(name = "fechaConstituida")
	private Date fechaConstituida;
	
	@Column(name = "contacto")
	private String contacto;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	@OneToMany(mappedBy="empresa",cascade= CascadeType.ALL)
	private Set<Direccion> direcciones;
	
	protected Empresa() {
	}
	
	public Empresa(Date fechaConstituida, String contacto, String razonSocial) {
		this.fechaConstituida = fechaConstituida;
		this.contacto = contacto;
		this.razonSocial = razonSocial;
	}
	
	
	
	public Date getFechaConstituida() {
		return fechaConstituida;
	}

	public void setFechaConstituida(Date fechaConstituida) {
		this.fechaConstituida = fechaConstituida;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
/*
	@Override
	public String toString() {
		return String.format("clientes[id=%d]",
				id);
	}*/	
	
}
