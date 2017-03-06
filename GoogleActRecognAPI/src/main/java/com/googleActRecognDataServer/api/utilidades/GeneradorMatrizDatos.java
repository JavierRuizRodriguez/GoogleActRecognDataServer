package com.googleActRecognDataServer.api.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.googleActRecognDataServer.api.cassandra.daos.BalizaCrud;
import com.googleActRecognDataServer.api.cassandra.pojos.Baliza;
import com.googleActRecognDataServer.api.pojos.ContadorInformes;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Clase encargada de montar la matriz de datos que será utilizada por el módulo
 * R para el tratamiento de datos.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class GeneradorMatrizDatos {

	/**
	 * Objeto para escribir fichero en el sistema de archivos.
	 */
	private PrintWriter escritorTxt;

	private ArrayList<Baliza> balizas;

	private final long CORTO = 15000;
	private final long MEDIO = 45000;
	private final long LARGO = 90000;
	private final long MUY_LARGO = 300000;

	/**
	 * Constructor de clase.
	 */
	public GeneradorMatrizDatos(ApplicationContext applicationContext) {
		BalizaCrud balizaCrud = applicationContext.getBean(BalizaCrud.class);
		balizas = new ArrayList<>(balizaCrud.cogerTodasBalizasUltimoDia());
	}

	/**
	 * Método principal encargado de llamar paso a paso a los distintos métodos
	 * que construyen la matriz.
	 */
	public void montarMatrizDatos() {
		try {
			this.escritorTxt = new PrintWriter("datosR/matriz.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		montarCabezeraMatriz();
		montarCuerpoMatriz();

		escritorTxt.close();
	}

	/**
	 * Método para crear el cuerpo de la matriz.
	 */
	private void montarCuerpoMatriz() {
		int idBandera;
		int idActividad;
		int idDuracion;

		if (balizas != null && balizas.size() > 0) {
			for (Baliza baliza : balizas) {
				idBandera = DatosBanderasActividades.getIdBandera(baliza.getBandera());
				idActividad = DatosBanderasActividades.getIdActividad(baliza.getActividad());
				idDuracion = DatosBanderasActividades.getIdDuracion(filtrarTiempo(baliza.duracion));
				
				String fila = crearCadenaBinaria(idActividad, idBandera, idDuracion);
				fila = fila.substring(0, fila.length() - 1);
				
				escritorTxt.print(fila);
				escritorTxt.println();
			}
		}
	}

	/**
	 * Método para crear una cadena binaria que representa cada fila de la
	 * matriz.
	 * 
	 * @param idActividad
	 *            entero que representa la posición de la actividad dentro de la
	 *            cabecera de la matriz
	 * @param idBandera
	 *            entero que representa la posición de la bandera dentro de la
	 *            cabecera de la matriz
	 * @param idDuracion
	 *            entero que representa la posición de la bandera dentro de la
	 *            cabecera de la matriz
	 * @return cadena binaria que representa una fila de la matriz
	 */
	private String crearCadenaBinaria(int idActividad, int idBandera, int idDuracion) {
		String cadenaBinaria = "";
		for (int i = 0; i <= (DatosBanderasActividades.NOMBRE_ACTIVIDADES.length
				+ DatosBanderasActividades.NOMBRE_BANDERAS.length + DatosBanderasActividades.NOMBRE_DURACIONES.length
				- 1); i++) {
			if (i == idActividad || i == idBandera || i == idDuracion)
				cadenaBinaria += "1 ";
			else
				cadenaBinaria += "0 ";
		}
		return cadenaBinaria;
	}

	/**
	 * Método para construir la cabecera de la matriz.
	 */
	private void montarCabezeraMatriz() {
		String cabecera = "";
		for (String cadena : DatosBanderasActividades.NOMBRE_ACTIVIDADES)
			cabecera += cadena + " ";
		for (String cadena : DatosBanderasActividades.NOMBRE_BANDERAS)
			cabecera += cadena + " ";
		for (String cadena : DatosBanderasActividades.NOMBRE_DURACIONES)
			cabecera += cadena + " ";

		cabecera = cabecera.substring(0, cabecera.length() - 1);

		escritorTxt.print(cabecera);
		escritorTxt.println("");
	}

	/**
	 * Método para borrar los archivos generados para representar la matriz.
	 */
	public void borrarArchivo(String ubicacion) {
		File file = new File(ubicacion);
		if (file.delete())
			System.out.println("Documento borrado.");
		else
			System.out.println("Error --> No es posible borrar el documento.");
	}

	/**
	 * Método de generación de la tabla de datos que se usará en el entorno R.
	 */

	public void montarTablaDatos() {
		try {
			this.escritorTxt = new PrintWriter("datosR/matriz.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//Collections.reverse(balizas);

		escritorTxt.print("Actividad Bandera Cliente Duracion");
		escritorTxt.println();

		for (Baliza b : balizas) {
			escritorTxt.print(b.actividad.toString() + " " + b.bandera.toString() + " " + b.cogerIdsConcatenados() + " "
					+ filtrarTiempo(b.duracion));
			escritorTxt.println();
		}
		escritorTxt.close();
	}

	/**
	 * Método para juntar los datos obtenido en el log, en formato .txt, y las
	 * gráficas obtenidas en formato .pdf.
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void copiarTxtPdf() throws DocumentException, IOException {
		String nombreInforme = "Informe_n" + ContadorInformes.getInstancia().getIdContadorInformes() + "_"
				+ new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		BufferedReader docTxt = null;
		OutputStream informe = new FileOutputStream("datosR/" + nombreInforme + ".pdf");
		Document doc = new Document(PageSize.A4);
		doc.setMargins(75, 75, 50, 50);
		PdfWriter escritor = PdfWriter.getInstance(doc, informe);
		doc.open();
		PdfContentByte cb = escritor.getDirectContent();
		PdfReader pdfLectura = new PdfReader("datosR/grafico.pdf");

		Font fuenteTitulo1 = new Font();
		fuenteTitulo1.setSize(64);
		Font fuenteTitulo2 = new Font();
		fuenteTitulo2.setSize(20);
		fuenteTitulo2.setStyle(Font.BOLD);

		Paragraph parrafoTitulo1 = new Paragraph("Informe diario sobre reconocimiento de actividades", fuenteTitulo1);
		Paragraph parrafoTitulo2 = new Paragraph("\n\n\nNombre: " + nombreInforme, fuenteTitulo2);
		parrafoTitulo1.setAlignment(Element.ALIGN_CENTER);
		parrafoTitulo2.setAlignment(Element.ALIGN_CENTER);

		doc.newPage();
		doc.add(parrafoTitulo1);
		doc.add(parrafoTitulo2);

		for (int i = 1; i <= pdfLectura.getNumberOfPages(); i++) {
			doc.newPage();
			if (i == 9) {
				Paragraph p = new Paragraph("DIAGRAMA CLASIFICACION SUPERVISADA SOBRE VARIABLE BANDERA", fuenteTitulo2);
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				doc.add(p);
			}
			PdfImportedPage pagina = escritor.getImportedPage(pdfLectura, i);
			cb.addTemplate(pagina, 0, 0);
		}

		doc.newPage();

		docTxt = new BufferedReader(new FileReader("datosR/informe.txt"));
		String linea = "";
		while (null != (linea = docTxt.readLine())) {
			System.out.println(linea);
			Paragraph p = new Paragraph(linea);
			p.setAlignment(Element.ALIGN_JUSTIFIED);
			doc.add(p);
		}

		docTxt.close();
		doc.close();
		informe.close();

		borrarArchivo("datosR/informe.txt");
		borrarArchivo("datosR/grafico.pdf");
	}

	private String filtrarTiempo(long duracion) {

		if (duracion <= CORTO)
			return "MUY_CORTO";
		else if (duracion <= MEDIO)
			return "CORTO";
		else if (duracion <= LARGO)
			return "MEDIO";
		else if (duracion <= MUY_LARGO)
			return "LARGO";
		else
			return "MUY_LARGO";
	}

}
