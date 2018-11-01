package fr.m2iformation.applijee.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // entité de données pérsistante en base ( alias EJB Entité )
@NamedQueries({
	@NamedQuery(name="Compte.findWithOperations",query="select c from Compte c left join fetch c.operations where c.numero = :num")
	})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("CompteCourant") // valeur par défaut de type_compte pour les instance de cette classe
public class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id // identifiant( clé primaire )
	@GeneratedValue(strategy=GenerationType.IDENTITY) // pour auto_incr en base et valeur qui remonte dans l'objet java
	private Long numero;
	
	@Column(length=32) // varchar(32) dans la table générée
	private String label;
	
	private Double solde;
	
	@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)
	private List<Operation> operations ;
	
	@ManyToMany(mappedBy="comptes",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Client> clients;

	public Compte(Long numero, String label, Double solde) {
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Compte() {
		super();
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

}
