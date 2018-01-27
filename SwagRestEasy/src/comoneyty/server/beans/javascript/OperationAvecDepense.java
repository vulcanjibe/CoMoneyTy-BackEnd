package comoneyty.server.beans.javascript;

import comoneyty.server.beans.Depense;
import comoneyty.server.beans.Operation;

public class OperationAvecDepense implements Comparable<OperationAvecDepense> {
	Operation operation;
	Depense depense;
	String urlPhoto;
	public OperationAvecDepense(Operation operation, Depense depense) {
		super();
		this.operation = operation;
		this.depense = depense;
	}
	public OperationAvecDepense() {
		super();
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public Depense getDepense() {
		return depense;
	}
	public void setDepense(Depense depense) {
		this.depense = depense;
	}
	@Override
	public int compareTo(OperationAvecDepense o) {
		// TODO Auto-generated method stub
		return -operation.getDate().compareTo(o.operation.getDate());
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	
}
