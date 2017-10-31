package mybank.server.beans;

import java.text.ParseException;
import java.util.Date;

import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Utilitaire;

public class HistoriqueEvent implements ObjetId,Comparable<HistoriqueEvent> {
	String id;
	String nomClasse= "HistoriqueEvent";
	String idEvent;
	User user;
	String timestamp;
	String action;
	Object objet;
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
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Object getObjet() {
		return objet;
	}
	public void setObjet(Object objet) {
		this.objet = objet;
	}
	public HistoriqueEvent(ConnexionUser user) {
		super();
		this.user = user.getUser();
		this.timestamp = Utilitaire.FORMAT_DATE_HEURE.format(new Date());
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public static void historise(ConnexionUser connexionUser,Object o,String text,String idEvent) {
		 HistoriqueEvent aHisto = new HistoriqueEvent(connexionUser);
         aHisto.setObjet(o);
         aHisto.setAction(text);
         aHisto.setIdEvent(idEvent);
         try {
			AccesseurGenerique.getInstance().save(aHisto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HistoriqueEvent() {
		super();
	}
	@Override
	public int compareTo(HistoriqueEvent o) {
		if(id.equals(o.id))
			return 0;
		int delta=-1;
		try {
			delta = Utilitaire.FORMAT_DATE_HEURE.parse(timestamp).compareTo(Utilitaire.FORMAT_DATE_HEURE.parse(o.timestamp));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return delta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		HistoriqueEvent other = (HistoriqueEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
}
