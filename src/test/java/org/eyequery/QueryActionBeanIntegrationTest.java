package org.eyequery;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.eyequery.testEntities.Fruit;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class QueryActionBeanIntegrationTest
{
	private EntityManager entityManager = null;
	
	private static Fruit[] FRUITS = {new Fruit("banana"), new Fruit("apple"), new Fruit("mango")};
	
	@Inject
	private QueryActionBean queryActionbean;
	
	@Inject
	private Query query;
	
	@Deployment
	public static WebArchive createDeployment()
	{
		return ShrinkWrap.create(WebArchive.class, "eyequery-test.war")
				.addPackage(QueryActionBean.class.getPackage())
				.addManifestResource("persistence-test.xml", "persistence.xml")
				.addWebResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	public EntityManager createEntityManager()
	{
		return Persistence.createEntityManagerFactory("eyeQueryPU").createEntityManager();
	}
	
	public void populateFruits()
	{
		getEntityManager().getTransaction().begin();
		for (int i = 0; i < FRUITS.length; i++)
		{
			getEntityManager().persist(FRUITS[i]);
			System.out.println("Persisted " + FRUITS[i]);
		}
		getEntityManager().getTransaction().commit();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testRun_SelectAllFruits()
	{
		populateFruits();
		
		query.setQuery("select f from Fruit f order by f.id");
		
		List<Fruit> fruitsFromDatabase = (List<Fruit>) queryActionbean.run();
		
		Assert.assertEquals(3, fruitsFromDatabase.size());
		
		for(int i = 0; i < FRUITS.length; i++)
		{
			Assert.assertEquals(FRUITS[i], fruitsFromDatabase.get(i));
		}
	}
	
	public EntityManager getEntityManager()
	{
		if (entityManager == null)
		{
			entityManager = createEntityManager();
		}
		
		return entityManager;
	}
	
}
