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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
//@Table(name = "datareparto")
public class DataReparto implements Serializable {

	private static final long serialVersionUID = -4931085613752819291L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//@Column(name = "idReparto")
	private long idReparto;
	
	//@Column(name = "descripcion")
	private String descripcion;	
	
	@ManyToOne
	@JoinColumn(name = "vendedor1_id")	
	private Usuario vendedor1;
	
	@ManyToOne
	@JoinColumn(name = "vendedor2_id")	
	private Usuario vendedor2;
	
	//@Column(name = "descripcionVehiculo")
	private String descripcionVehiculo;
	
	//@Column(name = "dias_id")
	private DiaSemana dias;
	
	@ManyToOne//@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "vehiculo_id")	
	private Vehiculo vehiculo;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@Column(name="fecha", nullable=false, length=13)
	private Date fecha;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ruta_id")	
	private Ruta ruta;
	
	//@Column(name = "estado_id")
	private EstadoReparto estado;
	
	@JoinColumn(name = "fabricaid")	
	private Long fabricaid;
	
	@OneToOne(cascade=CascadeType.MERGE, orphanRemoval=true)
	@JoinColumn(name = "stock_id", nullable = true)	
	private Stock stock;
	
	
	public DataReparto() {
	}
	
	public DataReparto(long idReparto, String descripcion, String descripcionVehiculo, DiaSemana dias) {
		this.idReparto = idReparto;
		this.descripcion = descripcion;
		this.descripcionVehiculo = descripcionVehiculo;
		this.dias = dias;
	}

	
	
	public DataReparto(long id, long idReparto, String descripcion, Usuario vendedor1, Usuario vendedor2,
			String descripcionVehiculo, DiaSemana dias, Vehiculo vehiculo, Date fecha, Ruta ruta, EstadoReparto estado,
			Long fabricaid, Stock stock) {
		super();
		this.id = id;
		this.idReparto = idReparto;
		this.descripcion = descripcion;
		this.vendedor1 = vendedor1;
		this.vendedor2 = vendedor2;
		this.descripcionVehiculo = descripcionVehiculo;
		this.dias = dias;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
		this.ruta = ruta;
		this.estado = estado;
		this.fabricaid = fabricaid;
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
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@JsonDeserialize(using=JsonDateDeserializer.class)
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



	public Long getFabricaid() {
		return fabricaid;
	}

	public void setFabricaid(Long fabricaid) {
		this.fabricaid = fabricaid;
	}

	
	
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "";//String.format("DataReparto[id=%d, idReparto=%d, descripcion=%s, descripcionVehiculo=%s");
	}
	
	
}
