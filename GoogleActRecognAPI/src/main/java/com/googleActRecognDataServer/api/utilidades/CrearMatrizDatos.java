package com.googleActRecognDataServer.api.utilidades;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.googleActRecognDataServer.api.datosR.DatosBanderasActividades;
import com.googleActRecognDataServer.api.postgres.PostgreSQL;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadBandera;

public class CrearMatrizDatos {

	private PrintWriter escritorTxt;

	public CrearMatrizDatos() {
		try {
			this.escritorTxt = new PrintWriter("datosR/matriz.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void montarMatrizDatos() {
		montarCabezeraMatriz();
		montarCuerpoMatriz();		
		escritorTxt.close();
	}

	private void montarCuerpoMatriz() {
		int idBandera;
		int idActividad;
		for(ActividadBandera tupla : PostgreSQL.getPostgresInterface().cogerActividadesUltimoDia()){
			idBandera = DatosBanderasActividades.getIdBandera(tupla.getBandera());
			idActividad = DatosBanderasActividades.getIdActividad(tupla.getActividad());
			escritorTxt.print(crearCadenaBinaria(idActividad, idBandera));
			escritorTxt.println();
		}		
	}

	private String crearCadenaBinaria(int idActividad, int idBandera) {
		String cadenaBinaria = "";
		for(int i=0; i<=(DatosBanderasActividades.NOMBRE_ACTIVIDADES.length + DatosBanderasActividades.NOMBRE_BANDERAS.length); i++){
			if(i == idActividad || i == idBandera)
				cadenaBinaria += "1 ";
			else
				cadenaBinaria += "0 ";
		}
		return cadenaBinaria;
	}

	private void montarCabezeraMatriz() {
		for (String cadena : DatosBanderasActividades.NOMBRE_ACTIVIDADES)
			escritorTxt.print(cadena + " ");
		for (String cadena : DatosBanderasActividades.NOMBRE_BANDERAS)
			escritorTxt.print(cadena + " ");
		escritorTxt.println("");
	}

}
