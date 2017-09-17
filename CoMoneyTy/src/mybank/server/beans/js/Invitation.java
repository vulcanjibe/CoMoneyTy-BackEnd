package mybank.server.beans.js;

import mybank.server.beans.Communaute;
import mybank.server.beans.User;

public class Invitation {
	private String email;
	private String telephone;
	private String nom;
	private String commentaire;
	private Communaute communaute;
	private User emetteur;
	public Invitation() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Communaute getCommunaute() {
		return communaute;
	}
	public void setCommunaute(Communaute communaute) {
		this.communaute = communaute;
	}
	public User getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}
	
	
}
