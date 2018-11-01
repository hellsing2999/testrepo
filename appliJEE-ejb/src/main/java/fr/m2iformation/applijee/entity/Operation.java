package fr.m2iformation.applijee.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Operation implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numOp;
	
	private String label;
	
	private Double montant;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")// ou "dd-MM-yyyy hh:mm:ss"
	@Temporal(TemporalType.DATE)
	private Date dateOp;
	
	@JsonIgnore//de  la technologie jackson (utilisé en interne par jax-rs pour transformer java en json
	//@jsonignore signifie ne pas suivre le lien vers le suos objet compte quand un objet java sera transformé en json
	//cela permet d'éviter des boucles infinies ou des LazyException
	@ManyToOne
	@JoinColumn(name="id_compte")
	private Compte compte;

	public Operation() {
		super();
		
	}

	public Long getNumOp() {
		return numOp;
	}

	public void setNumOp(Long numOp) {
		this.numOp = numOp;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date date) {
		this.dateOp = date;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", date=" + dateOp
				+ ", compte=" + compte + "]";
	}
	
	

}
