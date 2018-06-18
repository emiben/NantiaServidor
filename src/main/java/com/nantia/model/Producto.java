package com.nantia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productos", uniqueConstraints={@UniqueConstraint(columnNames = {"nombre"})}) 
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 3258306168943102469L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productosId")
	private long productoId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "presentacion")
	private String presentacion;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "retornable")
	private boolean retornable;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "productos")
	private Set<ProductoLista> setProductoLista = new HashSet<ProductoLista>();

	public long getProductoId() {
		return productoId;
	}

	public void setProductoId(long productoId) {
		this.productoId = productoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isRetornable() {
		return retornable;
	}

	public void setRetornable(boolean retornable) {
		this.retornable = retornable;
	}

	public Set<ProductoLista> getSetProductoLista() {
		return setProductoLista;
	}

	public void setSetProductoLista(Set<ProductoLista> setProductoLista) {
		this.setProductoLista = setProductoLista;
	}
	
}
