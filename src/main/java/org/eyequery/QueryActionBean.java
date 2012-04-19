package org.eyequery;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named("queryAction") @RequestScoped
public class QueryActionBean {
	
	@Inject
	private Query query;
	
	public QueryActionBean() {}
	
	public List<?> run() {
		
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("eyeQueryPU");
		EntityManager em = emf.createEntityManager();
		
		
		return em.createQuery(query.getQuery()).getResultList();
	}
	
}
