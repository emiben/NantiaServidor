package com.nantia.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="fecha", nullable=false, length=19)
	private Date fecha;
	
	
	public VehiculoUbicacion() {
		super();
	}

	public VehiculoUbicacion(long id, Vehiculo vehiculo, String coordLon, String coordLat, Date fecha) {
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getFecha() {
		return fecha;
	}
	
    @JsonDeserialize(using=JsonDateDeserializer.class)
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


}
