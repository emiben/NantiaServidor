package com.nantia.repo;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.nantia.model.Cliente;
import com.nantia.model.Venta;

public interface VentaRepository extends CrudRepository<Venta, Long>{
	
	List<Venta> findByFecha(Calendar fecha);
	List<Venta> findByCliente(Cliente cliente);
	Venta findById(long id);
	
	//@Query(value = "SELECT ve.* FROM venta ve where ve.fecha > :fechaIni AND ve.fecha < :fechaFin", nativeQuery = true)
	@Query(value = "SELECT ve.* FROM venta ve where ve.fecha BETWEEN to_timestamp(:fechaIni, 'yyyy-MM-dd')\r\n" + 
			"AND to_timestamp(:fechaFin, 'yyyy-MM-dd')", nativeQuery = true)

    List<Venta> getVentasPorPeriodo(@Param("fechaIni") String fechaIni, @Param("fechaFin") String fechaFin);

}
