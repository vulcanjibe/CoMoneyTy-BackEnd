package comoneyty.server.beans;

import java.util.Date;
import java.util.UUID;

import comoneyty.server.beans.util.ObjetId;

public class Message extends ObjetId implements Comparable<ObjetId> {
	String message;
	String titre;
	String messageCache;
	User emetteur;
	User destinataire;
	Date date;
	String nomClasse="Message";
	boolean dejaLu=false;
	boolean actionRealise=false;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(User emetteur) {
		this.emetteur = emetteur;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public boolean isDejaLu() {
		return dejaLu;
	}
	public void setDejaLu(boolean dejaLu) {
		this.dejaLu = dejaLu;
	}
	public Message() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public Message(String titre,String message, User emetteur) {
		super();
		this.id = UUID.randomUUID().toString();
		this.message = message;
		this.emetteur = emetteur;
		this.titre = titre;
		this.date=new Date();
		this.dejaLu=false;
	}
	public String getMessageCache() {
		return messageCache;
	}
	public void setMessageCache(String messageCache) {
		this.messageCache = messageCache;
	}
	public User getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(User destinataire) {
		this.destinataire = destinataire;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public boolean isActionRealise() {
		return actionRealise;
	}
	public void setActionRealise(boolean actionRealise) {
		this.actionRealise = actionRealise;
	}
	@Override
	public int compareTo(ObjetId obj) {
		if(!(obj instanceof Message))
			return -1;
		Message other = (Message)obj;
		if (date == null) {
			if (other.date != null)
				return 1;
		} else if (!date.equals(other.date))
			return date.compareTo(other.date);
		if (id == null) {
			if (other.id != null)
				return 1;
		} else if (!id.equals(other.id))
			return id.compareTo(other.id);
		return 0;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
