package mybank.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Depense  implements ObjetId {
	String id;
	String idEvent;
	String idPayeur;
	String typeRepartition;
	String commentaire;
	String idOperation;
	Date date;
	double montant;
	String urlPhoto;
	String nomClasse="Depense";
	
	
	public Depense(String libelle, double montant) {
		super();
		this.id = UUID.randomUUID().toString();
		this.commentaire = libelle;
		this.montant = montant;
	}
	public Depense() {
		super();
		this.id = UUID.randomUUID().toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public String getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(String idOperation) {
		this.idOperation = idOperation;
	}

	
}
