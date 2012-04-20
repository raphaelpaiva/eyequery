package org.eyequery;

import java.util.ArrayList;
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
	
	private List<?> resultList = new ArrayList<Object>();
	
	public QueryActionBean() {}
	
	public void run() {
		
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("eyeQueryPU");
		EntityManager em = emf.createEntityManager();
		
		resultList = em.createQuery(query.getQuery()).getResultList();
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
	
}
