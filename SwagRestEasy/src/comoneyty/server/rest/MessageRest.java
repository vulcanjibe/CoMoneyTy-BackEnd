package comoneyty.server.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import comoneyty.server.beans.Message;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/message")
public class MessageRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Message",summary="Lecture d'un message via son id",
	description="Permet de récuperer un message à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le message demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Message.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Message getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id message", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'message en base
			Message aMessage = (Message) new Accesseur<Message>().get(Message.class, strid);
			if (aMessage == null)
				throw new Exception("Message inconnu");
			return aMessage;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Message",summary="Sauvegarde d'un nouvel message",
	description="Permet de sauvegarder un message. En retour, l'opération renvoie l'message avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le message sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Message.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Message save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'message à sauvegarder (Sans id renseigné)", required = true) Message message)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(message.getId()!=null)
				throw new Exception("Impossible de créer un message qui a déjà  un ID!");
			
			// Sauvegarde de l'message en base
			new Accesseur<Message>().save(message);
			
			Utilitaire.loggingRest(this.getClass(), "save", message, connexionUser);
			return message;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création message", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Message",summary="Sauvegarde d'un message déjà enregistré",
	description="Permet de sauvegarder les modifications sur un message déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'message à enregistrer (avec id renseigné)", required = true) Message message)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(message.getId()==null)
				throw new Exception("Impossible d'updater un message sans ID!");
			
			// Update de l'message en base
			new Accesseur<Message>().update(message);
			Utilitaire.loggingRest(this.getClass(), "update", message, connexionUser);
			return new RetourAppel(0, "Le message "+message.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update message", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Message",summary="Suppression d'un message déjà enregistré",
	description="Permet de supprimer un message déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'message à supprimer (avec id renseigné)", required = true) Message message)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(message.getId()==null)
				throw new Exception("Impossible de supprimer un message sans ID!");
			
			// Suppression de l'message en base
			new Accesseur<Message>().delete(message);
			return new RetourAppel(0, "L'message "+message.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression message", connexionUser);
			throw e;
		}
	}

}
