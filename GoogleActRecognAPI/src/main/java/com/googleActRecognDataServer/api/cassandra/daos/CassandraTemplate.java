package com.googleActRecognDataServer.api.cassandra.daos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.googleActRecognDataServer.api.cassandra.pojos.Baliza;

public class CassandraTemplate {

	// @Query("INSERT INTO cliente_actividad
	// (adv_id_cliente,ins_id_cliente,timestamp,actividad,bandera,coeficiente,duracion)
	// VALUES (?0,?1,?2,?3,?4,?5,?6)")

	@Autowired
	private CassandraOperations motorCassandra;

	/**
	 * Creating the entity.
	 * 
	 * @param entity
	 * @return {@link Object}
	 */
	public <T> T create(T entity) {
		return motorCassandra.insert(entity);
	}

	/**
	 * Creating the entity.
	 * 
	 * @param entity
	 * @param claz
	 * @return T
	 */
	public <T> T create(T entity, Class<T> claz) {
		return (T) motorCassandra.insert(entity);
	}

	/**
	 * Creating the list of entities.
	 * 
	 * @param entity
	 */
	public <T> void createList(List<T> entities) {
		motorCassandra.insert(entities);
	}

	/**
	 * Updating the entity.
	 * 
	 * @param entity
	 * @param claz
	 * @return T
	 */
	public <T> T update(T entity) {
		return (T) motorCassandra.update(entity);
	}

	/**
	 * Updating the list of entities.
	 * 
	 * @param entity
	 * @param claz
	 * @return T
	 */
	public <T> void updateList(List<T> entities) {
		motorCassandra.update(entities);
	}

	/**
	 * Updating the entity.
	 * 
	 * @param entity
	 * @param claz
	 * @return T
	 */
	public <T> T update(T entity, Class<T> claz) {
		return (T) motorCassandra.update(entity);
	}

	/**
	 * Get the Entity using Id.
	 * 
	 * @param id
	 * @param claz
	 * @return T
	 */
	public <T> T findById(Object id, Class<T> claz) {
		return motorCassandra.selectOneById(claz, id);
	}

	/**
	 * Delete the Entity using Id.
	 * 
	 * @param id
	 * @param claz
	 */
	public <T> void deleteById(Object id, Class<T> claz) {
		motorCassandra.deleteById(claz, id);
	}

	/**
	 * Delete the Entity using object.
	 * 
	 * @param entity
	 */
	public void delete(Object entity) {
		motorCassandra.delete(entity);
	}

	/**
	 * Deleting the list of entities
	 * 
	 * @param entities
	 */
	public <T> void delete(List<T> entities) {
		motorCassandra.delete(entities);
	}

	/**
	 * Deleting the all entities.
	 * 
	 * @param claz
	 */
	public <T> void deleteAll(Class<T> claz) {
		motorCassandra.deleteAll(claz);
	}

	/**
	 * Getting the all entities.
	 * 
	 * @param claz
	 * @return List of entities
	 */
	public <T> List<T> findAll(Class<T> claz) {
		return (List<T>) motorCassandra.selectAll(claz);
	}

	/**
	 * Getting the all entities.
	 * 
	 * @param claz
	 * @return List of entities
	 */
	public List<Baliza> cogerTodasBalizasUltimoDia() {
		DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DATE, -2);

		Select select = QueryBuilder.select().from("cliente_actividad");
		select.where(QueryBuilder.eq("etiqueta_fecha", formatoFecha.format(calendario.getTime())));

		return new ArrayList<Baliza>(motorCassandra.select(select, Baliza.class));

	}

	/**
	 * Getting the all entity values using specific id's data.
	 * 
	 * @param ids
	 * @param claz
	 * @return
	 */
	public <T> List<T> findAll(List<Object> ids, Class<T> claz) {
		return motorCassandra.selectBySimpleIds(claz, ids);
	}

	/**
	 * Getting the count of records.
	 * 
	 * @param claz
	 * @return the count value.
	 */
	public <T> void truncate(Class<T> claz) {
		motorCassandra.truncate(claz.getName());
	}

	/**
	 * Getting the count of records.
	 * 
	 * @param claz
	 * @return the count value.
	 */
	public <T> long getCount(Class<T> claz) {
		return motorCassandra.count(claz);
	}

	/**
	 * Checking the object exists or not.
	 * 
	 * @param id
	 * @param claz
	 * @return true if the object exists in the database otherwise it will
	 *         return false.
	 */
	public <T> boolean exists(Object id, Class<T> claz) {
		return motorCassandra.exists(claz, id);
	}
}
