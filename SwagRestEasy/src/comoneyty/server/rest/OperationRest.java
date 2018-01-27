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

import comoneyty.server.beans.Operation;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/operation")
public class OperationRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Operation",summary="Lecture d'un operation via son id",
	description="Permet de récuperer un operation à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le operation demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Operation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Operation getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id operation", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'operation en base
			Operation aOperation = (Operation) new Accesseur<Operation>().get(Operation.class, strid);
			if (aOperation == null)
				throw new Exception("Operation inconnu");
			return aOperation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Operation",summary="Sauvegarde d'un nouvel operation",
	description="Permet de sauvegarder un operation. En retour, l'opération renvoie l'operation avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le operation sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Operation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Operation save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'operation à sauvegarder (Sans id renseigné)", required = true) Operation operation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(operation.getId()!=null)
				throw new Exception("Impossible de créer un operation qui a déjà  un ID!");
			
			// Sauvegarde de l'operation en base
			new Accesseur<Operation>().save(operation);
			
			Utilitaire.loggingRest(this.getClass(), "save", operation, connexionUser);
			return operation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création operation", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Operation",summary="Sauvegarde d'un operation déjà enregistré",
	description="Permet de sauvegarder les modifications sur un operation déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'operation à enregistrer (avec id renseigné)", required = true) Operation operation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(operation.getId()==null)
				throw new Exception("Impossible d'updater un operation sans ID!");
			
			// Update de l'operation en base
			new Accesseur<Operation>().update(operation);
			Utilitaire.loggingRest(this.getClass(), "update", operation, connexionUser);
			return new RetourAppel(0, "Le operation "+operation.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update operation", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Operation",summary="Suppression d'un operation déjà enregistré",
	description="Permet de supprimer un operation déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'operation à supprimer (avec id renseigné)", required = true) Operation operation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(operation.getId()==null)
				throw new Exception("Impossible de supprimer un operation sans ID!");
			
			// Suppression de l'operation en base
			new Accesseur<Operation>().delete(operation);
			return new RetourAppel(0, "L'operation "+operation.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression operation", connexionUser);
			throw e;
		}
	}

}
