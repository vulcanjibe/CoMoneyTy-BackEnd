package comoneyty.server.rest.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DefautExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		// TODO Auto-generated method stub
		String message = e.getMessage();

		if (message == null) {
			message = "Erreur technique du serveur REST : null sur " + e.getStackTrace()[0].getClassName().toString()
					+ " (Ligne:" + e.getStackTrace()[0].getLineNumber() + ")";
		}

		RetourAppel monErreur = new RetourAppel(500, message);

		ObjectMapper mapper = new ObjectMapper();
		String res = "";
		try {
			res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(monErreur);
		} catch (Exception ex) {
			res = "{ code:500,message:'Probl�me technique'}";
		}
		return Response.status(500).entity(res).build();
	}

}
