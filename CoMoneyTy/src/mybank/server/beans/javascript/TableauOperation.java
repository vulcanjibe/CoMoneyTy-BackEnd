package mybank.server.beans.javascript;

import java.text.ParseException;
import java.util.Date;
import java.util.TreeSet;

import mybank.server.rest.UserRest;

public class TableauOperation implements Comparable<TableauOperation> {
	  String titre;
	  Date date;
	  TreeSet<OperationAvecDepense>  tableau;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public TreeSet<OperationAvecDepense> getTableau() {
		return tableau;
	}
	public void setTableau(TreeSet<OperationAvecDepense> tableau) {
		this.tableau = tableau;
	}
	public TableauOperation(String titre) throws ParseException {
		super();
		this.titre = titre;
		if(titre.equalsIgnoreCase("Cette semaine"))
			this.date = new Date();
		else  
			this.date = UserRest.SDF_MOIS.parse(titre);
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
		TableauOperation other = (TableauOperation) obj;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
	@Override
	public int compareTo(TableauOperation o) {
		if(this.equals(o))
			return 0;
		return -date.compareTo(o.date);
	}
	  
	
}
