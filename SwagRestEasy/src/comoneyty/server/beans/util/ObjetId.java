package comoneyty.server.beans.util;

import java.util.UUID;

public abstract  class ObjetId implements Comparable<ObjetId> {

	protected String id=null;
	String nomClasse;
	
	public ObjetId() {
		id=UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ObjetId other = (ObjetId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ObjetId obj) {
		if (this == obj)
			return 0;
		if (obj == null)
			return -1;
		if (getClass() != obj.getClass())
			return -1;
		ObjetId other = (ObjetId) obj;
		if (id == null) {
			if (other.id != null)
				return -1;
		} else if (!id.equals(other.id))
			return id.compareTo(obj.id);
		return 0;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}


}
