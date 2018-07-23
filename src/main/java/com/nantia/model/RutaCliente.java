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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rutacliente")
public class RutaCliente implements Serializable {

	private static final long serialVersionUID = 4645620402969911042L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "cliente_id")	
	private Cliente cliente;
	
	@Column(name = "ordenVisita")
	private int ordenVisita;
	
	@ManyToOne
	@JoinColumn(name = "ruta_id")  
	@JsonIgnore
	private Ruta ruta;
	
	protected RutaCliente() {
	}
	
	public RutaCliente(Cliente cliente, int ordenVisita) {
		this.cliente = cliente;
		this.ordenVisita = ordenVisita;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getOrdenVisita() {
		return ordenVisita;
	}

	public void setOrdenVisita(int ordenVisita) {
		this.ordenVisita = ordenVisita;
	}
	
	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	@Override
	public String toString() {
		return String.format("RutaCliente[id=%d, nombre=%s, marca=%d]", id, cliente.getNombre1(), ordenVisita);
	}

}
