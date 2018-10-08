package com.nantia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "datapago")
public class DataPago implements Serializable{
	
	private static final long serialVersionUID = 3848059160865564064L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long clienteid;
		
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechapago;
	
	private float monto;

	public DataPago() {
		super();
	}

	public DataPago(long id, long clienteid, Date fechapago, float monto) {
		super();
		this.id = id;
		this.clienteid = clienteid;
		this.fechapago = fechapago;
		this.monto = monto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClienteid() {
		return clienteid;
	}

	public void setClienteid(long clienteid) {
		this.clienteid = clienteid;
	}

	public Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	
	

}
