package mybank.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Event implements ObjetId {
	String id;
	String libelle;
	Date date;
	double montantTotal;
	double montantDu;
	double montantDepense;
	String urlPhoto;
	String etat;
	@JsonIgnore
	ArrayList<User> participants=null;
	String nomClasse="Event";
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
	public ArrayList<User> getParticipants() throws Exception {
		
		// Implémenter
		if(participants==null)
		{
			throw new Exception("A IMPLEMENTER");
		}
		return participants;
	}
	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}
	public Event() {
		super();
		this.id = UUID.randomUUID().toString();
	}
	public Event(String libelle, Date date, String photo) {
		super();
		this.id = UUID.randomUUID().toString();
		this.libelle = libelle;
		this.date = date;
		this.urlPhoto = photo;
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

}
