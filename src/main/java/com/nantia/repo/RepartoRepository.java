package com.nantia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nantia.model.EstadoReparto;
import com.nantia.model.Reparto;

public interface RepartoRepository extends CrudRepository<Reparto, Long>{
	
	Reparto findByDescripcion(String descripcion);
	
	@Query("SELECT R FROM Reparto R WHERE R.estado = :estadoReparto")
    List<Reparto> getAllRepartoCreado(@Param("estadoReparto") EstadoReparto estadoReparto);

}
