package mybank.server.beans;

import java.util.ArrayList;
import java.util.UUID;

public class LienEventUser implements ObjetId{
	String id;
	private String userId;
	private String eventId;
	ArrayList<String> roles;
	String nomClasse="LienEventUser";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	public LienEventUser( String userId, String eventId) {
		super();
		this.id = UUID.randomUUID().toString();
		this.userId = userId;
		this.eventId = eventId;
	}
	public LienEventUser() {
		// TODO Auto-generated constructor stub
	}

	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
