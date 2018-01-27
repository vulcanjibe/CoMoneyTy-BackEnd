package comoneyty.server.beans;

import java.util.Date;

import comoneyty.server.beans.type.TypeOperation;
import comoneyty.server.beans.util.ObjetId;

public class Operation extends ObjetId {
	private String idUser;

	Date date;
	String description;
	double montant;
	String ibanEmetteur;
	String urlPhotoEmetteur;
	String ibanDestinataire;
	TypeOperation typeOperation;
	String urlPhotoDestinataire;
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String userId) {
		this.idUser = userId;
	}
	public String getNomClasse() {
		return "Operation";
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getIbanEmetteur() {
		return ibanEmetteur;
	}
	public void setIbanEmetteur(String ibanEmetteur) {
		this.ibanEmetteur = ibanEmetteur;
	}
	public String getIbanDestinataire() {
		return ibanDestinataire;
	}
	public void setIbanDestinataire(String ibanDestinataire) {
		this.ibanDestinataire = ibanDestinataire;
	}
	public TypeOperation getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(TypeOperation typeOperation) {
		this.typeOperation = typeOperation;
	}
	
	public Operation() {
		super();
	}
	public Operation(String userId, Date date, String description, double montant) {
		super();
		this.idUser = userId;
		this.date = date;
		this.description = description;
		this.montant = montant;
	}
	public String getUrlPhotoEmetteur() {
		return urlPhotoEmetteur;
	}
	public void setUrlPhotoEmetteur(String urlPhotoEmetteur) {
		this.urlPhotoEmetteur = urlPhotoEmetteur;
	}
	public String getUrlPhotoDestinataire() {
		return urlPhotoDestinataire;
	}
	public void setUrlPhotoDestinataire(String urlPhotoDestinataire) {
		this.urlPhotoDestinataire = urlPhotoDestinataire;
	}
	
	
}
