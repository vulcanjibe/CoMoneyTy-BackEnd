package mybank.server.rest.util;

import java.util.Date;

import mybank.server.beans.ObjetId;

public class UserStore implements ObjetId {
	String id; // C'est le TOKEN en fait
	String nomClasse="UserStore";
	String idUser;
	Date timestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public UserStore() {
		super();
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	
}
