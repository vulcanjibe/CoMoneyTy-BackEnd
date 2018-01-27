package comoneyty.server.beans;

import java.util.ArrayList;

import comoneyty.server.beans.util.ObjetId;

public class LienEventUser extends ObjetId {
	private String idUser;
	private String idEvent;
	ArrayList<String> roles;

	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	public LienEventUser( String userId, String eventId) {
		super();
		this.idUser = userId;
		this.idEvent = eventId;
	}
	public LienEventUser() {
		// TODO Auto-generated constructor stub
	}

	public String getNomClasse() {
		return "LienEventUser";
	}

	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String userId) {
		this.idUser = userId;
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String eventId) {
		this.idEvent = eventId;
	}

}
