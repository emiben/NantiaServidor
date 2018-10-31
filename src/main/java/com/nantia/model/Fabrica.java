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
@Table(name = "fabrica")
public class Fabrica implements Serializable {

	private static final long serialVersionUID = 4647178816125843615L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToOne(cascade=CascadeType.MERGE, orphanRemoval=true)
	@JoinColumn(name = "stock_id")	
	private Stock stock;
	
	
	public Fabrica() {
	}
	
	public Fabrica(String nombre, Stock stock) {
		this.nombre = nombre;
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return String.format("Fabrica[id=%d, nombre=%s]", id, nombre);
	}
	
	
}
