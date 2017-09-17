package mybank.server.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import mybank.server.rest.util.Accesseur;

public class CacheType implements ObjetId {
	
	String nomClasse = CacheType.class.getName();
	
	String id;
	
	String nomEntite;
	ArrayList<TypeObjet> listeValeur = new ArrayList<TypeObjet>();

	static HashMap<String, ArrayList<TypeObjet>> THE_CACHE = null;

	public ArrayList<TypeObjet> getListeValeur() {
		return listeValeur;
	}

	public void setListeValeur(ArrayList<TypeObjet> listeValeur) {
		this.listeValeur = listeValeur;
	}

	public CacheType(String nomEntite) {
		super();
		this.nomEntite = nomEntite;
	}

	public CacheType() {
		super();
	}

	public String getNomEntite() {
		return nomEntite;
	}

	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public static void reinit() {
		THE_CACHE = null;
	}

	public static ArrayList<TypeObjet> getTHE_CACHE(String nom) {
		if (THE_CACHE == null) {
			THE_CACHE = new HashMap<String, ArrayList<TypeObjet>>();
			chargeListe();
		}
		return THE_CACHE.get(nom);
	}

	public static void chargeListe() {
		List<CacheType> liste;
		try {
			liste = (List<CacheType>) Accesseur.getListe(CacheType.class);

			for (CacheType cache : liste) {
				THE_CACHE.put(cache.getNomEntite(), cache.getListeValeur());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static TypeObjet getPremiereInstance(String nom) {
		return getTHE_CACHE(nom).get(0);
	}

	public static TypeObjet getTypeObjet(String nom, String id) {
		ArrayList<TypeObjet> liste = getTHE_CACHE(nom);
		for (TypeObjet type : liste)
			if (type.getId().equals(id))
				return type;
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static HashMap<String, ArrayList<TypeObjet>> getTHE_CACHE() {
		return THE_CACHE;
	}

}
