package com.nantia.model;

public enum DiaSemana {
	
	DOMINGO,
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO;
	
    public static DiaSemana getDiaSemana(String diaSemana){  
	  switch (diaSemana){  
	   case "DOMINGO" : return DiaSemana.DOMINGO;  
	   case "LUNES" : return DiaSemana.LUNES; 
	   case "MARTES" : return DiaSemana.MARTES; 
	   case "MIERCOLES" : return DiaSemana.MIERCOLES; 
	   case "JUEVES" : return DiaSemana.JUEVES;  
	   case "VIERNES" : return DiaSemana.VIERNES; 
	   case "SABADO" : return DiaSemana.SABADO; 
	   default : return null;  
	  }  
	 }  

}
