package com.nantia.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Calendar;

@Entity
@Table(name = "vehiculoubicacion")
public class VehiculoUbicacion implements Serializable{
	

	private static final long serialVersionUID = 7933083245755235306L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "vehiculo_id")	
	private Vehiculo vehiculo;
	
	@Column(name = "coordLon")
	private String coordLon;

	@Column(name = "coordLat")
	private String coordLat; 
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "fecha")
	private Calendar fecha;
	
	
	public VehiculoUbicacion() {
		super();
	}

	public VehiculoUbicacion(long id, Vehiculo vehiculo, String coordLon, String coordLat, Calendar fecha) {
		super();
		this.id = id;
		this.vehiculo = vehiculo;
		this.coordLon = coordLon;
		this.coordLat = coordLat;
		this.fecha = fecha;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCoordLon() {
		return coordLon;
	}

	public void setCoordLon(String coordLon) {
		this.coordLon = coordLon;
	}

	public String getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(String coordLat) {
		this.coordLat = coordLat;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
}
