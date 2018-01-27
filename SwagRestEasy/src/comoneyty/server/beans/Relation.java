package comoneyty.server.beans;

import comoneyty.server.beans.util.ObjetId;

public class Relation extends ObjetId {
	private String idUser1;
	private String idUser2;
	String nomClasse="Relation";
	public Relation( String user1Id, String user2Id) {
		super();
		this.idUser1 = user1Id;
		this.idUser2 = user2Id;
	}
	public Relation() {
	
	}

	public String getNomClasse() {
		return "Relation";
	}
	
	public String getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(String user1Id) {
		this.idUser1 = user1Id;
	}
	public String getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(String user2Id) {
		this.idUser2 = user2Id;
	}

}
