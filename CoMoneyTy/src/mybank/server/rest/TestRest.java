package mybank.server.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.User;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;

@Path("/test")
public class TestRest {
	
	@POST
	@Path("/sendPhoto")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendPhoto(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			FileOutputStream targetFile = new FileOutputStream("../standalone/deployments/Image.war/event/test.jpg");
			targetFile.write(data);
			targetFile.close();
		       
			return Reponse.getResponseOK("OK");
		} catch (Exception e) {
			// Traitement de l'exception
			return Reponse.reponseKO(e);
		}

	}

}
