package mybank.server.beans.js;

import mybank.server.beans.User;



public class ContactRetenu {
	User contact;
	double montant;
	public User getContact() {
		return contact;
	}
	public void setContact(User contact) {
		this.contact = contact;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
}