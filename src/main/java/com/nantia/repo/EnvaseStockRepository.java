package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.EnvaseStock;


public interface EnvaseStockRepository extends CrudRepository<EnvaseStock, Long> {
	EnvaseStock findById(long id);
}
