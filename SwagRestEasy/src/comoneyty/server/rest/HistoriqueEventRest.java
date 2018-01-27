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

import comoneyty.server.beans.HistoriqueEvent;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/historiqueevent")
public class HistoriqueEventRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="HistoriqueEvent",summary="Lecture d'un historiqueevent via son id",
	description="Permet de récuperer un historiqueevent à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le historiqueevent demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=HistoriqueEvent.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public HistoriqueEvent getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id historiqueevent", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'historiqueevent en base
			HistoriqueEvent aHistoriqueEvent = (HistoriqueEvent) new Accesseur<HistoriqueEvent>().get(HistoriqueEvent.class, strid);
			if (aHistoriqueEvent == null)
				throw new Exception("HistoriqueEvent inconnu");
			return aHistoriqueEvent;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="HistoriqueEvent",summary="Sauvegarde d'un nouvel historiqueevent",
	description="Permet de sauvegarder un historiqueevent. En retour, l'opération renvoie l'historiqueevent avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le historiqueevent sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=HistoriqueEvent.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public HistoriqueEvent save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'historiqueevent à sauvegarder (Sans id renseigné)", required = true) HistoriqueEvent historiqueevent)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(historiqueevent.getId()!=null)
				throw new Exception("Impossible de créer un historiqueevent qui a déjà  un ID!");
			
			// Sauvegarde de l'historiqueevent en base
			new Accesseur<HistoriqueEvent>().save(historiqueevent);
			
			Utilitaire.loggingRest(this.getClass(), "save", historiqueevent, connexionUser);
			return historiqueevent;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création historiqueevent", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="HistoriqueEvent",summary="Sauvegarde d'un historiqueevent déjà enregistré",
	description="Permet de sauvegarder les modifications sur un historiqueevent déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'historiqueevent à enregistrer (avec id renseigné)", required = true) HistoriqueEvent historiqueevent)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(historiqueevent.getId()==null)
				throw new Exception("Impossible d'updater un historiqueevent sans ID!");
			
			// Update de l'historiqueevent en base
			new Accesseur<HistoriqueEvent>().update(historiqueevent);
			Utilitaire.loggingRest(this.getClass(), "update", historiqueevent, connexionUser);
			return new RetourAppel(0, "Le historiqueevent "+historiqueevent.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update historiqueevent", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="HistoriqueEvent",summary="Suppression d'un historiqueevent déjà enregistré",
	description="Permet de supprimer un historiqueevent déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'historiqueevent à supprimer (avec id renseigné)", required = true) HistoriqueEvent historiqueevent)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(historiqueevent.getId()==null)
				throw new Exception("Impossible de supprimer un historiqueevent sans ID!");
			
			// Suppression de l'historiqueevent en base
			new Accesseur<HistoriqueEvent>().delete(historiqueevent);
			return new RetourAppel(0, "L'historiqueevent "+historiqueevent.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression historiqueevent", connexionUser);
			throw e;
		}
	}

}
