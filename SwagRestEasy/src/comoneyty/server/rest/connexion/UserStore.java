package comoneyty.server.rest.connexion;

import java.util.Date;

import comoneyty.server.beans.util.ObjetId;

public class UserStore extends ObjetId {
	String id; // C'est le TOKEN en fait
	String idUser;
	String ipAppelante;
	Date timestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomClasse() {
		return "UserStore";
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
	public String getIpAppelante() {
		return ipAppelante;
	}
	public void setIpAppelante(String ipAppelante) {
		this.ipAppelante = ipAppelante;
	}

	
	
}
