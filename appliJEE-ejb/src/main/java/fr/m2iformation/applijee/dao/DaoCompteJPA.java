package fr.m2iformation.applijee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/*
 * implementation du dao s'appuyant sur la technologie
 * JPA = Java persitance api (entity manager)
 */


import fr.m2iformation.applijee.entity.Compte;


@Stateless //EJB Session sans état ( ejb de traitement )
@Local // accès local possible ( depuis autre EJB ou partie web )
//@TransactionManagement(TransactionManagementType.CONTAINER) par défaut sur EJB , transactions automatiques
//@TransactionManagement(TransactionManagementType.BEAN) // transaction à coder nous même
public class DaoCompteJPA implements IDaoCompte {
	
	@PersistenceContext(unitName="appEJB") // initialise entityManager via META-INF/persistance.xml
	private EntityManager entityManager; // objet principal de la techno JPA

	/* Construction de entityManager 
	@PostConstruct
	public void init() {
		
		Map<String, String> properties = new HashMap<String,String>();
		// le provider jpa ici hibernate correspond à la technologie d'implémentation (code concret)
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "root");
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/compteDB?serverTimezone=UTC");
		//le fichier META-INF/persistence.xml est pris en compte
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("appEJB",properties);
		this.entityManager = emf.createEntityManager();
	}*/
	
	
	@Override
	public Compte createCompte(Compte c) {
		//en entrée: c nouveau ( avec numéro inconnu a null )
		entityManager.persist(c); // auto_incr en base et .numero qui n'est plus null
		return c;// on retourne le compte avec numéro non null
	}

	@Override
	public Compte getCompteByNumero(Long numero) {
		return entityManager.find(Compte.class, numero);
	}

	@Override
	public List<Compte> getComptesDuClient(Long numClient) {
		
		return entityManager.createQuery(
				"select cpt from Client cli join cli.comptes cpt where cli.numero = :numCli",Compte.class)
				.setParameter("numCli", numClient)
				.getResultList();
	}
	
	/* update avec Transaction explicite
	@Override
	public void updateCompte(Compte c) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(c);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			entityManager.getTransaction().rollback();
		}
		
	}*/

	@Override
	public void updateCompte(Compte c) {	
			entityManager.merge(c);	
	}
	// test git merge
	@Override
	public void deleteCompte(Long numero) {
		Compte c = entityManager.find(Compte.class, numero);
		entityManager.remove(c);
	}

	@Override
	public Compte getCompteWithOperationsByNumber(long l) {
		/*
		return entityManager
				.createQuery("SELECT c FROM Compte c JOIN FETCH c.operations WHERE c.numero = :num  ", Compte.class)
				.setParameter("num", l)
				.getSingleResult();
		*/
		
		return entityManager.createNamedQuery("Compte.findWithOperations",Compte.class)
				.setParameter("num", l)
				.getSingleResult();
		
	}

}
