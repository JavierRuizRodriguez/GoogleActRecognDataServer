package com.googleActRecognDataServer.api.datosR;

public class DatosBanderasActividades {

	public static final String[] NOMBRE_BANDERAS = { "MenuPrincipal", "Menu1", "Menu2", "Menu3" };
	public static final String[] NOMBRE_ACTIVIDADES = { "Vehiculo", "Bicicleta", "Andando-Corriendo", "Corriendo",
			"Parado", "Parado-Inclinado", "Caminando", "Desconocido" };

	public static int getIdBandera(String bandera){
		int contador = 0;
		boolean seguirContado = true;
		for(String band : NOMBRE_BANDERAS){
			if (seguirContado){
				if(!band.equalsIgnoreCase(bandera))
					contador++;
				else
					seguirContado = false;
			}
		}
		return NOMBRE_ACTIVIDADES.length + contador;
	}
	
	public static int getIdActividad(String actividad){
		int contador = 0;
		boolean seguirContado = true;
		for(String act : NOMBRE_ACTIVIDADES){
			if (seguirContado){
				if(!act.equalsIgnoreCase(actividad))
					contador++;
				else
					seguirContado = false;
			}
		}
		return  contador;
	}
}
