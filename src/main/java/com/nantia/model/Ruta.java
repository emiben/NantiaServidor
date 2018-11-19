package com.nantia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "ruta")
public class Ruta implements Serializable {
	
	
	private static final long serialVersionUID = 8260536335732454860L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "dias_id")
	private DiaSemana dias;
	

	//@OrderBy(clause = "id")
	//@Cascade(CascadeType.ALL)
	//@OneToMany(orphanRemoval = true, mappedBy = "ruta")
	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = false, mappedBy = "ruta")
	@OrderBy(clause = "id")
	private Set<RutaCliente> setRutaCliente = new HashSet<RutaCliente>();
	
	
	public Ruta() {
	}
	
	public Ruta(String nombre, DiaSemana dias, Set<RutaCliente> setRutaCliente) {
		this.nombre = nombre;
		this.dias = dias;
		this.setRutaCliente = setRutaCliente;
	}
	
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DiaSemana getDias() {
		return dias;
	}

	public void setDias(DiaSemana dias) {
		this.dias = dias;
	}

	public Set<RutaCliente> getSetRutaCliente() {
		return setRutaCliente;
	}

	public void setSetRutaCliente(Set<RutaCliente> setRutaCliente) {
		this.setRutaCliente = setRutaCliente;
	}

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Vehiculo[id=%d, nombre=%s]", id, nombre);
	}

}
