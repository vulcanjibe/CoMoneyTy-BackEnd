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

import comoneyty.server.beans.Credential;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/credential")
public class CredentialRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(tags="Credential",summary="Lecture d'un credential via son id",
	description="Permet de récuperer un credential à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="L'credential demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Credential.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Credential getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id de l'credential", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'credential en base
			Credential aCredential = (Credential) new Accesseur<Credential>().get(Credential.class, strid);
			if (aCredential == null)
				throw new Exception("Credential inconnu");
			return aCredential;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags="Credential",summary="Sauvegarde d'un nouvel credential",
	description="Permet de sauvegarder un credential. En retour, l'opération renvoie l'credential avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="L'credential sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Credential.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Credential save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'credential à sauvegarder (Sans id renseigné)", required = true) Credential credential)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(credential.getId()!=null)
				throw new Exception("Impossible de créer un credential qui a déjà  un ID!");
			
			// Sauvegarde de l'credential en base
			new Accesseur<Credential>().save(credential);
			
			Utilitaire.loggingRest(this.getClass(), "save", credential, connexionUser);
			return credential;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création credential", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags="Credential",summary="Sauvegarde d'un credential déjà enregistré",
	description="Permet de sauvegarder les modifications sur un credential déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'credential à enregistrer (avec id renseigné)", required = true) Credential credential)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(credential.getId()==null)
				throw new Exception("Impossible d'updater un credential sans ID!");
			
			// Update de l'credential en base
			new Accesseur<Credential>().update(credential);
			Utilitaire.loggingRest(this.getClass(), "update", credential, connexionUser);
			return new RetourAppel(0, "L'credential "+credential.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update credential", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags="Credential",summary="Suppression d'un credential déjà enregistré",
	description="Permet de supprimer un credential déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'credential à supprimer (avec id renseigné)", required = true) Credential credential)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(credential.getId()==null)
				throw new Exception("Impossible de supprimer un credential sans ID!");
			
			// Suppression de l'credential en base
			new Accesseur<Credential>().delete(credential);
			return new RetourAppel(0, "L'credential "+credential.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression credential", connexionUser);
			throw e;
		}
	}

	

}
