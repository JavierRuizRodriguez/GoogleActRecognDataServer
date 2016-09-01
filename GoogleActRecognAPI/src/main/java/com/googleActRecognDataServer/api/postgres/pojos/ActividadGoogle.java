package com.googleActRecognDataServer.api.postgres.pojos;

public class ActividadGoogle {
	public String actividad;
	public int coeficiente;
	
	public ActividadGoogle(){}
	
	public ActividadGoogle(String actividad, int coeficiente) {
		this.actividad = actividad;
		this.coeficiente = coeficiente;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public int getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(int coeficiente) {
		this.coeficiente = coeficiente;
	}
}
