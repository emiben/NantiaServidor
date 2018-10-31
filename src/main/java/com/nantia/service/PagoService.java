package com.nantia.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nantia.model.Pago;
import com.nantia.repo.PagoRepository;

@Service
public class PagoService implements IPagoService{

	
	@Autowired
	private PagoRepository pagoRepository;
	
	private Pago findById(Long precioId) {
		Pago pago = pagoRepository.findById(precioId);
		return pago;
	}
	
	
	@Override
	public List<Pago> getAllPagos() {
		List<Pago> list = new ArrayList<>();	
		pagoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Pago getPagoById(long pagoId) {
		Pago pago = pagoRepository.findOne(pagoId);
		return pago;
	}

	@Override
	public Pago addPago(Pago pago) {
		return pagoRepository.save(pago);
	}

	@Override
	public Pago updatePago(Pago pago) {
		return pagoRepository.save(pago);
	}

	@Override
	public void deletePago(long pagoId) {
		pagoRepository.delete(getPagoById(pagoId));
		
	}

	@Override
	public boolean existe(Pago pago) {
		return findById(pago.getId()) != null;
	}

}
