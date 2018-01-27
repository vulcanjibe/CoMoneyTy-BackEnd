package comoneyty.server.beans;

import java.util.Date;

import comoneyty.server.beans.util.ObjetId;

public class Mouvement extends ObjetId {
	String idEmetteur;
	String idDestinataire;
	double montant;
	String commentaire;
	Date date;
	String idEvent;
	String etat;
	public String getIdEmetteur() {
		return idEmetteur;
	}
	public void setIdEmetteur(String idEmetteur) {
		this.idEmetteur = idEmetteur;
	}
	public String getIdDestinataire() {
		return idDestinataire;
	}
	public void setIdDestinataire(String idDestinataire) {
		this.idDestinataire = idDestinataire;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Mouvement(String idEmetteur, String idDestinataire, double montant,
			String commentaire, Date date,String idEvent) {
		super();
		
		this.idEmetteur = idEmetteur;
		this.idDestinataire = idDestinataire;
		this.montant = montant;
		this.commentaire = commentaire;
		this.date = date;
		this.idEvent = idEvent;
	}
	public Mouvement() {
		
		// TODO Auto-generated constructor stub
	}
	public String getNomClasse() {
		// TODO Auto-generated method stub
		return "Mouvement";
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}

	
}
