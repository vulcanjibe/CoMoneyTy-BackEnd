package mybank.server.beans;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class Contact implements ObjetId {
	String id;
	String nomClasse="Contact";
	String displayName;
	String email;
	String phoneNumber;
	String photo;
	String idInterne;
	@JsonIgnore
	boolean dejaInvite;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Contact() {
		super();
		this.id = UUID.randomUUID().toString();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumer) {
		this.phoneNumber = phoneNumer;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIdInterne() {
		return idInterne;
	}
	public void setIdInterne(String idInterne) {
		this.idInterne = idInterne;
	}
	public boolean isDejaInvite() {
		return dejaInvite;
	}
	public void setDejaInvite(boolean dejaInvite) {
		this.dejaInvite = dejaInvite;
	}
	
}
