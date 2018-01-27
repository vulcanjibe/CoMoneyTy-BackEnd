package comoneyty.server.beans.javascript;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

import comoneyty.server.beans.Message;


public class TableauMessage implements Comparable<TableauMessage> {
	public static SimpleDateFormat SDF_MOIS = new SimpleDateFormat("MMM yy");

	  String titre;
	  Date date;
	  TreeSet<Message>  tableau;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public TreeSet<Message> getTableau() {
		return tableau;
	}
	public void setTableau(TreeSet<Message> tableau) {
		this.tableau = tableau;
	}
	public TableauMessage(String titre) throws ParseException {
		super();
		this.titre = titre;
		if(titre.equalsIgnoreCase("Cette semaine"))
			this.date = new Date();
		else  
			this.date = SDF_MOIS.parse(titre);
		this.tableau = new TreeSet<>();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		TableauMessage other = (TableauMessage) obj;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
	@Override
	public int compareTo(TableauMessage o) {
		if(this.equals(o))
			return 0;
		return -date.compareTo(o.date);
	}
	  
	
}
