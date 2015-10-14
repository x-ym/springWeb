package org.xia.www.springWeb.web.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

public interface IBaseDao {

	JdbcTemplate getJdbcTemplate();

	NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();

	Serializable save(Object entity);

	/**
	 * @param entity
	 */
	void saveOrUpdate(Object entity);

	<T extends Serializable> void saveOrUpdateAll(Collection<T> collection);

	/**
	 * @param entityClass
	 * @param id
	 */
	<T extends Serializable> T get(Class<T> clazz, Serializable id);

	/**
	 * @param entity
	 */
	void delete(Object entity);

	/**
	 * @param entity
	 */
	void merge(Object entity);

	/**
	 * @param entity
	 */
	<T extends Serializable> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object value);

	<T extends Serializable> T findByProperty4Object(Class<T> clazz,
			String propertyName, Object value);

	/**
	 * @param instance
	 * @return
	 */
	<T extends Serializable> List<T> findByExample(T instance);

	/**
	 * @param instance
	 * @return
	 */
	<T extends Serializable> List<T> findAll(Class<T> clazz);

	HibernateTemplate getHibernateTemplate();

	void update(Object entity);

	void persist(Object entity);

	<T extends Serializable> T merge(T entity);

	SessionFactory getSF();

}
