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

import comoneyty.server.beans.Invitation;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/invitation")
public class InvitationRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Invitation",summary="Lecture d'un invitation via son id",
	description="Permet de récuperer un invitation à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le invitation demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Invitation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Invitation getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id invitation", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'invitation en base
			Invitation aInvitation = (Invitation) new Accesseur<Invitation>().get(Invitation.class, strid);
			if (aInvitation == null)
				throw new Exception("Invitation inconnu");
			return aInvitation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Invitation",summary="Sauvegarde d'un nouvel invitation",
	description="Permet de sauvegarder un invitation. En retour, l'opération renvoie l'invitation avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le invitation sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Invitation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Invitation save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'invitation à sauvegarder (Sans id renseigné)", required = true) Invitation invitation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(invitation.getId()!=null)
				throw new Exception("Impossible de créer un invitation qui a déjà  un ID!");
			
			// Sauvegarde de l'invitation en base
			new Accesseur<Invitation>().save(invitation);
			
			Utilitaire.loggingRest(this.getClass(), "save", invitation, connexionUser);
			return invitation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création invitation", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Invitation",summary="Sauvegarde d'un invitation déjà enregistré",
	description="Permet de sauvegarder les modifications sur un invitation déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'invitation à enregistrer (avec id renseigné)", required = true) Invitation invitation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(invitation.getId()==null)
				throw new Exception("Impossible d'updater un invitation sans ID!");
			
			// Update de l'invitation en base
			new Accesseur<Invitation>().update(invitation);
			Utilitaire.loggingRest(this.getClass(), "update", invitation, connexionUser);
			return new RetourAppel(0, "Le invitation "+invitation.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update invitation", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Invitation",summary="Suppression d'un invitation déjà enregistré",
	description="Permet de supprimer un invitation déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'invitation à supprimer (avec id renseigné)", required = true) Invitation invitation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(invitation.getId()==null)
				throw new Exception("Impossible de supprimer un invitation sans ID!");
			
			// Suppression de l'invitation en base
			new Accesseur<Invitation>().delete(invitation);
			return new RetourAppel(0, "L'invitation "+invitation.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression invitation", connexionUser);
			throw e;
		}
	}

}
