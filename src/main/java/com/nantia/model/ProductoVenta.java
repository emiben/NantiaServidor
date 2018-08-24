package com.nantia.model;

import java.io.Serializable;

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
@Table(name = "productoventa") 
public class ProductoVenta implements Serializable{


	private static final long serialVersionUID = -7431083625450719044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "venta_id")  
	@JsonIgnore
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "productostock_id")  
	private ProductoStock productoStock;
	
	@Column(name = "cantidad")
	private float cantidad;
	
	@Column(name = "treciounitario")
	private float precioUnitario;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "subtotal")
	private float subTotal;

	@Column(name = "iva")
	private float iva;

	public ProductoVenta(long id, Venta venta, ProductoStock productoStock, float cantidad, float precioUnitario,
			float total, float subTotal, float iva) {
		super();
		this.id = id;
		this.venta = venta;
		this.productoStock = productoStock;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.total = total;
		this.subTotal = subTotal;
		this.iva = iva;
	}

	public ProductoVenta() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public ProductoStock getProductoStock() {
		return productoStock;
	}

	public void setProductoStock(ProductoStock productoStock) {
		this.productoStock = productoStock;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	
	
	
	
}
