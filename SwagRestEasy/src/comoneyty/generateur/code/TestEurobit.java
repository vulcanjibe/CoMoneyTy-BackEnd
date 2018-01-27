package comoneyty.generateur.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import comoneyty.server.rest.util.Utilitaire;

public class TestEurobit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post("https://soporte.eurobits.es/lbpneo/privateWS/api/login")
					  .header("Content-Type","application/json")
					  .body("{\"password\":\"Proj-01Néo*\",\"service\":\"lbpNeoUsr\"}")
					  .asJson();
			System.err.println(jsonResponse.getBody().toString());
			jsonResponse.getBody().getObject().get("token");
			System.err.println(jsonResponse.getBody().toString());
//	        eurobits();
	        
		} catch (Exception e) {
			// Traitement de l'exception
			e.printStackTrace();
		}
	}

	public static String eurobits() throws Exception {
		URL url = new URL("https://soporte.eurobits.es/lbpneo/privateWS/api/login");
		/*
		 * user : lbpNeoUsr
			mdp : Proj-01Néo*
			ServiceID = lbpNeoUsr
		 */
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type","application/json");
		String body = "{\"password\":\"Proj-01Néo*\",\"service\":\"lbpNeoUsr\"}";
		OutputStream writer = conn.getOutputStream();
		writer.write(body.getBytes());
		writer.flush();
		writer.close();
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String output;
		String rep="";
		while ((output = br.readLine()) != null) {
		    rep+=output;
		}
		String res = "Connexion incorrect";
		if(rep.contains("token"))
		{
			String[] tab = rep.split("\"");
			res=tab[3];
		}
		else throw new Exception("Connexion incorrecte!");
		conn.disconnect();
		String TOKEN=res;
		System.out.println(rep);
		conn.disconnect();
		
		url = new URL("https://soporte.eurobits.es/lbpneo/privateWS/api/aggregation");
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type","application/json");
		conn.setRequestProperty("Authorization","Bearer "+TOKEN);
		body = "{\"robotName\":\"DummyRobot\",\"serviceId\":\"test\",\"userId\":\"lbpNeoUsr\",\"fromDate\":\"01/01/2017\",\"products\":[\"Accounts\"],\"credentials\":[{\"name\":\"user\",\"value\":\"user\"},{\"name\":\"password\",\"value\":\"pwd\"}],\"extendedTrxData\":true,\"encryptedCredentials\":false}";
		writer = conn.getOutputStream();
		writer.write(body.getBytes());
		writer.flush();
		writer.close();
		br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		 
		rep="";
		while ((output = br.readLine()) != null) {
		    rep+=output;
		}
		if(rep.contains("executionId"))
		{
			String[] tab = rep.split("\"");
			res=tab[3];
		}
		else throw new Exception("Impossible de récupérer un ID Robot!");
		String ROBOTID=res;
		
		
		url = new URL("https://soporte.eurobits.es/lbpneo/privateWS/api/aggregation/"+ROBOTID);
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type","application/json");
		conn.setRequestProperty("Authorization","Bearer "+TOKEN);
		br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		 
		rep="";
		while ((output = br.readLine()) != null) {
		    rep+=output;
		}
		System.out.println(rep);
		return rep;
	}

}
