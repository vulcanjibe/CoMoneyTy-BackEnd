package mybank.server.beans.javascript;

import mybank.server.beans.User;

public class UserAvecDepense {
	User user;
	double aPaye;
	double doit;
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
	
}
