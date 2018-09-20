package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "envasestock")//, uniqueConstraints = {@UniqueConstraint(columnNames = {"stock_id"})}
public class EnvaseStock implements Serializable {
	
	private static final long serialVersionUID = 5394273125528765283L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	@JsonIgnore
	private Stock stock;
	
	@Column(name = "cantidad")
	private float cantidad;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="fecha", nullable=false, length=13)
	private Date fecha;
	
	@ManyToOne//@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "envasetipos_id")//@MapsId	
	private EnvasesTipos envasesTipos;
	
	protected EnvaseStock() {
	}
	
	public EnvaseStock(Stock stock, float cantidad, Date fecha, EnvasesTipos envasesTipos) {
		this.stock = stock;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.envasesTipos = envasesTipos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EnvasesTipos getEnvasesTipos() {
		return envasesTipos;
	}

	public void setEnvasesTipos(EnvasesTipos envasesTipos) {
		this.envasesTipos = envasesTipos;
	}
	
	@Override
	public String toString() {
		return String.format("EnvaseStock[id=%d]", id);
	}

}
