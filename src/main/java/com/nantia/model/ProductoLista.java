package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productolista")
public class ProductoLista  implements Serializable{
	

	private static final long serialVersionUID = 7724833792505097605L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	
	@ManyToOne
	@JoinColumn(name = "listas_id")  
	@JsonIgnore
	private ListaPrecio listaPrecio;
		
	   
	@ManyToOne
	@JoinColumn(name = "productos_id")
	private Producto productos;
	
	
	@Column(name = "precio")
	private float precio;
	
	@Column(name = "actualizado")
	private Date actualizado;
	
	
	
	
	protected ProductoLista() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Date getActualizado() {
		return actualizado;
	}

	public void setActualizado(Date actualizado) {
		this.actualizado = actualizado;
	}

	public ListaPrecio getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(ListaPrecio ListaPrecio) {
		this.listaPrecio = ListaPrecio;
	}

	public Producto getProductos() {
		return productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return String.format("ProductoLista[id=%d]", id);
	}	

	

}
