package mybank.server.beans;

import java.util.Date;
import java.util.UUID;

import mybank.server.beans.type.TypeOperation;

public class Operation implements ObjetId {
	String id;
	private String userId;
	String nomClasse="Operation";
	Date date;
	String description;
	double montant;
	String ibanEmetteur;
	String urlPhotoEmetteur;
	String ibanDestinataire;
	TypeOperation typeOperation;
	String urlPhotoDestinataire;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
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
		this.id = UUID.randomUUID().toString();
	}
	public Operation(String userId, Date date, String description, double montant) {
		super();
		this.id = UUID.randomUUID().toString();
		this.userId = userId;
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
