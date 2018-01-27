package comoneyty.server.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import comoneyty.server.beans.Contact;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/eurobits")
public class EurobitsRest {
	static String TOKEN = "";
	static String JETON = "";

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags = "Eurobits", summary = "Connexion aux API Eurobits", description = "Permet de récupérer le token et le robot", responses = {
			@ApiResponse(responseCode = "200", description = "Le Token et l'id du robot", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class))),
			@ApiResponse(responseCode = "500", description = "Une erreur est survenue", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetourAppel.class))) })
	public String login() throws Exception {
		ConnexionUser connexionUser = null;
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post("https://soporte.eurobits.es/lbpneo/privateWS/api/login")
					  .header("Content-Type","application/json")
					  .body("{\"password\":\"Proj-01Néo*\",\"service\":\"lbpNeoUsr\"}")
					  .asJson();
			TOKEN = jsonResponse.getBody().getObject().get("token").toString();
			if(TOKEN==null)
				throw new Exception("Connexion impossible à Eurobits");
			
			String body = "{\"robotName\":\"DummyRobot\",\"serviceId\":\"test\",\"userId\":\"lbpNeoUsr\",\"fromDate\":\"01/01/2017\",\"products\":[\"Accounts\"],\"credentials\":[{\"name\":\"user\",\"value\":\"user\"},{\"name\":\"password\",\"value\":\"pwd\"}],\"extendedTrxData\":true,\"encryptedCredentials\":false}";
			HttpResponse<JsonNode> jsonResponse1 = Unirest.post("https://soporte.eurobits.es/lbpneo/privateWS/api/aggregation")
					  .header("Content-Type","application/json")
					  .header("Authorization", "Bearer "+TOKEN)
					  .body(body)
					  .asJson();
			JETON = jsonResponse1.getBody().getObject().get("executionId").toString();
				
			return "{'TOKEN':'" + TOKEN + "' ; 'JETON':'" + JETON + "'}";

		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "login", "", connexionUser);
			throw e;
		}
	}

	@GET
	@Path("/compte")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags = "Eurobits", summary = "Recupération des données d'un compte", description = "Permet de récupérer toutes les données d'un compte", responses = {
			@ApiResponse(responseCode = "200", description = "Les données du compte", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class))),
			@ApiResponse(responseCode = "500", description = "Une erreur est survenue", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetourAppel.class))) })
	public String recuperationDonneesCompte() throws Exception {
		ConnexionUser connexionUser = null;
		try {
			URL url = new URL("https://soporte.eurobits.es/lbpneo/privateWS/api/aggregation/" + JETON);
			HttpResponse<JsonNode> jsonResponse1 = Unirest.get("https://soporte.eurobits.es/lbpneo/privateWS/api/aggregation/" + JETON)
					  .header("Content-Type","application/json")
					  .header("Authorization", "Bearer "+TOKEN)
					  .asJson();
			String rep = jsonResponse1.getBody().toString();
			return rep;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "jeton", "", connexionUser);
			throw e;
		}
	}
}
