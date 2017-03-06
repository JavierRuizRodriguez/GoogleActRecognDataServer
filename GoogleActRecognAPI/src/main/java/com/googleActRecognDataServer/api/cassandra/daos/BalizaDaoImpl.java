package com.googleActRecognDataServer.api.cassandra.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.googleActRecognDataServer.api.cassandra.pojos.Baliza;

public class BalizaDaoImpl implements BalizaDao{

	@Autowired
    private CassandraTemplate cassandraTemplate;
	
	@Override
	public Baliza añadirBaliza(Baliza baliza) {
		return cassandraTemplate.create(baliza, Baliza.class);
	}

	@Override
	public List<Baliza> cogerTodasBalizasUltimoDia() {
		return cassandraTemplate.cogerTodasBalizasUltimoDia();
	}

}
