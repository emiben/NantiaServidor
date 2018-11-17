package com.nantia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Cliente;
import com.nantia.model.Venta;
import com.nantia.repo.VentaRepository;

@Service
public class VentaService implements IVentaService {

	
	@Autowired
	private VentaRepository ventaRepository;
	
	private Venta findById(long id) {
		Venta ven = ventaRepository.findById(id);
		return ven;
	}
	
	@Override
	public List<Venta> getAllVentas() {
		List<Venta> list = new ArrayList<>();	
		ventaRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Venta getVentaById(long ventaId) {
		Venta ven = ventaRepository.findOne(ventaId);
		return ven;
	}

	@Override
	public Venta addVenta(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	public Venta updateVenta(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	public void deleteVenta(long ventaId) {
		ventaRepository.delete(getVentaById(ventaId));
	}

	@Override
	public boolean existe(Venta venta) {
		return findById(venta.getId()) != null;
	}

	@Override
	public List<Venta> getVentasPorPeriodo(String fechaIni, String fechaFin, long cliente) {
		List<Venta> list = new ArrayList<>();	
		
		if(cliente == 0)
		{
			ventaRepository.getVentasPorPeriodo(fechaIni, fechaFin).forEach(e -> list.add(e));
		}
		else 
		{
			ventaRepository.getVentasPorPeriodoYCliente(fechaIni, fechaFin, cliente).forEach(e -> list.add(e));
		}
		
		return list;
	}

}
