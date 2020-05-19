package daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MyEntityManager {
	
	private static MyEntityManager instance = null;
	private EntityManager em;
	
	private MyEntityManager() {
		em = Persistence.createEntityManagerFactory("ThuchanhJPA_Ontap_OGM")
				.createEntityManager();
	}
	
	public synchronized static MyEntityManager getInstance() {
		if(instance == null)
			instance = new MyEntityManager();
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
}
