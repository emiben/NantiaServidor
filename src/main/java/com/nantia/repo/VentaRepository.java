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
	
	@Query(value = "SELECT id, descuento, fecha, ivatotal, observaciones, pagototal, totalventa, "
			+ "cliente_id, fabrica_id, reparto_id, usuario_id	"
			+ "FROM public.venta "
			+ "where fecha > :fechaIni AND fecha < :fechaFin ;", nativeQuery = true)
    List<Venta> getVentasPorPeriodo(@Param("fechaIni") String fechaIni, @Param("fechaFin") String fechaFin);

}
