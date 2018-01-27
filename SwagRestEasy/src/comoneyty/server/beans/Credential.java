package comoneyty.server.beans;

import comoneyty.server.beans.util.ObjetId;

public class Credential extends ObjetId {
	String idClientApi;
	String login;
	String email;
	String phone;
	String password;
	String idUser;
	int nbTentative;
	@Override
	public String getNomClasse() {
		// TODO Auto-generated method stub
		return "Credential";
	}
	public Credential() {
		super();
	}

	public Credential(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getNbTentative() {
		return nbTentative;
	}
	public void setNbTentative(int nbTentative) {
		this.nbTentative = nbTentative;
	}
	public String getIdClientApi() {
		return idClientApi;
	}
	public void setIdClientApi(String idClientApi) {
		this.idClientApi = idClientApi;
	}

	
}
