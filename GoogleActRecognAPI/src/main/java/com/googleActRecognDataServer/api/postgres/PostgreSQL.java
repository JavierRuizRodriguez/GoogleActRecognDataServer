package com.googleActRecognDataServer.api.postgres;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Clase que registra el bean de conexión a la base de datos.
 * 
 * @author Javier Ruiz Rodríguez
 * 
 */
@Configuration
@ImportResource("classpath:/MyBatis.xml")
public class PostgreSQL {

	/**
	 * Representa una instancia de la clase ServicioPostgreSQL.java
	 */
	private static SqlSessionFactory factory;

	/**
	 * Método que sirve para instanciar la variable 'factory'.
	 * 
	 * @param factory representa un objeto de la clase ServicioPostgreSQL.
	 */
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		PostgreSQL.factory = factory;
	}

	/**
	 * Método para coger la variable 'factory'.
	 * 
	 * @return instancia de 'factory'.
	 */
	public static SqlSessionFactory getFactory() {
		return factory;
	}

	/**
	 * Método para coger el valor de la variable instanciada de la clase
	 * ServicioPostgreSQL.java.
	 * 
	 * @return objeto del tipo ServicioPostgreSQL.
	 */
	public static ServicioPostgreSQL getInterfazPostgreSQL() {
		SqlSessionTemplate plantillaSesionPostgreSQL = new SqlSessionTemplate(getFactory());
		return plantillaSesionPostgreSQL.getMapper(ServicioPostgreSQL.class);
	}

}
