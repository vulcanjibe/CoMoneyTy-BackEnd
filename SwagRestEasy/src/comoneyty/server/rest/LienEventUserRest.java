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

import comoneyty.server.beans.LienEventUser;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/lieneventuser")
public class LienEventUserRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="LienEventUser",summary="Lecture d'un lieneventuser via son id",
	description="Permet de récuperer un lieneventuser à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le lieneventuser demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=LienEventUser.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public LienEventUser getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id lieneventuser", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'lieneventuser en base
			LienEventUser aLienEventUser = (LienEventUser) new Accesseur<LienEventUser>().get(LienEventUser.class, strid);
			if (aLienEventUser == null)
				throw new Exception("LienEventUser inconnu");
			return aLienEventUser;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="LienEventUser",summary="Sauvegarde d'un nouvel lieneventuser",
	description="Permet de sauvegarder un lieneventuser. En retour, l'opération renvoie l'lieneventuser avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le lieneventuser sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=LienEventUser.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public LienEventUser save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'lieneventuser à sauvegarder (Sans id renseigné)", required = true) LienEventUser lieneventuser)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(lieneventuser.getId()!=null)
				throw new Exception("Impossible de créer un lieneventuser qui a déjà  un ID!");
			
			// Sauvegarde de l'lieneventuser en base
			new Accesseur<LienEventUser>().save(lieneventuser);
			
			Utilitaire.loggingRest(this.getClass(), "save", lieneventuser, connexionUser);
			return lieneventuser;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création lieneventuser", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="LienEventUser",summary="Sauvegarde d'un lieneventuser déjà enregistré",
	description="Permet de sauvegarder les modifications sur un lieneventuser déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'lieneventuser à enregistrer (avec id renseigné)", required = true) LienEventUser lieneventuser)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(lieneventuser.getId()==null)
				throw new Exception("Impossible d'updater un lieneventuser sans ID!");
			
			// Update de l'lieneventuser en base
			new Accesseur<LienEventUser>().update(lieneventuser);
			Utilitaire.loggingRest(this.getClass(), "update", lieneventuser, connexionUser);
			return new RetourAppel(0, "Le lieneventuser "+lieneventuser.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update lieneventuser", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="LienEventUser",summary="Suppression d'un lieneventuser déjà enregistré",
	description="Permet de supprimer un lieneventuser déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'lieneventuser à supprimer (avec id renseigné)", required = true) LienEventUser lieneventuser)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(lieneventuser.getId()==null)
				throw new Exception("Impossible de supprimer un lieneventuser sans ID!");
			
			// Suppression de l'lieneventuser en base
			new Accesseur<LienEventUser>().delete(lieneventuser);
			return new RetourAppel(0, "L'lieneventuser "+lieneventuser.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression lieneventuser", connexionUser);
			throw e;
		}
	}

}
