package com.nantia.repo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.nantia.model.Cliente;
import com.nantia.model.DiaSemana;


public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByNroDocumento(String nroDocumento);
	List<Cliente> findByMail(String mail);
	
	//@Query("SELECT c FROM Cliente c WHERE c.dias in :fecha")
	
	@Query(value = "SELECT cl.* FROM clientes cl INNER JOIN cliente_dias cd ON cl.id = cd.cliente_id WHERE dias_id = :fecha", nativeQuery = true)
    List<Cliente> getAllClientesPorDia(@Param("fecha") String fecha);
	
	
}
