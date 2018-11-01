package fr.m2iformation.applijee.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import fr.m2iformation.applijee.entity.Compte;

/*
 * Service métier codé sous forme d'ejb
 * responsabilité du service:
 * 		- transaction (commit/rollback)
 * 		- regle de gestion (ex : verifier possibilte de découvert )
 * 		- traitement spécifique au métier ( banque, assurance, ... )
 */

@WebService // pour permettre appel web service SOAP
// @webParam pour que les nom de parametre soient corrects dans la description wsdl générée
public interface IServiceCompte {
	
	
	public Compte rechercherCompteParNumero(@WebParam(name="numero") long numero);
	public void transferer( @WebParam(name="montant") double montant,
							@WebParam(name="numCptDeb") long numCptDeb,
							@WebParam(name="numCptCred") long numCptCred);
	public void saveOrUpdateCompte(Compte c);
	public void ajouterCompte(Compte c);
	public void mettreAjourCompte(Compte c);
	public void supprimerCompte(Long numCpt);
	public Compte rechercherCompteAvecOperationsParNumero(long l);
	
	public List<Compte> rechercherCompteDeClient(long numClient);

}
