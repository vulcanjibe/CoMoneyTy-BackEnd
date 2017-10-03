package mybank.test;

import java.util.Date;

import mybank.server.beans.ObjetId;

public class ObjetTest implements ObjetId {
	String id;
	String nom;
	String nomClasse="ObjetTest";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public ObjetTest() {
		super();
	}
	public ObjetTest(String nom) {
		super();
		this.nom = nom;
	}
	

}
