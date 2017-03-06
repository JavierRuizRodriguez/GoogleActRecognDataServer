package com.googleActRecognDataServer.api.cassandra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.googleActRecognDataServer.api.cassandra.daos.BalizaCrud;
import com.googleActRecognDataServer.api.cassandra.daos.BalizaCrudImpl;
import com.googleActRecognDataServer.api.cassandra.daos.BalizaDao;
import com.googleActRecognDataServer.api.cassandra.daos.BalizaDaoImpl;
import com.googleActRecognDataServer.api.cassandra.daos.CassandraTemplate;

@Configuration
@Import(ConfiguracionCassandra.class)
public class ConfiguracionCassandraDaos {
	
	@Bean
    public BalizaCrud getEmployeeService() {
        return new BalizaCrudImpl();
    }
	
	@Bean
    public BalizaDao getEmployeeDAO() {
        return new BalizaDaoImpl();
    }
    
    @Bean
    public CassandraTemplate getMyCassandraTemplate() {
        return new CassandraTemplate();
    }
}
