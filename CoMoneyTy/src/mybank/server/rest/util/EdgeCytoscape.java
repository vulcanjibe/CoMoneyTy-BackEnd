package mybank.server.rest.util;

import mybank.server.beans.TypeObjet;

public class EdgeCytoscape implements Comparable<EdgeCytoscape> {
	public EdgeCytoscape(String source, String target,int montant,TypeObjet type) {
		super();
		this.source = source;
		this.target = target;
		this.montant = montant;
		this.typeMouvement = type;
	}
	String source;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result
				+ ((typeMouvement == null) ? 0 : typeMouvement.hashCode());
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
		EdgeCytoscape other = (EdgeCytoscape) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		if (typeMouvement == null) {
			if (other.typeMouvement != null)
				return false;
		} else if (typeMouvement.getId()!=other.typeMouvement.getId())
			return false;
		return true;
	}
	String target;
	int montant;
	int widthMontant=1;
	String color="";
        String typeLigne="";

    public String getTypeLigne() {
        return typeLigne;
    }

    public void setTypeLigne(String typeLigne) {
        this.typeLigne = typeLigne;
    }
        
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	TypeObjet typeMouvement;
	public TypeObjet getTypeMouvement() {
		return typeMouvement;
	}
	public void setTypeMouvement(TypeObjet typeMouvement) {
		this.typeMouvement = typeMouvement;
	}
	public int getWidthMontant() {
		return widthMontant;
	}
	public void setWidthMontant(int widthMontant) {
		this.widthMontant = widthMontant;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public int compareTo(EdgeCytoscape o) {
		if(this.equals(o))
			return 0;
		else return (source+"-"+target).compareTo(o.source+"-"+o.target);
	}
}
