package com.nantia.repo;

import org.springframework.data.repository.CrudRepository;
import com.nantia.model.ProductoVenta;

public interface ProductoVentaRepository  extends CrudRepository<ProductoVenta, Long> {
	ProductoVenta findById(long id);
}
