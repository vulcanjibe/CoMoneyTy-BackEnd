package comoneyty.server.beans;

import java.util.Date;

import comoneyty.server.beans.util.ObjetId;

public class Invitation extends ObjetId {
	String idUser;
	Date date;
	String etatReponse;
	Contact contact;

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
		return "Invitation";
	}

	public Invitation(Date date, String etatReponse) {
		super();
		this.date = date;
		this.etatReponse = etatReponse;
	}
	public Invitation() {
		super();
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
