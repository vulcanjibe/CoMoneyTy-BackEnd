package comoneyty.server.beans;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import comoneyty.server.beans.util.ObjetId;

public class Event extends ObjetId {
	String libelle;
	Date date;
	double montantTotal;
	double montantDu;
	double montantDepense;
	String urlPhoto;
	String etat;
	ArrayList<String> roles;
	@JsonIgnore
	public ArrayList<User> participants=null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontantTotal() {
		return montantTotal;
	}
	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}
	public double getMontantDu() {
		return montantDu;
	}
	public void setMontantDu(double montantDu) {
		this.montantDu = montantDu;
	}
	
	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}
	public Event() {
		super();
	}
	public Event(String libelle, Date date, String photo) {
		super();
		this.libelle = libelle;
		this.date = date;
		this.urlPhoto = photo;
		this.etat = "Ouvert";
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	public String getNomClasse() {
		return "Event";
	}
	public double getMontantDepense() {
		return montantDepense;
	}
	public void setMontantDepense(double montantDepense) {
		this.montantDepense = montantDepense;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}

}
