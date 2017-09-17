package mybank.server.beans;



import java.util.ArrayList;
import java.util.List;




public class Communaute implements ObjetId {

	
	String nomClasse=Communaute.class.getName();
   
    private TypeObjet typecommunaute=CacheType.getPremiereInstance("TypeCommunaute");
  
    private String nom;
  
    private String photo;
   
    private boolean visibilitePublique;
   
    private int nbMembre;
   
    private boolean isGestionnaire; 
	
	String id;
  
    public Communaute() {
        
    }
	public String getNomClasse() {
		return nomClasse;
	}
 

    public TypeObjet getTypecommunaute() {
        return typecommunaute;
    }

    public void setTypecommunaute(TypeObjet typecommunaute) {
        this.typecommunaute = typecommunaute;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }
 
    public void setPhoto(String photo) {
        this.photo = photo;
    }

  
    
   public boolean isVisibilitePublique() {
        return visibilitePublique;
    }

    public void setVisibilitePublique(boolean visibilitePublique) {
        this.visibilitePublique = visibilitePublique;
    }

	public boolean isGestionnaire() {
		return isGestionnaire;
	}

	public void setGestionnaire(boolean isGestionnaire) {
		this.isGestionnaire = isGestionnaire;
	}

	public int getNbMembre() {
		return nbMembre;
	}

	public void setNbMembre(int nbMembre) {
		this.nbMembre = nbMembre;
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public void setId(String id) {
		this.id=id;
		
	}
}
