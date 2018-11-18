package com.nantia.repo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.nantia.model.Cliente;
import com.nantia.model.DataEnvasesEnPrestamo;
import com.nantia.model.DiaSemana;


public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByNroDocumento(String nroDocumento);
	List<Cliente> findByMail(String mail);
	
	//@Query("SELECT c FROM Cliente c WHERE c.dias in :fecha")
	
	@Query(value = "SELECT cl.* FROM clientes cl INNER JOIN cliente_dias cd ON cl.id = cd.cliente_id WHERE dias_id = :fecha", nativeQuery = true)
    List<Cliente> getAllClientesPorDia(@Param("fecha") String fecha);
	
	@Query(value = "SELECT cl.* FROM clientes cl WHERE cl.id = :cliente AND saldo <> 0", nativeQuery = true)
    List<Cliente> getCuentasACobrarYCliente(@Param("cliente") long cliente);
	
	@Query(value = "SELECT cl.* FROM clientes cl WHERE saldo <> 0", nativeQuery = true)
    List<Cliente> getCuentasACobrar();
	
	@Query(value = "SELECT envasesenprestamo.* FROM (SELECT cl.nombre1 AS nombre1, cl.nombre2 AS nombre2, et.descripcion AS descripcion, ep.cantidad AS cantidad \r\n" + 
					"FROM clientes cl INNER JOIN envasesenprestamo ep ON cl.id = ep.clientes_id \r\n" + 
					"                 INNER JOIN envasetipos et ON ep.envases_id = et.envases_id) AS envasesenprestamo\r\n" + 
					"				  ORDER BY envasesenprestamo.nombre1, envasesenprestamo.nombre2 \r\n" + 
					" ", nativeQuery = true)
	List<Object> getEnvasesEnPrestamo();
	
	@Query(value = "SELECT cl.nombre1, cl.nombre2, et.descripcion, ep.cantidad \r\n" + 
					"FROM clientes cl INNER JOIN envasesenprestamo ep ON cl.id = ep.clientes_id \r\n" + 
					"                 INNER JOIN envasetipos et ON ep.envases_id = et.envases_id \r\n" + 
					"WHERE cl.id = :cliente ORDER BY nombre1, nombre2", nativeQuery = true)
	List<Object> getEnvasesEnPrestamoYCliente(@Param("cliente") long cliente);	
	
}
