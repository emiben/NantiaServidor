package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

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
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productolista", uniqueConstraints = {@UniqueConstraint(columnNames = {"listas_id", "productos_id"})})
public class ProductoLista  implements Serializable{
	

	private static final long serialVersionUID = 7724833792505097605L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;	
	
	@ManyToOne
	@JoinColumn(name = "listas_id")  
	@JsonIgnore
	private ListaPrecio listaPrecio;
		
	   
	@ManyToOne
	@JoinColumn(name = "productos_id")
	private Producto producto;
	
	
	@Column(name = "precio")
	private float precio;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "actualizado")
	private Calendar actualizado;
	
	
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

	public Calendar getActualizado() {
		return actualizado;
	}

	public void setActualizado(Calendar actualizado) {
		this.actualizado = actualizado;
	}

	public ListaPrecio getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(ListaPrecio ListaPrecio) {
		this.listaPrecio = ListaPrecio;
	}

	public Producto getProductos() {
		return producto;
	}

	public void setProductos(Producto productos) {
		this.producto = productos;
	}

	@Override
	public String toString() {
		return String.format("ProductoLista[id=%d]", id);
	}	

	

}
