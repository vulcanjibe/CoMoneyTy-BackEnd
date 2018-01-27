package mybank.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class Document implements ObjetId {
	String id;
	String nomClasse="Document";
	String type;
	String description;
	String url;
	Date date;
	String idOperation;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	
	public Document() {
		super();
		this.id = UUID.randomUUID().toString();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(String idOperation) {
		this.idOperation = idOperation;
	}

	
}
