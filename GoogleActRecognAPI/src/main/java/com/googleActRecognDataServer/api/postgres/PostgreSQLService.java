package com.googleActRecognDataServer.api.postgres;

import org.apache.ibatis.annotations.Param;

import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;

public interface PostgreSQLService {

	public void nuevaActividad(@Param("id") String idCliente, @Param("actividad") ActividadGoogle actividad);

	public void nuevoIdCliente(String idCliente);

}
