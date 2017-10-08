package mybank.server.beans;

import java.util.Date;
import java.util.UUID;

public class Invitation implements ObjetId {
	String id;
	String idUser;
	Date date;
	String etatReponse;
	Contact contact;
	String nomClasse="Invitation";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEtatReponse() {
		return etatReponse;
	}
	public void setEtatReponse(String etatReponse) {
		this.etatReponse = etatReponse;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public Invitation(Date date, String etatReponse) {
		super();
		this.id = UUID.randomUUID().toString();
		this.date = date;
		this.etatReponse = etatReponse;
	}
	public Invitation() {
		super();
		this.id = UUID.randomUUID().toString();
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
