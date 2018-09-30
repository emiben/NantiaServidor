package com.nantia.model;


import java.io.Serializable;
import java.util.Date;
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

import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "listasdeprecios", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombreLista"})})
public class ListaPrecio implements Serializable{
	
	private static final long serialVersionUID = 666261282935880237L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
		
	@Column(name = "nombreLista")	
	private String nombreLista;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="fechaAlta", nullable=false, length=13)
	private Date fechaAlta;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "listaPrecio")
	@OrderBy(clause = "id")
	private Set<ProductoLista> setProductoLista = new HashSet<ProductoLista>();
	
	
	protected ListaPrecio() {
	}
	
	public ListaPrecio(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getFechaAlta() {
		return fechaAlta;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	public Set<ProductoLista> getSetProductoLista() {
		return setProductoLista;
	}

	public void setProductoLista(Set<ProductoLista> setProductoLista) {
		this.setProductoLista = setProductoLista;
	}
	
	@Override
	public String toString() {
		return String.format("ProductoLista[id=%d]", id);
	}
	
}
