package fr.m2iformation.applijee.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

import fr.m2iformation.applijee.dao.IDaoCompte;
import fr.m2iformation.applijee.entity.Compte;


/*
 * NB : jboss doit être paramétré avec l'option -b 0.0.0.0 
 * pour accepter des requêtes provenant d'autres 
 */

@Stateless
@Local
//@TransactionManagement(TransactionManagementType.CONTAINER) par défaut sur EJB
//@TransactionAttribute(TransactionAttributeType.REQUIRED) par défaut sur EJB
@WebService(endpointInterface="fr.m2iformation.applijee.service.IServiceCompte")
//@TransactionManagement(TransactionManagementType.BEAN)
public class ServiceCompteImpl implements IServiceCompte {
	
	@EJB //@EJB permet de demander une initialisation de daoCompte 
		//en demandant à ce que ça référence un ejb existant compatible avec l'interface IDaoCompte.
		// c'est une sorte d'injection de dépendance ( équivalent à @Autowired de spring ou bien @Inject de CDI
	private IDaoCompte daoCompte; 

	
	
	@Override
	public Compte rechercherCompteParNumero(long numero) {
		// on délègue au dao.
		return daoCompte.getCompteByNumero(numero);
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		//en début de méthode, création automatique entityManager et Transaction
		
		Compte cptDeb = daoCompte.getCompteByNumero(numCptDeb);
		cptDeb.setSolde(cptDeb.getSolde()-montant);
		daoCompte.updateCompte(cptDeb);
		
		Compte cptCred = daoCompte.getCompteByNumero(numCptCred);
		cptCred.setSolde(cptCred.getSolde()+montant);
		daoCompte.updateCompte(cptCred);
		
		//en fin de méthode, commit automatique si aucune exception
		//ou rollback automatique si exception remontée

	}

	@Override
	public void saveOrUpdateCompte(Compte c) {
		if(c.getNumero()==null) {
			daoCompte.createCompte(c); // insert compte via .persist()
		}else {
			daoCompte.updateCompte(c);// update compte via .merge()
		}
		
	}

	@Override
	public void ajouterCompte(Compte c ){
		daoCompte.createCompte(c);
		
	}

	@Override
	public void mettreAjourCompte(Compte c) {
		daoCompte.updateCompte(c);
		
	}

	@Override
	public void supprimerCompte(Long numCpt) {
		daoCompte.deleteCompte(numCpt);
		
	}

	@Override
	public Compte rechercherCompteAvecOperationsParNumero(long l) {
		/*
		//solution 1 pour le mode lazy
			Compte cpt = this.daoCompte.getCompteByNumero(l);
			fetchLazyCollection(cpt.getOperations());
			return cpt;
		//seulement en cette  fin de méthode transactionelle sur EJB
		//la transaction et le entityManager sont fermés
		 * 
		 * */
		
		//Solution 2 pour le mode lazy
		// appeler sur le dao une méthode de recherche avec SELECT ... JOIN FETCH..
		return daoCompte.getCompteWithOperationsByNumber(l);
		
	}
	
	private static void fetchLazyCollection(List<? extends Object> liste) {
		for(Object obj : liste) {
			//rien (simple parcours de la collection lazy)
			//pour forcer les mécanismes internes de jpa/hibernate
			//à remonter tout de suite les valeurs de la base de données
			//vers la mémoire AVANT QU'IL NE SOIT TROP TARD (coté web ou presentation ...)
		}
	}

	@Override
	public List<Compte> rechercherCompteDeClient(long numClient) {
		
		return daoCompte.getComptesDuClient(numClient);
	}

}
