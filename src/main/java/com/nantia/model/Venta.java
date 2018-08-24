package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "venta")
public class Venta implements Serializable{
	private static final long serialVersionUID = -7899617239584557918L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "fecha")	
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")  
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")  
	private Cliente cliente;
	

	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true, mappedBy = "venta")
	private Set<ProductoVenta> setProductoVenta = new HashSet<ProductoVenta>();
	
	
	@Column(name = "descuento")
	private float descuento;
	
	@Column(name = "totalventa")
	private float totalventa;
	
	@Column(name = "ivatotal")
	private float ivatotal;
	
	@Column(name = "pagototal")
	private float pagototal;
	
	@ManyToOne
	@JoinColumn(name = "fabrica_id")  
	private Fabrica fabrica;
	
	@ManyToOne
	@JoinColumn(name = "reparto_id")  
	private Reparto repartp;

	public Venta(long id, Date fecha, Usuario usuario, Cliente cliente, Set<ProductoVenta> setProductoVenta,
			float descuento, float totalventa, float ivatotal, float pagototal, Fabrica fabrica, Reparto repartp) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.cliente = cliente;
		this.setProductoVenta = setProductoVenta;
		this.descuento = descuento;
		this.totalventa = totalventa;
		this.ivatotal = ivatotal;
		this.pagototal = pagototal;
		this.fabrica = fabrica;
		this.repartp = repartp;
	}

	public Venta() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ProductoVenta> getSetProductoVenta() {
		return setProductoVenta;
	}

	public void setSetProductoVenta(Set<ProductoVenta> setProductoVenta) {
		this.setProductoVenta = setProductoVenta;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getTotalventa() {
		return totalventa;
	}

	public void setTotalventa(float totalventa) {
		this.totalventa = totalventa;
	}

	public float getIvatotal() {
		return ivatotal;
	}

	public void setIvatotal(float ivatotal) {
		this.ivatotal = ivatotal;
	}

	public float getPagototal() {
		return pagototal;
	}

	public void setPagototal(float pagototal) {
		this.pagototal = pagototal;
	}

	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public Reparto getRepartp() {
		return repartp;
	}

	public void setRepartp(Reparto repartp) {
		this.repartp = repartp;
	}

	
}
