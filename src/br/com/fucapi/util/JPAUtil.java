package br.com.fucapi.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("locadora");
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
}
