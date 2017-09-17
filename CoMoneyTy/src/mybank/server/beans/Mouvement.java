package mybank.server.beans;

import java.util.Date;
import java.util.UUID;

public class Mouvement implements ObjetId {
	String id;
	User emetteur;
	User destinataire;
	double montant;
	String commentaire;
	Date date;
	Event event;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}
	public User getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(User destinataire) {
		this.destinataire = destinataire;
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
	public Mouvement(User emetteur, User destinataire, double montant,
			String commentaire, Date date,Event event) {
		super();
		this.id = UUID.randomUUID().toString();
		this.emetteur = emetteur;
		this.destinataire = destinataire;
		this.montant = montant;
		this.commentaire = commentaire;
		this.date = date;
		this.event = event;
	}
	public Mouvement() {
		this.id = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}
	public String getNomClasse() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
