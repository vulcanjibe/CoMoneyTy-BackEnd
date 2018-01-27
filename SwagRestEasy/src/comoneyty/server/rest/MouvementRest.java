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

import comoneyty.server.beans.Mouvement;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/mouvement")
public class MouvementRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Mouvement",summary="Lecture d'un mouvement via son id",
	description="Permet de récuperer un mouvement à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le mouvement demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Mouvement.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Mouvement getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id mouvement", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'mouvement en base
			Mouvement aMouvement = (Mouvement) new Accesseur<Mouvement>().get(Mouvement.class, strid);
			if (aMouvement == null)
				throw new Exception("Mouvement inconnu");
			return aMouvement;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Mouvement",summary="Sauvegarde d'un nouvel mouvement",
	description="Permet de sauvegarder un mouvement. En retour, l'opération renvoie l'mouvement avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le mouvement sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Mouvement.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Mouvement save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'mouvement à sauvegarder (Sans id renseigné)", required = true) Mouvement mouvement)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(mouvement.getId()!=null)
				throw new Exception("Impossible de créer un mouvement qui a déjà  un ID!");
			
			// Sauvegarde de l'mouvement en base
			new Accesseur<Mouvement>().save(mouvement);
			
			Utilitaire.loggingRest(this.getClass(), "save", mouvement, connexionUser);
			return mouvement;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création mouvement", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Mouvement",summary="Sauvegarde d'un mouvement déjà enregistré",
	description="Permet de sauvegarder les modifications sur un mouvement déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'mouvement à enregistrer (avec id renseigné)", required = true) Mouvement mouvement)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(mouvement.getId()==null)
				throw new Exception("Impossible d'updater un mouvement sans ID!");
			
			// Update de l'mouvement en base
			new Accesseur<Mouvement>().update(mouvement);
			Utilitaire.loggingRest(this.getClass(), "update", mouvement, connexionUser);
			return new RetourAppel(0, "Le mouvement "+mouvement.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update mouvement", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Mouvement",summary="Suppression d'un mouvement déjà enregistré",
	description="Permet de supprimer un mouvement déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'mouvement à supprimer (avec id renseigné)", required = true) Mouvement mouvement)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(mouvement.getId()==null)
				throw new Exception("Impossible de supprimer un mouvement sans ID!");
			
			// Suppression de l'mouvement en base
			new Accesseur<Mouvement>().delete(mouvement);
			return new RetourAppel(0, "L'mouvement "+mouvement.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression mouvement", connexionUser);
			throw e;
		}
	}

}
