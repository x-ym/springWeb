package org.xia.www.springWeb.web.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.xia.www.springWeb.web.base.dao.IBaseDao;

@Repository
public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao {
	private static final Log log = LogFactory.getLog(IBaseDao.class);
	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public SessionFactory getSF() {
		return super.getSessionFactory();
	}

	public Serializable save(Object entity) {
		return super.getHibernateTemplate().save(entity);
	}

	public void update(Object entity) {
		super.getHibernateTemplate().update(entity);
	}

	public void persist(Object entity) {
		super.getHibernateTemplate().persist(entity);
	}

	public <T extends Serializable> T merge(T entity) {
		return super.getHibernateTemplate().merge(entity);
	}

	public void saveOrUpdate(Object entity) {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(Object entity) {
		super.getHibernateTemplate().delete(entity);
	}

	public <T extends Serializable> T get(Class<T> clazz, Serializable id) {
		return (T) super.getHibernateTemplate().get(clazz, id);
	}

	public <T extends Serializable> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object value) {
		log.debug("finding " + clazz.getSimpleName()
				+ " instance with property: " + propertyName + ", value: "
				+ value);
		try {
			String queryString = "from " + clazz.getSimpleName()
					+ " as model where model." + propertyName + "= ?";
			Query queryObject = super.getHibernateTemplate()
					.getSessionFactory().getCurrentSession()
					.createQuery(queryString);
			queryObject.setParameter(0, value);

			@SuppressWarnings("unchecked")
			List<T> list = queryObject.list();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public <T extends Serializable> T findByProperty4Object(Class<T> clazz,
			String propertyName, Object value) {
		log.debug("finding " + clazz.getSimpleName()
				+ " instance with property: " + propertyName + ", value: "
				+ value);
		try {
			String queryString = "from " + clazz.getSimpleName()
					+ " as model where model." + propertyName + "= ?";
			Query queryObject = super.getHibernateTemplate()
					.getSessionFactory().getCurrentSession()
					.createQuery(queryString);
			queryObject.setParameter(0, value);

			@SuppressWarnings("unchecked")
			T result = (T) queryObject.uniqueResult();
			return result;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void merge(Object entity) {
		super.getHibernateTemplate().merge(entity);
	}

	public <T extends Serializable> List<T> findByExample(T instance) {
		log.debug("finding " + instance.getClass().getSimpleName()
				+ " instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<T> results = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(instance.getClass())
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public <T extends Serializable> List<T> findAll(Class<T> clazz) {
		log.debug("findAll " + clazz.getSimpleName() + " instance");
		try {
			String queryString = "from " + clazz.getSimpleName() + " as model ";
			Query queryObject = super.getHibernateTemplate()
					.getSessionFactory().getCurrentSession()
					.createQuery(queryString);

			@SuppressWarnings("unchecked")
			List<T> list = queryObject.list();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 得到分页的sql语句
	 */
	public String getPageSqlWithMap4Mysql(String sql) {
		StringBuffer stringBuffer = new StringBuffer(sql);
		stringBuffer.append("  LIMIT :start ,:limit ");
		return stringBuffer.toString();
	}

	/**
	 * 得到分页的sql语句
	 */
	public String getPageSqlWithMap4Oracel(String sql) {
		String pagesql = String
				.format("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (%s) A WHERE ROWNUM <= :o_end_rownum)WHERE RN > :o_start_rownum",
						sql);
		return pagesql;
	}

	public <T extends Serializable> void saveOrUpdateAll(
			Collection<T> collection) {
		for (T entry : collection) {
			 saveOrUpdate(entry);
		}
	}

}
