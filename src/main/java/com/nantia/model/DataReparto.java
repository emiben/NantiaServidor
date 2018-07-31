package com.nantia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datareparto")
public class DataReparto implements Serializable {

	private static final long serialVersionUID = -4931085613752819291L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "idReparto")
	private long idReparto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "descripcionVehiculo")
	private String descripcionVehiculo;
	
	@Column(name = "dias_id")
	private DiaSemana dias;
	
	public DataReparto() {
	}
	
	public DataReparto(long idReparto, String descripcion, String descripcionVehiculo, DiaSemana dias) {
		this.idReparto = idReparto;
		this.descripcion = descripcion;
		this.descripcionVehiculo = descripcionVehiculo;
		this.dias = dias;
	}

	public long getIdReparto() {
		return idReparto;
	}

	public void setIdReparto(long idReparto) {
		this.idReparto = idReparto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionVehiculo() {
		return descripcionVehiculo;
	}

	public void setDescripcionVehiculo(String descripcionVehiculo) {
		this.descripcionVehiculo = descripcionVehiculo;
	}

	public DiaSemana getDias() {
		return dias;
	}

	public void setDias(DiaSemana dias) {
		this.dias = dias;
	}
	
	@Override
	public String toString() {
		return String.format("DataReparto[id=%d, idReparto=%d, descripcion=%s, descripcionVehiculo=%s");
	}
	
	
}
