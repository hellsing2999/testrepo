package fr.m2iformation.applijee.dao;

import java.util.List;

import fr.m2iformation.applijee.entity.Compte;


/*
 * DAO = Data Access Object avec
 * methodes CRUD
 * avec throws RuntimeException implicites
 */
public interface IDaoCompte {
	
	public Compte createCompte(Compte c);// en retour le compte avec la clé primaire auto incrementé.
	public Compte getCompteByNumero(Long numero);
	public List<Compte> getComptesDuClient(Long numClient);
	public void updateCompte(Compte c);
	public void deleteCompte(Long numero);
	public Compte getCompteWithOperationsByNumber(long l);
	

}
