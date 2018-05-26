package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente implements Serializable{
	

	private static final long serialVersionUID = -5966354327901311107L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "direcciones_id")	
	private Direccion direccion;
	
	@Column(name = "tipoDocumento")
	private tipoDocumento tipoDocumento;
	
	@Column(name = "nroDocumento")
	private String nroDocumento;
	
	@Column(name = "nombre1")
	private String nombre1;
	
	@Column(name = "nombre2")
	private String nombre2;
	
	@Column(name = "saldo")
	private float saldo;
	
	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "fechaAlta")
	private Date fechaAlta;	
	
	@Column(name = "celular")
	private String celular;

	@Column(name = "mail")
	private String mail;	
	
	@Column(name = "idLista")
	private int idLista;
	
	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "activo")
	private Boolean activo;
	
	
	protected Cliente() {
	}
	
	public Cliente(tipoDocumento tipoDocumento, String nroDocumento, String nombre1, String nombre2, float saldo, Date fechaNacimiento, Date fechaAlta, String celular, String mail, int idLista, String observaciones, Boolean activo) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.saldo = saldo;		
		this.fechaNacimiento = fechaNacimiento;		
		this.fechaAlta = fechaAlta;		
		this.celular = celular;
		this.mail = mail;
		this.idLista = idLista;
		this.observaciones = observaciones;
		this.activo = activo;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public tipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(tipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	
	@Override
	public String toString() {
		return String.format("clientes[id=%d]",	id);
	}	
	
	public enum tipoDocumento {
	    CI,
	    RUT,
	    NA,	   
	}


}
