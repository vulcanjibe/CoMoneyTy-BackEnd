package mybank.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message implements ObjetId {
	String id;
	String message;
	String titre;
	String messageCache;
	User emetteur;
	User destinataire;
	Date date;
	String nomClasse="Message";
	boolean dejaLu;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public boolean isDejaLu() {
		return dejaLu;
	}
	public void setDejaLu(boolean dejaLu) {
		this.dejaLu = dejaLu;
	}
	public Message() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public Message(String titre,String message, User emetteur) {
		super();
		this.id = UUID.randomUUID().toString();
		this.message = message;
		this.emetteur = emetteur;
		this.titre = titre;
		this.date=new Date();
		this.dejaLu=false;
	}
	public String getMessageCache() {
		return messageCache;
	}
	public void setMessageCache(String messageCache) {
		this.messageCache = messageCache;
	}
	public User getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(User destinataire) {
		this.destinataire = destinataire;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}	
}
