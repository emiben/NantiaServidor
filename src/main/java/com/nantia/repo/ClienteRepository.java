package com.nantia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.nantia.model.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByNroDocumento(String nroDocumento);
	List<Cliente> findByMail(String mail);
	
	@Query("SELECT R FROM Reparto R WHERE R.estado = :estadoReparto")
    List<Cliente> getAllClientePorDia(@Param("estadoReparto") Cliente cliente);
}
