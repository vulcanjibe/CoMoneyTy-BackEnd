package mybank.server.rest.util;

public class Index implements Comparable<Index> {
	String chaine;
	int nombreAppel;
	public void addAppel()
	{
		nombreAppel++;
	}
	public String getChaine() {
		return chaine;
	}
	public void setChaine(String chaine) {
		this.chaine = chaine.toLowerCase();
	}
	public int getNombreAppel() {
		return nombreAppel;
	}
	public void setNombreAppel(int nombreAppel) {
		this.nombreAppel = nombreAppel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chaine == null) ? 0 : chaine.hashCode());
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
		Index other = (Index) obj;
		if (chaine == null) {
			if (other.chaine != null)
				return false;
		} else if (!chaine.equals(other.chaine))
			return false;
		return true;
	}
	@Override
	public int compareTo(Index o) {
		// TODO Auto-generated method stub
		if(this.equals(o))
			return 0;
		else 
			return chaine.compareTo(chaine);
	}
	
}
