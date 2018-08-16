package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;

import com.nantia.model.ProductoStock;

public interface ProductoStockRepository extends CrudRepository<ProductoStock, Long> {
	ProductoStock findById(long id);
}
