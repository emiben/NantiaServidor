package com.nantia.repo;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.nantia.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {

	Vehiculo findByMatricula(String matricula);
	
	@Query("SELECT v FROM Vehiculo v WHERE v.id not in (SELECT r.vehiculo FROM Reparto r WHERE r.fecha = :fechaReparto)")
    List<Vehiculo> getAllVehiculosSinStock(@Param("fechaReparto") Date fechaReparto);
}
