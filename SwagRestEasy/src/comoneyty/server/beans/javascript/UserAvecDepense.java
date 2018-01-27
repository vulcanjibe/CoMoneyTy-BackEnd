package comoneyty.server.beans.javascript;

import java.util.ArrayList;

import comoneyty.server.beans.User;

public class UserAvecDepense {
	User user;
	double aPaye;
	double doit;
	ArrayList<String> roles;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getaPaye() {
		return aPaye;
	}
	public void setaPaye(double aPaye) {
		this.aPaye = aPaye;
	}
	public double getDoit() {
		return doit;
	}
	public void setDoit(double doit) {
		this.doit = doit;
	}
	public UserAvecDepense(User user) {
		super();
		this.user = user;
	}
	
	public void paye(double montant) {
		aPaye+=montant;
		doit-=montant;
	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	
}
