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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cliente implements Serializable{
	

	private static final long serialVersionUID = 1L;

	//private static final long serialVersionUID = -3009157732242241606L;
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	*/
	
	@Id
    @GeneratedValue (strategy=GenerationType.TABLE , generator= "idsGenerator" )
    @TableGenerator (name= "idsGenerator" , table= "idsGenerator" , 
           pkColumnName= "id" , pkColumnValue= "clientes" , valueColumnName= "clienteIds" ) 
    @Column (name =  "id" , unique = true  , nullable = false  )
	private long id;
		
	@Column(name = "tipoDocumento")
	private tipoDocumento tipoDocumento;
	
	@Column(name = "nroDocumento")
	private String nroDocumento;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "celular")
	private String celular;

	@Column(name = "saldo")
	private float saldo;
	
	@Column(name = "envases")
	private int envases;
	
	@Column(name = "idLista")
	private int idLista;
	
	@Column(name = "fechaAlta")
	private Date fechaAlta;

	@Column(name = "mail")
	private String mail;
	
	@Column(name = "activo")
	private Boolean activo;

	protected Cliente() {
	}
	
	public Cliente(tipoDocumento tipoDocumento, String nroDocumento, String descripcion, String celular, float saldo, int envases, int idLista, Date fechaAlta, String mail, Boolean activo) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.descripcion = descripcion;
		this.celular = celular;
		this.saldo = saldo;		
		this.envases = envases;
		this.idLista = idLista;
		this.fechaAlta = fechaAlta;		
		this.mail = mail;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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
		return String.format("clientes[id=%d]",
				id);
	}	
	
	public enum tipoDocumento {
	    CI,
	    RUT,
	    NA,	   
	}


}
