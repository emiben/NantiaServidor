package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productostock")
public class ProductoStock implements Serializable{
	

	private static final long serialVersionUID = 4713143103492833081L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	@JsonIgnore
	private Stock stock;
	
	@Column(name = "cantidad")
	private float cantidad;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "fecha")
	private Calendar fecha;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "productos_id")	
	private Producto producto;
	
	protected ProductoStock() {
	}
	
	public ProductoStock(Stock stock, float cantidad, Calendar fecha, Producto producto) {
		this.stock = stock;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.producto = producto;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return String.format("ProductoStock[id=%d]", id);
	}

}
