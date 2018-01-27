package comoneyty.generateur.code;

import java.util.ArrayList;

public class Bean implements Comparable<Bean> {
	String nom;
	ArrayList<String> dependances = new ArrayList();
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<String> getDependances() {
		return dependances;
	}
	public void setDependances(ArrayList<String> dependances) {
		this.dependances = dependances;
	}
	
	@Override
	public int compareTo(Bean obj) {
		if (this == obj)
			return 0;
		if (obj == null)
			return -1;
		if (getClass() != obj.getClass())
			return -1;
		Bean other = (Bean) obj;
		if (nom == null) {
			if (other.nom != null)
				return -1;
		} else if (!nom.equals(other.nom))
			return nom.compareTo(other.nom);
		return 0;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Bean other = (Bean) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
}
