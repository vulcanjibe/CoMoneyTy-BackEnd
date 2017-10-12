package mybank.server.beans.javascript;

import mybank.server.beans.Depense;
import mybank.server.beans.Operation;

public class OperationAvecDepense implements Comparable<OperationAvecDepense> {
	Operation operation;
	Depense depense;
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
	
}
