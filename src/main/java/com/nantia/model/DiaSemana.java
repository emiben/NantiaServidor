package com.nantia.model;

public enum DiaSemana {
	
	DOMINGO,
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO;
	
    public static String getDiaSemana(DiaSemana diaSemana){  
	  switch (diaSemana){  
	   case DOMINGO : return "DOMINGO";  
	   case LUNES : return "LUNES"; 
	   case MARTES : return "MARTES";  
	   case MIERCOLES : return "MIERCOLES"; 
	   case JUEVES : return "JUEVES";  
	   case VIERNES : return "VIERNES"; 
	   case SABADO : return "SABADO"; 
	   default : return null;  
	  }  
	 }  

}
