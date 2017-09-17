/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybank.server.beans;


import java.util.List;

/**
 *
 * @author pelt815
 */
public class TypeObjet implements Comparable<TypeObjet> {
    String id;
    String libelle;
    

    public TypeObjet() {
       
    }
    public TypeObjet(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
  
    public static TypeObjet getTypeObjet(String nom,String id) {
		
		return CacheType.getTypeObjet(nom, id);
	}
    public static TypeObjet getTypeObjet(String nom) {
		List<TypeObjet> liste = CacheType.getTHE_CACHE(nom);
		return liste.get(0);
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int compareTo(TypeObjet o) {
        return id.compareTo(o.getId());
    }
    
    public boolean equals(Object o)
    {
    	if(o instanceof TypeObjet)
    	{
    		return this.compareTo((TypeObjet) o)==0;
    	}
    	return false;
    	
    }
}
