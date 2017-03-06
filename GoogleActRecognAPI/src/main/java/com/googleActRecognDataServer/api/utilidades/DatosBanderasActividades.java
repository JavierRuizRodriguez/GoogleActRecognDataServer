package com.googleActRecognDataServer.api.utilidades;

/**
 * Clase donde se definen las posibles banderas y actividades, y donde se
 * definen métodos para coger la posición de esas cadenas.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class DatosBanderasActividades {

	/**
	 * Array de cadenas que define las banderas posibles de las que se compone
	 * la aplicación.
	 */
	public static final String[] NOMBRE_BANDERAS = { "m_principal", "m_vistaCam", "m_configCam", "m_productos", "m_contacto" };
	/**
	 * Array de cadenas que define las actividades físicas detectables por la
	 * librería AAR.
	 */
	public static final String[] NOMBRE_ACTIVIDADES = { "Vehiculo", "Bicicleta", "Andando-Corriendo", "Corriendo",
			"Parado", "Parado-Inclinado", "Caminando", "Desconocido" };
	
	
	public static final String[] NOMBRE_DURACIONES = { "Muy_corto", "Corto", "Medio", "Largo", "Muy_largo"};

	/**
	 * Método para conseguir la posición de la bandera tomada.
	 * 
	 * @param bandera
	 *            cadena de la que se requiere la posición.
	 * @return entero representado la posición de dicha cadena.
	 */
	public static int getIdBandera(String bandera) {
		int contador = 0;
		boolean seguirContado = true;
		for (String band : NOMBRE_BANDERAS) {
			if (seguirContado) {
				if (!band.equalsIgnoreCase(bandera))
					contador++;
				else
					seguirContado = false;
			}
		}
		return NOMBRE_ACTIVIDADES.length + contador;
	}

	/**
	 * Método para conseguir la posición de la actividad tomada.
	 * 
	 * @param actividad
	 *            cadena de la que se requiere la posición.
	 * @return entero representado la posición de dicha cadena.
	 */
	public static int getIdActividad(String actividad) {
		int contador = 0;
		boolean seguirContado = true;
		for (String act : NOMBRE_ACTIVIDADES) {
			if (seguirContado) {
				if (!act.equalsIgnoreCase(actividad))
					contador++;
				else
					seguirContado = false;
			}
		}
		return contador;
	}

	public static int getIdDuracion(String duracion) {
		int contador = 0;
		boolean seguirContado = true;
		for (String act : NOMBRE_DURACIONES) {
			if (seguirContado) {
				if (!act.equalsIgnoreCase(duracion))
					contador++;
				else
					seguirContado = false;
			}
		}
		return contador;
	}
}
