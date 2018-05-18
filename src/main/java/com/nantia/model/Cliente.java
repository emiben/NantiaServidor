package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "idDocumento")
	private int idDocumento;
	
	@Column(name = "tipoDocumento")
	private String tipoDocumento;
	
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "saldo")
	private float saldo;
	
	@Column(name = "envases")
	private int envases;
	
	@Column(name = "idLista")
	private int idLista;
	
	@Column(name = "idDirecciones")
	private int idDirecciones;
	
	@Column(name = "fechaAlta")
	private Date fechaAlta;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "activo")
	private Boolean activo;

	protected Cliente() {
	}
	
	public Cliente(int idDocumento, String tipoDocumento, String descripcion, float saldo, int envases, int idLista, int idDirecciones, Date fechaAlta, String mail, Boolean activo) {
		this.idDocumento = idDocumento;
		this.tipoDocumento = tipoDocumento;
		this.descripcion = descripcion;
		this.saldo = saldo;		
		this.envases = envases;
		this.idLista = idLista;
		this.idDirecciones = idDirecciones;
		this.fechaAlta = fechaAlta;		
		this.mail = mail;
		this.activo = activo;
	}
	
	
	

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getEnvases() {
		return envases;
	}

	public void setEnvases(int envases) {
		this.envases = envases;
	}

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public int getIdDirecciones() {
		return idDirecciones;
	}

	public void setIdDirecciones(int idDirecciones) {
		this.idDirecciones = idDirecciones;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return String.format("clientes[Id=%d]",
				id);
	}	

}
