package com.nantia.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "envasesenprestamo")
public class EnvasesEnPrestamo implements Serializable{
	

	private static final long serialVersionUID = -1191923279027806047L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "envases_id")  
	@JsonIgnore
	private EnvasesTipos envasetipos;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clientes_id")
	@JsonIgnore
	private Cliente clientes;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	

	public EnvasesEnPrestamo() {
	}
	
	public EnvasesEnPrestamo(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public EnvasesTipos getEnvasetipos() {
		return envasetipos;
	}

	public void setEnvasetipos(EnvasesTipos envasetipos) {
		this.envasetipos = envasetipos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return String.format("envasesEnPrestamo[id=%d]", id);
	}	

}
