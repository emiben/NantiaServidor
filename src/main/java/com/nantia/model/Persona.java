package com.nantia.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona extends Cliente{
	

	private static final long serialVersionUID = 7266581728442124882L;

	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy="persona",cascade= CascadeType.ALL)
	private Set<Direccion> direcciones;
	
	protected Persona() {
	}
	
	public Persona(Date fechaNacimiento, String nombre) {
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
