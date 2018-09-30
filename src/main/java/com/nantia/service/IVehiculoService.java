package com.nantia.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.nantia.model.Vehiculo;

public interface IVehiculoService {
	
	
	List<Vehiculo> getAllVehiculo();
	Vehiculo getVehiculoById(long vehiculoId);
	Vehiculo addVehiculo(Vehiculo vehiculo);
	Vehiculo updateVehiculo(Vehiculo vehiculo);
	void deleteVehiculo(long vehiculoId);
	boolean existe(Vehiculo vehiculo);
	List<Vehiculo> getAllVehiculosSinStock(Date fecha);

}
