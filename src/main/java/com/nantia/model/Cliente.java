package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Direccion")
	private String direccion;

	@Column(name = "Apellido")
	private String apellido;
	
	@Column(name = "Telefono")
	private String telefono;
	
	@Column(name = "Codigo", unique=true)
	private int codigo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	protected Cliente() {
	}
	
	public Cliente(String nombre, String direccion, String apellido, String telefono, int codigo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.apellido = apellido;		
		this.telefono = telefono;
		this.codigo = codigo;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return String.format("Clientes[Nombre='%s', Direccion='%s', Apellido='%s', Telefono='%s', Codigo=%d, Id=%d]",
				nombre, direccion, apellido, telefono, codigo, id);
	}

}
