package br.com.cartaoamigo.infra.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T> {

	@PersistenceContext
	protected EntityManager em;

	private final Class<T> objectClass;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.objectClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T getPorId(Serializable id) {
		return em.find(objectClass, id);
	}

	public List<T> getAll() {
		return em.createQuery("select t from " + objectClass.getSimpleName() + " t", objectClass).getResultList();
	}

	public T salvar(T entity) {
		em.persist(entity);
		return entity;
	}

	public void delete(T entity) {
		em.remove(entity);
	}
	
	public void merge(T entity) {
		em.merge(entity);
	}

	public void flush() {
	    em.flush();
	}
}
