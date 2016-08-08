package com.googleActRecognDataServer.api.pojo;

public class ActividadesGoogle {
	public String actividad;
	public int coeficiente;
	
	public ActividadesGoogle(String actividad, int coeficiente) {
		super();
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
