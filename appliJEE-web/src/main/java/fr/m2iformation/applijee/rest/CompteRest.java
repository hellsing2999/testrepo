package fr.m2iformation.applijee.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.m2iformation.applijee.entity.Compte;
import fr.m2iformation.applijee.service.IServiceCompte;

/*
 * classe java du ws rest sur les comptes bancaires
 */

@Path("service/compte")
@Produces("application/json")//en retour java convertit en json
public class CompteRest {
	
	//@EJB n'est pas interprété ici dans la techno récente JAX-RS problème de compatibilité
	@Inject // @Inject est une annotation un peu plus récente de CDI/JEE6
			// il faut rajouter le fichier beans.xml dans le repertoire webapp/WEN-INF/
			// pour que le @Inject soit pris en compte
	private IServiceCompte serviceCompte;

	@GET
	@Path("/{num}")
	// URL = http://localhost:8080/aplliJEE-web/rest/service/compte/2
	public Compte getCompteByNum(@PathParam("num") Long num) {
		
		//V1 sans lien avec EJB
		//return new Compte(num,"compte "+num,50.0);
		// V2 avec EJB
		//return serviceCompte.rechercherCompteParNumero(num);
		Compte c = serviceCompte.rechercherCompteAvecOperationsParNumero(num);
		return c;
	}
	
	@POST
	@Path("")
	// url = http://localhost:8080/appliJEE-web/rest/service/compte appelé en méthode post
	// avec {"numero":3 ou null,"label":"compte xy","solde":50.0} dans le corps de la requete
	@Consumes(MediaType.APPLICATION_JSON) // en entrée du json convertit en java
	public Compte postCompte(Compte c) {
		serviceCompte.saveOrUpdateCompte(c);
		return c;// en retour,  copie du compte sauvegardé avec clef primaire quelque fois auto_incr
	}
	
	@GET
	@Path("")
	// url= http://localhost:8080/appliJEE-web/rest/service/compte?numMax=2
	public List<Compte> getComptesByNumMax(@QueryParam("numMax") Long numMax){
		List<Compte> listeComptes = new ArrayList<>();
		if(numMax>=1) listeComptes.add(new Compte(1L,"compte 1",25.0));
		if(numMax>=2) listeComptes.add(new Compte(2L,"compte 2",78.24));
		return listeComptes;
	}
	
	
}
