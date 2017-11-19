package mybank.server.beans;

import java.util.UUID;

public class User  implements ObjetId {
	String id;
	String nom;
	String prenom;
	String login;
	String password;
	String email;
	String urlAvatar;
	String phone;
	String codecourt;
	String nomClasse="User";
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String username) {
		this.login = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User(String nom, String prenom, String username,
			String password, String email,String avatar) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nom = nom;
		this.prenom = prenom;
		this.login = username;
		this.password = password;
		this.email = email;
		this.urlAvatar = avatar;
	}
	public User(String nom, String prenom, String username,
			String password, String email,String avatar,String phone) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nom = nom;
		this.prenom = prenom;
		this.login = username;
		this.password = password;
		this.email = email;
		this.urlAvatar = avatar;
		this.phone = phone;
	}
	public User() {
		// TODO Auto-generated constructor stub
		this.id = UUID.randomUUID().toString();
	}
	public String getUrlAvatar() {
		return urlAvatar;
	}
	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
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
