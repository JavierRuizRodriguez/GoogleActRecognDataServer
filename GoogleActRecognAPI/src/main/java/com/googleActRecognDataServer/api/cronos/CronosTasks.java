package com.googleActRecognDataServer.api.cronos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.rosuda.JRI.Rengine;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.googleActRecognDataServer.api.postgres.pojos.ContadorInformes;
import com.googleActRecognDataServer.api.postgres.pojos.MotorR;
import com.googleActRecognDataServer.api.utilidades.GeneradorMatrizDatos;
import com.itextpdf.text.DocumentException;

/**
 * Clase que sirve para la realización de tareas rutinarias dentro del servidor.
 * Concretamente sirve para realizar las tareas de análisis estadístico de
 * datos.
 * 
 * @author Javier Ruiz Rodríguez *
 * 
 */
@Configuration
@EnableScheduling
public class CronosTasks {

	/**
	 * Objeto que representa el motor de la tecnología R.
	 */
	private Rengine motorR;

	private GeneradorMatrizDatos cmd;

	private String directorio;

	/**
	 * Tarea que se ejecuta cada día a las 00:00 horas. Coge datos del servidor
	 * y los trata para convertirlos en información. Como consecuencia, se crean
	 * informes que contienen la información conseguida.
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 */
	@Scheduled(fixedRate = 5000)
	//@Scheduled(cron = "0 0 0 * * *", zone = "Europe/Madrid")
	public void realizarAnalisisEstadistico() throws DocumentException, IOException {

		inicializarVariables();
		cargarLibreriasR();

		manejarLog(1);
		lecturaDatos();
		obtenerGraficas();
		obtenerResultados();
		manejarLog(0);

		cmd.copiarTxtPdf();
	}

	/**
	 * Método para administrar el log de R que recopilará los resultados.
	 * 
	 * @param modo
	 *            1 significa iniciar, cualquier valor distinto, apagar.
	 */
	private void manejarLog(int modo) {
		if (modo == 1) {
			motorR.eval("log<-file('datosR/informe.txt')");
			motorR.eval("sink(log, append=TRUE)");
			motorR.eval("sink(log, append=TRUE, type='message')");
		} else {
			motorR.eval("sink(NULL)");
		}
	}

	/**
	 * Método que sirve para la inicialización de las variables implicadas en
	 * esta clase.
	 */
	private void inicializarVariables() {
		cmd = new GeneradorMatrizDatos();

		motorR = MotorR.getInstacia().getMotorR();
		directorio = System.getProperty("user.dir");
		directorio = directorio.replace("\\", "/");

		if (!motorR.waitForR()) {
			System.out.println("Cannot load R");
		}
	}

	/**
	 * Método encargado de cargar las librerías de R necesarias para el
	 * tratamiento de datos.
	 */
	private void cargarLibreriasR() {
		motorR.eval("library('plyr')");
		motorR.eval("library(\"arules\")");
		motorR.eval("library('tree')");
	}

	/**
	 * Método para la inserción de títulos en el informe.
	 * 
	 * @param titulo
	 *            cadena que representa el título.
	 */
	private void nuevoTituloSeccion(String titulo) {
		motorR.eval("cat(\"----------------------------------------------------------------\", sep=\"\\n\\n\")");
		motorR.eval("cat(\"" + titulo + "\", sep=\"\\n\\n\")");
		motorR.eval("cat(\"----------------------------------------------------------------\", sep=\"\\n\\n\")");
		motorR.eval("cat(\"----------------------------------------------------------------\", sep=\"\\n\\n\")");
	}

	/**
	 * Método que lee los datos desde la base de datos y los carga en el entorno
	 * de R. Es el encargado del tratamiento de los datos.
	 */
	private void lecturaDatos() {
		cmd.montarTablaDatos();

		motorR.eval("tabla <- read.table(\"" + directorio + "/datosR/matriz.txt\", header = TRUE)");

		motorR.eval("tabla.supervisada <- tabla");
		motorR.eval("tabla.supervisada <- ddply(tabla,.(Actividad, Bandera),nrow)");
		motorR.eval("colnames(tabla.supervisada)[3] <- \"Total\"");
		motorR.eval("tabla.supervisada <- tree(Bandera ~., data=tabla.supervisada, mincut=1, minsize=2)");

		motorR.eval("tabla.actividades <- ddply(tabla,.(Actividad),nrow)");
		motorR.eval("tabla.actividades <- tabla.actividades[order(tabla.actividades$V1, decreasing = TRUE),]");

		motorR.eval("tabla.banderas <- ddply(tabla,.(Bandera),nrow)");
		motorR.eval("tabla.banderas <- tabla.banderas[order(tabla.banderas$V1, decreasing = TRUE),]");

		motorR.eval("tabla <- tratarTabla(tabla)");
		motorR.eval("tabla.cluster <- tabla");
		motorR.eval("rownames(tabla.cluster) <- tabla.cluster$ActBan");

		motorR.eval("tabla.cluster <- hclust(dist(tabla.cluster,\"euclidean\", TRUE, TRUE))");
		motorR.eval("tabla.cluster$call <- NULL");

		cmd.borrarArchivo("datosR/matriz.txt");
		cmd.montarMatrizDatos();

		motorR.eval("matrizTabla <- read.table(\"" + directorio + "/datosR/matriz.txt\", header = TRUE)");
		motorR.eval("matriz <- as.matrix(matrizTabla)");
		motorR.eval("(transacciones = as(matriz,\"transactions\"))");

		cmd.borrarArchivo("datosR/matriz.txt");
	}

	/**
	 * Método encargado de la generación de las distintas gráficas que
	 * conformarán el informe.
	 */
	private void obtenerGraficas() {

		motorR.eval("pdf(\"" + directorio + "/datosR/grafico.pdf\", paper = \"a4\")");

		motorR.eval(
				"hist(tabla.actividades$V1,breaks=nrow(tabla.actividades),col=\"lightblue\",border=\"black\",xlim=c(0,max(tabla.actividades$V1) + nchar(as.integer(max(tabla.actividades$V1))) *10), main =\"Histograma de Actividades\", xlab=\"Totales Actividades\")");
		motorR.eval(
				"barplot(tabla.actividades$V1, names.arg = tabla.actividades$Actividad, las = 2, cex.names = 0.6,  main =\"Diagrama de barras de Actividades\")");

		motorR.eval(
				"hist(tabla.banderas$V1,breaks=nrow(tabla.banderas),col=\"lightblue\",border=\"black\",xlim=c(0,max(tabla.banderas$V1) + nchar(as.integer(max(tabla.banderas$V1))) * 10), main =\"Histograma de Banderas\", xlab=\"Totales Banderas\")");
		motorR.eval(
				"barplot(tabla.banderas$V1, names.arg = tabla.banderas$Bandera, las = 2, cex.names = 0.6,  main =\"Diagrama de barras de Banderas\")");

		motorR.eval(
				"hist(tabla$Total,breaks=nrow(tabla),col=\"lightblue\",border=\"black\",xlim=c(0,max(tabla$Total) + nchar(as.integer(max(tabla$Total))) * 10), main =\"Histograma tupla Actividad-Bandera\", xlab=\"Totales tuplas Actividad-Bandera\")");
		motorR.eval(
				"barplot(tabla$Total, names.arg = tabla$ActBan, las = 2, cex.names = 0.6,  main =\"Diagrama de barras tupla Actividad-Bandera\")");

		motorR.eval("boxplot(tabla$Total, main=\"Diagrama de Caja y Bigotes\")");
		motorR.eval("plot(tabla.cluster, hang = -1, main=\"Dendograma tupla Actividad-Bandera\", xlab=\"IDs Actividades-Bandera\")");

		motorR.eval("plot(tabla.supervisada)");
		motorR.eval("text(tabla.supervisada, cex = 0.5)");

		motorR.eval("dev.off()");
	}

	/**
	 * Método para la obtención de los resultados de los datos tratados.
	 */
	private void obtenerResultados() {
		nuevoTituloSeccion("INFORMACIÓN TABLA DE DATOS");
		motorR.eval("print(tabla)");

		nuevoTituloSeccion("DIAGRAMA DE TALLO Y HOJAS");
		motorR.eval("dTalloHojas(tabla)");

		nuevoTituloSeccion("INFORMACION CLASIFICACION SUPERVISADA SOBRE VARIABLE BANDERA");
		motorR.eval("print(tabla.supervisada)");

		nuevoTituloSeccion("SUMARIO DE LAS TRANSACCIONES");
		motorR.eval("print(summary(transacciones))");

		nuevoTituloSeccion("RESULTADO ASOCIACIONES SOPORTE 40% Y CONFIANZA 80%");
		motorR.eval("asociaciones = apriori(transacciones, parameter=list(support=0.4, confidence=0.8))");

		nuevoTituloSeccion("INSPECCION ASOCIACIONES");
		motorR.eval("inspect(asociaciones)");
		
		nuevoTituloSeccion("RESULTADO ASOCIACIONES SOPORTE 50% Y CONFIANZA 80%");
		motorR.eval("asociaciones = apriori(transacciones, parameter=list(support=0.5, confidence=0.8))");

		nuevoTituloSeccion("INSPECCION ASOCIACIONES");
		motorR.eval("inspect(asociaciones)");
	}

}
