package mybank.server.beans;

import java.util.UUID;

public class Relation implements ObjetId{
	String id;
	private String user1Id;
	private String user2Id;
	String nomClasse="Relation";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Relation( String user1Id, String user2Id) {
		super();
		this.id = UUID.randomUUID().toString();
		this.user1Id = user1Id;
		this.user2Id = user2Id;
	}
	public Relation() {
		// TODO Auto-generated constructor stub
	}

	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public String getUser1Id() {
		return user1Id;
	}
	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
	}
	public String getUser2Id() {
		return user2Id;
	}
	public void setUser2Id(String user2Id) {
		this.user2Id = user2Id;
	}

}
