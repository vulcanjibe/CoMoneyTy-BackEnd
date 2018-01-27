package comoneyty.server.rest;


import java.util.ArrayList;
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
// import des dépendances
import comoneyty.server.beans.Event;
import comoneyty.server.beans.Invitation;
import comoneyty.server.beans.LienEventUser;
import comoneyty.server.beans.Mouvement;
import comoneyty.server.beans.Relation;
import comoneyty.server.beans.User;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/user")
public class UserRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Lecture d'un user via son id",
	description="Permet de récuperer un user à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le user demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=User.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public User getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id user", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'user en base
			User aUser = (User) new Accesseur<User>().get(User.class, strid);
			if (aUser == null)
				throw new Exception("User inconnu");
			return aUser;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Sauvegarde d'un nouvel user",
	description="Permet de sauvegarder un user. En retour, l'opération renvoie l'user avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le user sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=User.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public User save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'user à sauvegarder (Sans id renseigné)", required = true) User user)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(user.getId()!=null)
				throw new Exception("Impossible de créer un user qui a déjà  un ID!");
			
			// Sauvegarde de l'user en base
			new Accesseur<User>().save(user);
			
			Utilitaire.loggingRest(this.getClass(), "save", user, connexionUser);
			return user;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création user", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Sauvegarde d'un user déjà enregistré",
	description="Permet de sauvegarder les modifications sur un user déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'user à enregistrer (avec id renseigné)", required = true) User user)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(user.getId()==null)
				throw new Exception("Impossible d'updater un user sans ID!");
			
			// Update de l'user en base
			new Accesseur<User>().update(user);
			Utilitaire.loggingRest(this.getClass(), "update", user, connexionUser);
			return new RetourAppel(0, "Le user "+user.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update user", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Suppression d'un user déjà enregistré",
	description="Permet de supprimer un user déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'user à supprimer (avec id renseigné)", required = true) User user)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(user.getId()==null)
				throw new Exception("Impossible de supprimer un user sans ID!");
			
			// Suppression de l'user en base
			new Accesseur<User>().delete(user);
			return new RetourAppel(0, "L'user "+user.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression user", connexionUser);
			throw e;
		}
	}

// LIEN-DEBUT
	@GET
	@Path("/{id}/events")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Lecture des events d'un user défini par son id",
	description="Permet de récuperer tous les events du user de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le user demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Event.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getEventDeUser(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de user", required = true) @PathParam("id") String idUser) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			// On cherche les liens EventUset
			List<LienEventUser> liste = new Accesseur<LienEventUser>()
					.getListeFiltre(LienEventUser.class, "idUser='" + idUser + "'");
			if(idUser.equals("999999"))
				liste = new Accesseur<LienEventUser>()
				.getListe(LienEventUser.class);
			ArrayList<Event> listeEvent = new ArrayList<>();
			// RecupÃ©ration des Event
			for (LienEventUser lien : liste) {
				Event event = new Accesseur<Event>().get(Event.class, lien.getIdEvent());

				if (lien.getRoles() == null || lien.getRoles().isEmpty()) {

					lien.setRoles(new ArrayList<>());
					lien.getRoles().add("Participant");
				}

				event.setRoles(lien.getRoles());
				listeEvent.add(event);
			}

			// Pour chaque event je calcule le montant
			for (Event event : listeEvent) {
				List<Depense> listeDepense = new Accesseur<Depense>()
						.getListeFiltre(Depense.class, "idEvent='" + event.getId() + "'");
				double montantEvent = 0;
				double montantPaye = 0;
				for (Depense depense : listeDepense) {
					montantEvent += depense.getMontant();
					if (depense.getIdPayeur().equals(idUser))
						montantPaye += depense.getMontant();
				}
				event.setMontantTotal(montantEvent);

				List<LienEventUser> listeUser = new Accesseur<LienEventUser>()
						.getListeFiltre(LienEventUser.class, "idEvent='" + event.getId() + "'");
				double montantDu = (montantEvent / listeUser.size()) - montantPaye;
				event.setMontantDu(montantDu);
				event.setMontantDepense(montantPaye);
			}

			// Check de la cloture des event + prise en compte des paiements
			for (Event event : listeEvent) {
				List<Mouvement> listeMouvement = new Accesseur<Mouvement>()
						.getListeFiltre(Mouvement.class, "idEvent='" + event.getId() + "'");
				if (event.getEtat().equalsIgnoreCase("En cours de solde")) {
					boolean tousRealise = true;
					for (Mouvement mouvement : listeMouvement) {
						if (!mouvement.getEtat().equals("Réalisé")) {
							tousRealise = false;
							break;
						}
					}
					if (tousRealise) {
						event.setEtat("Clos");
						new Accesseur<Event>().update(event);
					}
				}
				for (Mouvement mouvement : listeMouvement) {
					if (mouvement.getEtat().equals("Réalisé")) {
						if (mouvement.getIdEmetteur().equals(idUser)) {
							event.setMontantDepense(event.getMontantDepense() + mouvement.getMontant());
							event.setMontantDu(event.getMontantDu() - mouvement.getMontant());
						}
						if (mouvement.getIdDestinataire().equals(idUser)) {
							event.setMontantDepense(event.getMontantDepense() - mouvement.getMontant());
							event.setMontantDu(event.getMontantDu() + mouvement.getMontant());
						}

					}
				}
			}

			return listeEvent;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/event", "/" + idUser + "/depenses", connexionUser);
			throw e;
		}
	}
// LIEN-DEBUT
	@GET
	@Path("/{id}/invitations")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Lecture des invitations d'un user défini par son id",
	description="Permet de récuperer tous les invitations du user de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le user demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Invitation.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getInvitationDeUser(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de user", required = true) @PathParam("id") String idUser) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Invitation> liste = new Accesseur<Invitation>().getListeFiltre(Invitation.class,
					"idUser='" + idUser + "'");
			// Traitement de la log
			return liste;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/invitation", "/" + idUser + "/depenses", connexionUser);
			throw e;
		}
	}
// LIEN-DEBUT
	@GET
	@Path("/{id}/relations")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="User",summary="Lecture des relations d'un user défini par son id",
	description="Permet de récuperer tous les relations du user de l'id donné",
	responses= { @ApiResponse(responseCode="200",description="Le user demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=User.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Object getRelationDeUser(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@Parameter(description = "L'id de user", required = true) @PathParam("id") String idUser) throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Relation> liste = new Accesseur<Relation>().getListeFiltre(Relation.class,
					"idUser1='" + idUser + "'");
			ArrayList<User> listeRelation = new ArrayList<>();
			// RecupÃ©ration des Event
			for (Relation lien : liste) {
				listeRelation.add(new  Accesseur<User>().get(User.class, lien.getIdUser2()));
			}
			
			return listeRelation;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/relation", "/" + idUser + "/depenses", connexionUser);
			throw e;
		}
	}
}
