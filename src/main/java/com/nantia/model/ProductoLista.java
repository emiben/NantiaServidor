package com.nantia.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="actualizado", nullable=false, length=13)
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getActualizado() {
		return actualizado;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
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
