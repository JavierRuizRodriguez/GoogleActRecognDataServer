package com.googleActRecognDataServer.api.cassandra.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.googleActRecognDataServer.api.cassandra.pojos.Baliza;

public class BalizaCrudImpl implements BalizaCrud {

	@Autowired
	private BalizaDao balizaDao;

	public BalizaCrudImpl() {
		super();
	}

	@Override
	@Transactional
	public Baliza añadirBaliza(Baliza baliza) {
		return balizaDao.añadirBaliza(baliza);
	}

	@Override
	@Transactional
	public List<Baliza> cogerTodasBalizasUltimoDia() {
		return balizaDao.cogerTodasBalizasUltimoDia();
	}

}
