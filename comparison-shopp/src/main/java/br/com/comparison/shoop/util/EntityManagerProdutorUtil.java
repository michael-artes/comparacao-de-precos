package br.com.comparison.shoop.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProdutorUtil {
	
	private EntityManagerFactory factory;
	public static final String PERSISTENCE_NAME_PRODUCTION = "PU_POSTGRES";
	
	
	public EntityManagerProdutorUtil() {
		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME_PRODUCTION);
	}

	@Produces
	@RequestScoped
	public EntityManager criandoEntityManager(){
		return factory.createEntityManager();
	}
	
	public void fechandoEntityManager(@Disposes EntityManager manager){
		manager.close();
	}

}
