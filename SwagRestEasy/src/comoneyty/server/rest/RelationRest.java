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

import comoneyty.server.beans.Relation;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/relation")
public class RelationRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Relation",summary="Lecture d'un relation via son id",
	description="Permet de récuperer un relation à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le relation demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Relation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Relation getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id relation", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'relation en base
			Relation aRelation = (Relation) new Accesseur<Relation>().get(Relation.class, strid);
			if (aRelation == null)
				throw new Exception("Relation inconnu");
			return aRelation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Relation",summary="Sauvegarde d'un nouvel relation",
	description="Permet de sauvegarder un relation. En retour, l'opération renvoie l'relation avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le relation sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Relation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Relation save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'relation à sauvegarder (Sans id renseigné)", required = true) Relation relation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(relation.getId()!=null)
				throw new Exception("Impossible de créer un relation qui a déjà  un ID!");
			
			// Sauvegarde de l'relation en base
			new Accesseur<Relation>().save(relation);
			
			Utilitaire.loggingRest(this.getClass(), "save", relation, connexionUser);
			return relation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création relation", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Relation",summary="Sauvegarde d'un relation déjà enregistré",
	description="Permet de sauvegarder les modifications sur un relation déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'relation à enregistrer (avec id renseigné)", required = true) Relation relation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(relation.getId()==null)
				throw new Exception("Impossible d'updater un relation sans ID!");
			
			// Update de l'relation en base
			new Accesseur<Relation>().update(relation);
			Utilitaire.loggingRest(this.getClass(), "update", relation, connexionUser);
			return new RetourAppel(0, "Le relation "+relation.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update relation", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Relation",summary="Suppression d'un relation déjà enregistré",
	description="Permet de supprimer un relation déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'relation à supprimer (avec id renseigné)", required = true) Relation relation)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(relation.getId()==null)
				throw new Exception("Impossible de supprimer un relation sans ID!");
			
			// Suppression de l'relation en base
			new Accesseur<Relation>().delete(relation);
			return new RetourAppel(0, "L'relation "+relation.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression relation", connexionUser);
			throw e;
		}
	}

}