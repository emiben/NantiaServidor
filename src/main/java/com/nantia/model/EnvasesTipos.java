package com.nantia.model;


import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name = "envasetipos")
public class EnvasesTipos implements Serializable{
	
	private static final long serialVersionUID = -5668003671600498979L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//@Column(name = "envasesId")	
	private long id;
		
	@Column(name = "descripcion")	
	private String descripcion;
	/*
	@OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private EnvaseStock envaseStock;
	
/*
	@OneToOne
	@PrimaryKeyJoinColumn
    private EnvaseStock envaseStock;
	*/
	/*
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="TipoEnvaseEnvasePmo", joinColumns={@JoinColumn(name="IdEnvEnPmo")}, inverseJoinColumns={@JoinColumn(name="IdEnvaseEnPrestamo")})
    private Set<EnvasesEnPrestamo> envases = new HashSet<EnvasesEnPrestamo>();
	*/
	//@OneToMany(mappedBy = "envasetipos")
	//private Set<EnvasesEnPrestamo> envasesEnPrestamo = new HashSet<EnvasesEnPrestamo>();
	
		
	public EnvasesTipos() {
	}
	
	public EnvasesTipos(String descripcion) {
		this.descripcion = descripcion;
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
	
	
	/*
	public Set<EnvasesEnPrestamo> getEnvasesEnPrestamo() {
        return envasesEnPrestamo;
    }
	
	public void setEnvasesEnPrestamo(Set<EnvasesEnPrestamo> groups) {
        this.envasesEnPrestamo = groups;
    }
     
    public void addEnvasesEnPrestamo(EnvasesEnPrestamo envasesEnPrestamo) {
        this.envasesEnPrestamo.add(envasesEnPrestamo);
    }
*/
	@Override
	public String toString() {
		return String.format("tiposenvases[id=%d]",	id);
	}
}
