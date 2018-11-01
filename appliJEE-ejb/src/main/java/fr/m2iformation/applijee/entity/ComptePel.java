package fr.m2iformation.applijee.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue("ComptePel") // valeur de type_compte pour les instances de cette classe
public class ComptePel extends Compte {
	
	@Column(name="taux")
	private Double tauxInteret ;

	public Double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(Double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	@Override
	public String toString() {
		return "ComptePel [tauxInteret=" + tauxInteret + ", toString()=" + super.toString() + "]";
	}

	
}
