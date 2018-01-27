package comoneyty.server.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import comoneyty.server.beans.util.ObjetId;



public class Contact extends ObjetId {
	String displayName;
	String email;
	String phoneNumber;
	String photo;
	String idInterne;
	@JsonIgnore
	boolean dejaInvite;
	public String getNomClasse() {
		return "Contact";
	}

	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Contact() {
		super();
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
