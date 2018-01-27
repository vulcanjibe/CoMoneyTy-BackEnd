package comoneyty.server.beans.javascript;

public class SimpleCredential {
	String idClientApi;
	String login;
	String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SimpleCredential() {
		
	}
	public String getIdClientApi() {
		return idClientApi;
	}
	public void setIdClientApi(String idClientApi) {
		this.idClientApi = idClientApi;
	}
	
}
