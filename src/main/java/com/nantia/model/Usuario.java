package com.nantia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "usuario", unique=true)
	private String usuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;
	
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Rol rol;
	
	@Column(name = "contrasenia")
	private String contrasenia;
	
	@Column(name = "esVendedor")
	private boolean esVendedor;
	
	@Column(name = "saldoCaja")
	private float saldoCaja;
	
	public Usuario() {
	}

	public Usuario(String usuario, String nombre, String apellido, String contrasenia, boolean esVendedor, float saldoCaja) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.esVendedor = esVendedor;
		this.saldoCaja = saldoCaja;
		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEsVendedor() {
		return esVendedor;
	}

	public void setEsVendedor(boolean esVendedor) {
		this.esVendedor = esVendedor;
	}

	public float getSaldoCaja() {
		return saldoCaja;
	}

	public void setSaldoCaja(float saldoCaja) {
		this.saldoCaja = saldoCaja;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	

}