package mybank.server.beans.javascript;

import mybank.server.beans.LienEventUser;

public class CommandeAddDelParticipant {
	String commande;
	LienEventUser lienEventUser;
	public String getCommande() {
		return commande;
	}
	public void setCommande(String commande) {
		this.commande = commande;
	}
	public LienEventUser getLienEventUser() {
		return lienEventUser;
	}
	public void setLienEventUser(LienEventUser lienEventUser) {
		this.lienEventUser = lienEventUser;
	}
	public CommandeAddDelParticipant() {
		super();
	}
}
