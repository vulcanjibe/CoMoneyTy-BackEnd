package comoneyty.server.rest.util;

public class RetourAppel {

	protected int code;
	protected String message;

	public RetourAppel(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public RetourAppel() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}