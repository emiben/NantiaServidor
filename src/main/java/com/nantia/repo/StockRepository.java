package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;
import com.nantia.model.Stock;

public interface StockRepository  extends CrudRepository<Stock, Long>{
	Stock findById(long id);

}
