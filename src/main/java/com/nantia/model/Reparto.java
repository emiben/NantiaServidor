package com.nantia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "reparto")
public class Reparto implements Serializable {

	private static final long serialVersionUID = 5035521686864986871L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "descripcion")
	private String descripcion;	
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "vendedor1_id")	
	private Usuario vendedor1;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "vendedor2_id")	
	private Usuario vendedor2;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "vehiculo_id")	
	private Vehiculo vehiculo;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="fecha", nullable=false, length=13)
	private Date fecha;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ruta_id")	
	private Ruta ruta;
	
	@Column(name = "estado_id")
	private EstadoReparto estado;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "fabrica_id")	
	private Fabrica fabrica;
	
	public Reparto() {
	}
	
	public Reparto(String descripcion, Usuario vendedor1, Usuario vendedor2, Vehiculo vehiculo, Date fecha, Ruta ruta, EstadoReparto estado, Fabrica fabrica) {
		this.descripcion = descripcion;
		this.vendedor1 = vendedor1;
		this.vendedor2 = vendedor2;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
		this.ruta = ruta;
		this.estado = estado;
		this.fabrica = fabrica;
		
	}
	
	
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getVendedor1() {
		return vendedor1;
	}

	public void setVendedor1(Usuario vendedor1) {
		this.vendedor1 = vendedor1;
	}

	public Usuario getVendedor2() {
		return vendedor2;
	}

	public void setVendedor2(Usuario vendedor2) {
		this.vendedor2 = vendedor2;
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

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

		
	public EstadoReparto getEstado() {
		return estado;
	}

	public void setEstado(EstadoReparto estado) {
		this.estado = estado;
	}
	
	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	@Override
	public String toString() {
		return String.format("Reparto[id=%d, descripcion=%s, vendedor1=%s, vendedor2=%s, descripcion=%s, fecha=%tF, ruta=%s]", id, descripcion, vendedor1.getNombre(), vendedor2.getNombre(), vehiculo.getMatricula(), fecha, ruta.getNombre());
	}
	
}
