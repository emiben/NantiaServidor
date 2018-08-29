package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nantia.controller.StockController;


@Entity
@Table(name = "stock")
public class Stock implements Serializable {
	
	private static final long serialVersionUID = -7899617239584557918L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "fecha")
	private Calendar fecha;

	
	
	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true, mappedBy = "stock")
	private Set<EnvaseStock> setEnvaseStock = new HashSet<EnvaseStock>();
	
	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true, mappedBy = "stock")
	private Set<ProductoStock> setProductoStock = new HashSet<ProductoStock>();

	
	public Stock() {
	}
	
	public Stock(Calendar fecha, Set<EnvaseStock> setEnvaseStock, Set<ProductoStock> setProductoStock) {
		this.fecha = fecha;
		this.setEnvaseStock = setEnvaseStock;
		this.setProductoStock = setProductoStock;
	}
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Set<EnvaseStock> getSetEnvaseStock() {
		return setEnvaseStock;
	}

	public void setSetEnvaseStock(Set<EnvaseStock> setEnvaseStock) {
		this.setEnvaseStock = setEnvaseStock;

	}

	public Set<ProductoStock> getSetProductoStock() {
		return setProductoStock;
	}

	public void setSetProductoStock(Set<ProductoStock> setProductoStock) {
		this.setProductoStock = setProductoStock;

	}

	@Override
	public String toString() {
		//return String.format("Stock[id=%d, fecha=%tF]", id, fecha);
		return String.format("Stock[id=%d]", id);
	}
}
