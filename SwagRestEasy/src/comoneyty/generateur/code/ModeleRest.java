package comoneyty.generateur.code;

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

import comoneyty.server.beans.Modele;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/modele")
public class ModeleRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Modele",summary="Lecture d'un modele via son id",
	description="Permet de récuperer un modele à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le modele demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Modele.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Modele getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id modele", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'modele en base
			Modele aModele = (Modele) new Accesseur<Modele>().get(Modele.class, strid);
			if (aModele == null)
				throw new Exception("Modele inconnu");
			return aModele;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Modele",summary="Sauvegarde d'un nouvel modele",
	description="Permet de sauvegarder un modele. En retour, l'opération renvoie l'modele avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le modele sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Modele.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Modele save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'modele à sauvegarder (Sans id renseigné)", required = true) Modele modele)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(modele.getId()!=null)
				throw new Exception("Impossible de créer un modele qui a déjà  un ID!");
			
			// Sauvegarde de l'modele en base
			new Accesseur<Modele>().save(modele);
			
			Utilitaire.loggingRest(this.getClass(), "save", modele, connexionUser);
			return modele;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création modele", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Modele",summary="Sauvegarde d'un modele déjà enregistré",
	description="Permet de sauvegarder les modifications sur un modele déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'modele à enregistrer (avec id renseigné)", required = true) Modele modele)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(modele.getId()==null)
				throw new Exception("Impossible d'updater un modele sans ID!");
			
			// Update de l'modele en base
			new Accesseur<Modele>().update(modele);
			Utilitaire.loggingRest(this.getClass(), "update", modele, connexionUser);
			return new RetourAppel(0, "Le modele "+modele.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update modele", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Modele",summary="Suppression d'un modele déjà enregistré",
	description="Permet de supprimer un modele déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'modele à supprimer (avec id renseigné)", required = true) Modele modele)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(modele.getId()==null)
				throw new Exception("Impossible de supprimer un modele sans ID!");
			
			// Suppression de l'modele en base
			new Accesseur<Modele>().delete(modele);
			return new RetourAppel(0, "L'modele "+modele.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression modele", connexionUser);
			throw e;
		}
	}

// LIEN-DEBUT
	@GET
	@Path("/{id}/objetlies")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Modele",summary="Lecture des objetlies d'un modele défini par son id",
	description="Permet de récuperer tous les objetlies du modele de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le modele demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Objetlie.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getObjetlieDeModele(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de modele", required = true) @PathParam("id") String idModele) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Objetlie> liste = new Accesseur<Objetlie>().getListeFiltre(Objetlie.class,
					"idModele='" + idModele + "'");
			// Traitement de la log
			return liste;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/objetlie", "/" + idModele + "/depenses", connexionUser);
			throw e;
		}
	}
// LIEN-FIN

}
