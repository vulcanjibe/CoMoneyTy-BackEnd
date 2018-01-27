package comoneyty.server.beans;

import java.util.Date;

import comoneyty.server.beans.util.ObjetId;

public class Depense  extends ObjetId {
	String idEvent;
	String idPayeur;
	String typeRepartition;
	String commentaire;
	String idOperation;
	Date date;
	double montant;
	String urlPhoto;
	
	
	
	public Depense(String libelle, double montant) {
		super();
		this.commentaire = libelle;
		this.montant = montant;
	}
	public Depense() {
		super();
		
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	public String getIdPayeur() {
		return idPayeur;
	}
	public void setIdPayeur(String idPayeur) {
		this.idPayeur = idPayeur;
	}
	public String getTypeRepartition() {
		return typeRepartition;
	}
	public void setTypeRepartition(String typeRepartition) {
		this.typeRepartition = typeRepartition;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String libelle) {
		this.commentaire = libelle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	public String getNomClasse() {
		return "Depense";
	}
	
	public String getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(String idOperation) {
		this.idOperation = idOperation;
	}

	
}
