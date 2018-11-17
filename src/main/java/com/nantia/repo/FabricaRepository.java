package com.nantia.repo;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nantia.model.Fabrica;

public interface FabricaRepository extends CrudRepository<Fabrica, Long>{
	
	Fabrica findByNombre(String nombre);
	
	
		@Query(value = "SELECT envasesenprestamo.* FROM (SELECT fa.nombre as nombre1, null as nombre2, et.descripcion, es.cantidad \r\n" + 
						"FROM fabrica fa INNER JOIN envasestock es ON fa.stock_id = es.stock_id \r\n" + 
						"                 INNER JOIN envasetipos et ON es.envasetipos_id = et.envases_id ) as envasesenprestamo\r\n" + 
						"				 order by envasesenprestamo.nombre1, envasesenprestamo.nombre2 \r\n" + 
						" ", nativeQuery = true)
		List<Object> getEnvasesEnPrestamo();
		
		@Query(value = "SELECT envasesenprestamo.* FROM (SELECT fa.nombre as nombre1, null as nombre2, et.descripcion, es.cantidad \r\n" + 
						"FROM fabrica fa INNER JOIN envasestock es ON fa.stock_id = es.stock_id \r\n" + 
						"                 INNER JOIN envasetipos et ON es.envasetipos_id = et.envases_id WHERE fa.id = :fabrica) as envasesenprestamo\r\n" + 
						"				 order by envasesenprestamo.nombre1, envasesenprestamo.nombre2", nativeQuery = true)
		List<Object> getEnvasesEnPrestamoYCliente(@Param("fabrica") long fabrica);	

}
