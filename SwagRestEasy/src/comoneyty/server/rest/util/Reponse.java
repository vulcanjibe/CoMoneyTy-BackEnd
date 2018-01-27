/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comoneyty.server.rest.util;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import comoneyty.server.rest.util.json.DoubleSerializer;

/**
 *
 * @author pelt815
 */
public class Reponse {

	public static Response reponseKO(Exception e) {
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

	public static Response getResponseJsonOK(String json) throws JsonProcessingException {
		return Response.status(200).entity(json).build();
	}

	public static Response getResponseOK(String message) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(Utilitaire.FORMAT_DATE_COURT);
		RetourAppel monErreur = new RetourAppel(200, message);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(monErreur);
		return Response.status(200).entity(json).build();

	}

	public static String getJson(Object data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(double.class, new DoubleSerializer());
		mapper.registerModule(module);
		mapper.setDateFormat(Utilitaire.FORMAT_DATE_COURT);
		String json = mapper.writeValueAsString(data);
		return json;
	}

	public static Response getResponseOK(Object data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(double.class, new DoubleSerializer());
		mapper.registerModule(module);
		mapper.setDateFormat(Utilitaire.FORMAT_DATE_COURT);
		String json = mapper.writeValueAsString(data);
		return Response.status(200).entity(json).build();

	}
}
