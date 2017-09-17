/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybank.server.beans;

/**
 *
 * @author pelt815
 */
public class Role implements ObjetId {

    private String libelle = "";


	private String id="";
    
    //Constructeur
    public Role(String id,String name) {
        this.libelle = name;
        this.setId(id);
    }

    public String toString() {
        return libelle;
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

	public Role() {
		super();
	}

	@Override
	public String getNomClasse() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
}
