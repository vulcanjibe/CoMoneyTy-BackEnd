package comoneyty.server.beans.javascript;

import comoneyty.server.beans.Event;
import comoneyty.server.beans.Mouvement;
import comoneyty.server.beans.User;

public class Ordre {
	Mouvement mouvement;
	User emetteur;
	Event event;
	String nomClasse="Ordre";
	public Mouvement getMouvement() {
		return mouvement;
	}
	public void setMouvement(Mouvement mouvement) {
		this.mouvement = mouvement;
	}
	public User getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Ordre(Mouvement mouvement, User emetteur, Event event) {
		super();
		this.mouvement = mouvement;
		this.emetteur = emetteur;
		this.event = event;
	}
	public Ordre() {
		super();
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	
}
