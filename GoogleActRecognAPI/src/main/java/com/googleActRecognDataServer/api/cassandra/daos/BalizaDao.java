package com.googleActRecognDataServer.api.cassandra.daos;

import java.util.List;
import com.googleActRecognDataServer.api.cassandra.pojos.Baliza;

public interface BalizaDao {

	public Baliza añadirBaliza(Baliza baliza);
	
	public List<Baliza> cogerTodasBalizasUltimoDia();
}
