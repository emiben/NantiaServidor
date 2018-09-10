package com.nantia.model;

import java.io.Serializable;
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

@Entity
@Table(name = "pago")
public class Pago implements Serializable{
	

	private static final long serialVersionUID = -8897279124422804822L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")  
	private Cliente cliente;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "fechapago")
	private Calendar fechapago;
	
	@Column(name = "monto")
	private float monto;
	
	@OneToOne(cascade=CascadeType.MERGE, orphanRemoval=true)
	@JoinColumn(name = "venta_id", nullable = true)	
	private Venta venta;

	public Pago() {
		super();
	}

	public Pago(long id, Cliente cliente, Calendar fechapago, float monto, Venta venta) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fechapago = fechapago;
		this.monto = monto;
		this.venta = venta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Calendar getFechapago() {
		return fechapago;
	}

	public void setFechapago(Calendar fechapago) {
		this.fechapago = fechapago;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	@Override
	public String toString() {
		return String.format("Pago[id=%d, cliente=%s, monto=%d]", id, cliente.getNombre1(), monto);
	}
	
	

}
