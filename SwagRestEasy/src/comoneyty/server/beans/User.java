package comoneyty.server.beans;

import comoneyty.server.beans.util.ObjetId;

public class User  extends ObjetId {
	String nom;
	String prenom;
	String email;
	String urlAvatar;
	String phone;
	String codecourt;
	
	String iban;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User(String nom, String prenom,  String email,String avatar) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		
		this.email = email;
		this.urlAvatar = avatar;
	}
	public User(String nom, String prenom, String email,String avatar,String phone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	
		this.email = email;
		this.urlAvatar = avatar;
		this.phone = phone;
	}
	public User() {
		super();
		
	}
	public String getUrlAvatar() {
		return urlAvatar;
	}
	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}
	public String getNomClasse() {
		return "User";
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getCodecourt() {
		return codecourt;
	}
	public void setCodecourt(String codecourt) {
		this.codecourt = codecourt;
	}


}
