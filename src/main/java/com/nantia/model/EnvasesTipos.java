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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "envasetipos")
public class EnvasesTipos implements Serializable{
	
	private static final long serialVersionUID = -5668003671600498979L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "envasesId")
	private long id;
		
	@Column(name = "descripcion")	
	private String descripcion;
		
	public EnvasesTipos() {
	}
	
	public EnvasesTipos(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return String.format("tiposenvases[id=%d]",	id);
	}
}
