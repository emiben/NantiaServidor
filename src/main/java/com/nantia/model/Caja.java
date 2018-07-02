package com.nantia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cajas")
public class Caja implements Serializable{
	
	private static final long serialVersionUID = -3782118018086834918L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(mappedBy = "caja", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Usuario usuario;
	
	@Column(name = "saldo")
	private float saldo;
	
	protected Caja() {
	}

	public Caja(long id, float saldo) {
		super();
		this.id = id;
		this.saldo = saldo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
}
