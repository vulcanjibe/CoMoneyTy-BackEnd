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

import comoneyty.server.beans.Depense;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/depense")
public class DepenseRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Depense",summary="Lecture d'un depense via son id",
	description="Permet de récuperer un depense à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le depense demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Depense.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Depense getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id depense", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'depense en base
			Depense aDepense = (Depense) new Accesseur<Depense>().get(Depense.class, strid);
			if (aDepense == null)
				throw new Exception("Depense inconnu");
			return aDepense;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Depense",summary="Sauvegarde d'un nouvel depense",
	description="Permet de sauvegarder un depense. En retour, l'opération renvoie l'depense avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le depense sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Depense.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Depense save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'depense à sauvegarder (Sans id renseigné)", required = true) Depense depense)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(depense.getId()!=null)
				throw new Exception("Impossible de créer un depense qui a déjà  un ID!");
			
			// Sauvegarde de l'depense en base
			new Accesseur<Depense>().save(depense);
			
			Utilitaire.loggingRest(this.getClass(), "save", depense, connexionUser);
			return depense;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création depense", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Depense",summary="Sauvegarde d'un depense déjà enregistré",
	description="Permet de sauvegarder les modifications sur un depense déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'depense à enregistrer (avec id renseigné)", required = true) Depense depense)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(depense.getId()==null)
				throw new Exception("Impossible d'updater un depense sans ID!");
			
			// Update de l'depense en base
			new Accesseur<Depense>().update(depense);
			Utilitaire.loggingRest(this.getClass(), "update", depense, connexionUser);
			return new RetourAppel(0, "Le depense "+depense.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update depense", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Depense",summary="Suppression d'un depense déjà enregistré",
	description="Permet de supprimer un depense déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'depense à supprimer (avec id renseigné)", required = true) Depense depense)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(depense.getId()==null)
				throw new Exception("Impossible de supprimer un depense sans ID!");
			
			// Suppression de l'depense en base
			new Accesseur<Depense>().delete(depense);
			return new RetourAppel(0, "L'depense "+depense.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression depense", connexionUser);
			throw e;
		}
	}

}
