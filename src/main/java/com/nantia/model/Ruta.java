package com.nantia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ruta")
public class Ruta implements Serializable {
	
	
	private static final long serialVersionUID = 8260536335732454860L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nombre")
	private String nombre;

	
	//@ElementCollection(targetClass = DiaSemana.class)
	//@CollectionTable(name = "cliente_dias", joinColumns = @JoinColumn(name = "cliente_id"))
	//@Enumerated(EnumType.STRING)
	@Column(name = "dias_id")
	private DiaSemana dias;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "ruta")
	private Set<RutaCliente> setRutaCliente = new HashSet<RutaCliente>();
	
	
	protected Ruta() {
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

	@Override
	public String toString() {
		return String.format("Vehiculo[id=%d, nombre=%s]", id, nombre);
	}

}
