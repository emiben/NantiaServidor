package com.nantia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "productos", uniqueConstraints={@UniqueConstraint(columnNames = {"nombreProducto"})}) 
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 3258306168943102469L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productoId")
	private long productoId;
	
	@Column(name = "nombreProducto")
	private String nombreProducto;
	
	@Column(name = "retornable")
	private boolean retornable;

	@OneToMany(mappedBy = "productos")
	@JsonIgnore
	private Set<ProductoLista> setProductoLista = new HashSet<ProductoLista>();
	
	public long getId() {
		return productoId;
	}

	public void setId(long productoId) {
		this.productoId = productoId;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public boolean isRetornable() {
		return retornable;
	}

	public void setRetornable(boolean retornable) {
		this.retornable = retornable;
	}

	

	
}
