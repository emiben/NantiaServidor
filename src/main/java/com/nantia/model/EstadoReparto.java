package com.nantia.model;

public enum EstadoReparto {
	
	BORRADOR,
    CREADO,
    INICIADO,
    FINALIZADO;
	
    public static EstadoReparto getEstadoReparto(String estadoReparto){  
    	
	  switch (estadoReparto){  
	   case "BORRADOR" : return EstadoReparto.BORRADOR;  
	   case "CREADO" : return EstadoReparto.CREADO; 
	   case "INICIADO" : return EstadoReparto.INICIADO; 
	   case "FINALIZADO" : return EstadoReparto.FINALIZADO; 

	   default : return null;  
	  }  
	 }  


}
