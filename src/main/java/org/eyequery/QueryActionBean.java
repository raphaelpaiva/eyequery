package org.eyequery;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eyequery.testEntities.Fruit;

@Named("queryAction") @RequestScoped
public class QueryActionBean {
	
	@Inject
	private Query query;
	
	public QueryActionBean() {}
	
	public void run() {
		
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("eyeQueryPU");
		EntityManager em = emf.createEntityManager();
		
		em.persist(new Fruit("banana"));
		em.persist(new Fruit("mango"));
		em.persist(new Fruit("orange"));
		
		System.out.println("------");
		System.out.println(em.createQuery(query.getQuery()).getResultList());
	}
	
}
