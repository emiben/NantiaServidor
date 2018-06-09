package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import com.nantia.model.Cliente.TipoDocumento;

public class DataCliente implements Serializable{
	

	private static final long serialVersionUID = -868032689882263492L;
	
	
	private long clienteId;	
	private Direccion direccion;
	private TipoDocumento tipoDocumento;
	private String nroDocumento;
	private String nombre1;
	private String nombre2;
	private float saldo;
	private Date fechaNacimiento;
	private Date fechaAlta;	
	private String celular;
	private String mail;	
	private int idLista;
	private String observaciones;
	private Boolean activo;
	private Set<DiaSemana> dias = new HashSet<DiaSemana>();
	SortedMap<String, Integer> setEnvases = new TreeMap<String, Integer>();
	
	
	public DataCliente() {
	}
	
	public DataCliente(TipoDocumento tipoDocumento, String nroDocumento, String nombre1, String nombre2, float saldo, Date fechaNacimiento, Date fechaAlta, String celular, String mail, int idLista, String observaciones, Boolean activo, Set<DiaSemana> dias, SortedMap<String, Integer> setEnvases) {
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
		this.dias = dias;
		this.setEnvases = setEnvases;
	}
	
	
	
	
	
	public long getClienteId() {
		return clienteId;
	}

	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
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

	public Set<DiaSemana> getDias() {
		return dias;
	}

	public void setDias(Set<DiaSemana> dias) {
		this.dias = dias;
	}

	public SortedMap<String, Integer> getSm() {
		return setEnvases;
	}

	public void setSm(SortedMap<String, Integer> setEnvases) {
		this.setEnvases = setEnvases;
	}


/*
	public class Envases
	{
	    public String envase; 
	    public int cantidad; 
	 };
	 */

}
