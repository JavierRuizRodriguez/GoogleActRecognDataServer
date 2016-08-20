package com.googleActRecognDataServer.api.postgres;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Class to instance 'PostgreSQLService' interface.
 * 
 * @author AceroDocs
 *
 */

@Configuration
@ImportResource("classpath:/MyBatis.xml")
public class PostgreSQL {

	/**
	 * Interface instance 'PostgreSQLService'.
	 */
	private static SqlSessionFactory factory;

	/**
	 * Method that initiates 'PostgreSQLService' variable.
	 * 
	 * @param postgreSQLService
	 */

	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		PostgreSQL.factory = factory;
	}

	public static SqlSessionFactory getFactory() {
		return factory;
	}
	

	/**
	 * Method to get 'postgreSQLService' variable value.
	 * 
	 * @return postgreSQLService 'postgreSQLService' object.
	 */
	public static PostgreSQLService getPostgresInterface() {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(getFactory());
		return sessionTemplate.getMapper(PostgreSQLService.class);
	}

}
