package org.eyequery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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
	
	@Deployment
	public static WebArchive createDeployment()
	{
		return ShrinkWrap.create(WebArchive.class, "eyequery-test.war")
				.addPackage(QueryActionBean.class.getPackage())
				.addManifestResource("persistence-test.xml", "persistence.xml")
				.addWebResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	public static EntityManager createEntityManager()
	{
		return Persistence.createEntityManagerFactory("eyeQueryPU").createEntityManager();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testRun_SelectAllFruits()
	{
		List<Fruit> fruitsFromDatabase = getEntityManager().createQuery("select f from Fruit f order by f.id").getResultList();
		
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
