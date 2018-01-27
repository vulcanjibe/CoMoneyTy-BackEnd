package comoneyty.server.rest;


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

import comoneyty.server.beans.util.ObjetId;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/objetid")
public class ObjetIdRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(tags="ObjetId",summary="Lecture d'un objetid via son id",
	description="Permet de récuperer un objetid à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="L'objetid demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=ObjetId.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public ObjetId getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id de l'objetid", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'objetid en base
			ObjetId aObjetId = (ObjetId) new Accesseur<ObjetId>().get(ObjetId.class, strid);
			if (aObjetId == null)
				throw new Exception("ObjetId inconnu");
			return aObjetId;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags="ObjetId",summary="Sauvegarde d'un nouvel objetid",
	description="Permet de sauvegarder un objetid. En retour, l'opération renvoie l'objetid avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="L'objetid sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=ObjetId.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public ObjetId save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'objetid à sauvegarder (Sans id renseigné)", required = true) ObjetId objetid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(objetid.getId()!=null)
				throw new Exception("Impossible de créer un objetid qui a déjà  un ID!");
			
			// Sauvegarde de l'objetid en base
			new Accesseur<ObjetId>().save(objetid);
			
			Utilitaire.loggingRest(this.getClass(), "save", objetid, connexionUser);
			return objetid;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création objetid", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags="ObjetId",summary="Sauvegarde d'un objetid déjà enregistré",
	description="Permet de sauvegarder les modifications sur un objetid déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'objetid à enregistrer (avec id renseigné)", required = true) ObjetId objetid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(objetid.getId()==null)
				throw new Exception("Impossible d'updater un objetid sans ID!");
			
			// Update de l'objetid en base
			new Accesseur<ObjetId>().update(objetid);
			Utilitaire.loggingRest(this.getClass(), "update", objetid, connexionUser);
			return new RetourAppel(0, "L'objetid "+objetid.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update objetid", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags="ObjetId",summary="Suppression d'un objetid déjà enregistré",
	description="Permet de supprimer un objetid déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'objetid à supprimer (avec id renseigné)", required = true) ObjetId objetid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(objetid.getId()==null)
				throw new Exception("Impossible de supprimer un objetid sans ID!");
			
			// Suppression de l'objetid en base
			new Accesseur<ObjetId>().delete(objetid);
			return new RetourAppel(0, "L'objetid "+objetid.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression objetid", connexionUser);
			throw e;
		}
	}

	

}
