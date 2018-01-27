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

import comoneyty.server.beans.Event;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

// import des dépendances
import comoneyty.server.beans.User;
import comoneyty.server.beans.Depense;
import comoneyty.server.beans.HistoriqueEvent;
import comoneyty.server.beans.Mouvement;
@Path("/event")
public class EventRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Lecture d'un event via son id",
	description="Permet de récuperer un event à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le event demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Event.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Event getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id event", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'event en base
			Event aEvent = (Event) new Accesseur<Event>().get(Event.class, strid);
			if (aEvent == null)
				throw new Exception("Event inconnu");
			return aEvent;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Sauvegarde d'un nouvel event",
	description="Permet de sauvegarder un event. En retour, l'opération renvoie l'event avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le event sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Event.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Event save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'event à sauvegarder (Sans id renseigné)", required = true) Event event)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(event.getId()!=null)
				throw new Exception("Impossible de créer un event qui a déjà  un ID!");
			
			// Sauvegarde de l'event en base
			new Accesseur<Event>().save(event);
			
			Utilitaire.loggingRest(this.getClass(), "save", event, connexionUser);
			return event;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création event", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Sauvegarde d'un event déjà enregistré",
	description="Permet de sauvegarder les modifications sur un event déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'event à enregistrer (avec id renseigné)", required = true) Event event)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(event.getId()==null)
				throw new Exception("Impossible d'updater un event sans ID!");
			
			// Update de l'event en base
			new Accesseur<Event>().update(event);
			Utilitaire.loggingRest(this.getClass(), "update", event, connexionUser);
			return new RetourAppel(0, "Le event "+event.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update event", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Suppression d'un event déjà enregistré",
	description="Permet de supprimer un event déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'event à supprimer (avec id renseigné)", required = true) Event event)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(event.getId()==null)
				throw new Exception("Impossible de supprimer un event sans ID!");
			
			// Suppression de l'event en base
			new Accesseur<Event>().delete(event);
			return new RetourAppel(0, "L'event "+event.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression event", connexionUser);
			throw e;
		}
	}

// LIEN-DEBUT
	@GET
	@Path("/{id}/users")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Lecture des users d'un event défini par son id",
	description="Permet de récuperer tous les users du event de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le event demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=User.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getUserDeEvent(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de event", required = true) @PathParam("id") String idEvent) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<User> liste = new Accesseur<User>().getListeFiltre(User.class,
					"idEvent='" + idEvent + "'");
			// Traitement de la log
			return liste;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/user", "/" + idEvent + "/depenses", connexionUser);
			throw e;
		}
	}
// LIEN-DEBUT
	@GET
	@Path("/{id}/depenses")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Lecture des depenses d'un event défini par son id",
	description="Permet de récuperer tous les depenses du event de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le event demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Depense.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getDepenseDeEvent(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de event", required = true) @PathParam("id") String idEvent) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Depense> liste = new Accesseur<Depense>().getListeFiltre(Depense.class,
					"idEvent='" + idEvent + "'");
			// Traitement de la log
			return liste;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/depense", "/" + idEvent + "/depenses", connexionUser);
			throw e;
		}
	}
// LIEN-DEBUT
	@GET
	@Path("/{id}/historiqueevents")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Lecture des historiqueevents d'un event défini par son id",
	description="Permet de récuperer tous les historiqueevents du event de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le event demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=HistoriqueEvent.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getHistoriqueEventDeEvent(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de event", required = true) @PathParam("id") String idEvent) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<HistoriqueEvent> liste = new Accesseur<HistoriqueEvent>().getListeFiltre(HistoriqueEvent.class,
					"idEvent='" + idEvent + "'");
			// Traitement de la log
			return liste;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/historiqueevent", "/" + idEvent + "/depenses", connexionUser);
			throw e;
		}
	}
// LIEN-DEBUT
	@GET
	@Path("/{id}/mouvements")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Event",summary="Lecture des mouvements d'un event défini par son id",
	description="Permet de récuperer tous les mouvements du event de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le event demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Mouvement.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getMouvementDeEvent(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de event", required = true) @PathParam("id") String idEvent) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Mouvement> liste = new Accesseur<Mouvement>().getListeFiltre(Mouvement.class,
					"idEvent='" + idEvent + "'");
			// Traitement de la log
			return liste;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/mouvement", "/" + idEvent + "/depenses", connexionUser);
			throw e;
		}
	}
}
