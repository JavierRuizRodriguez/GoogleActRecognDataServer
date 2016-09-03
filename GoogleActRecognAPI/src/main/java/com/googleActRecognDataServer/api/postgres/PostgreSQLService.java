package com.googleActRecognDataServer.api.postgres;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.googleActRecognDataServer.api.postgres.pojos.ActividadBandera;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

public interface PostgreSQLService {

	public void nuevaActividad(@Param("ids") IdsCliente ids, @Param("actividad") ActividadGoogle actividad, @Param("bandera") String bandera);

	public void nuevoIdCliente(@Param("ids") IdsCliente ids);
	
	public List<ActividadGoogle> cogerUltimaActividadPorIds(@Param("ids") IdsCliente ids);
	
	public String cogerUltimaBanderaPorIds(@Param("ids") IdsCliente ids);
	
	public List<ActividadBandera> cogerActividadesUltimoDia();
}
