package mybank.server.rest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.Depense;
import mybank.server.beans.Event;
import mybank.server.beans.LienEventUser;
import mybank.server.beans.Mouvement;
import mybank.server.beans.User;
import mybank.server.beans.javascript.UserAvecDepense;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;

@Path("/event")
public class EventRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			List<Event> liste = (List<Event>) Accesseur.getListe(Event.class);
			// Traitement de la log
			// Utilitaire.loggingRest(this.getClass(), "save", "", connexionUser.getUser());
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "", connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String strid) {
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event aEvent = (Event) Accesseur.get(Event.class, strid);
			if (aEvent == null)
				throw new Exception("User inconnu");
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser.getUser());
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListe(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {

		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			String clauseWhere = new String(data, "UTF-8");
			List<User> liste = (List<User>) Accesseur.getListeFiltre(User.class, clauseWhere);

			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "getListe", data, connexionUser.getUser());
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getListe", data, connexionUser.getUser());
			return Reponse.reponseKO(e);
		}

	}

	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			User aEvent = new User();

			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser.getUser());
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "create", "", connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event aEvent = mapper.readValue(new String(data, "UTF-8"), Event.class);
			Accesseur.save(aEvent);

			// Lien avec l'utilisateur
			LienEventUser lien = new LienEventUser(connexionUser.getUser().getId(), aEvent.getId());
			Accesseur.save(lien);
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event aEvent = mapper.readValue(new String(data, "UTF-8"), Event.class);
			Accesseur.update(aEvent);
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un event connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			User aEvent = mapper.readValue(new String(data, "UTF-8"), User.class);
			Accesseur.delete(aEvent);
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersDeEventId(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un user connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<LienEventUser> liste = (List<LienEventUser>) Accesseur.getListeFiltre(LienEventUser.class,
					"eventId='" + idEvent + "'");
			ArrayList<User> listeUser = new ArrayList<>();
			// Recupération des Event
			for (LienEventUser lien : liste) {
				listeUser.add((User) Accesseur.get(User.class, lien.getUserId()));
			}

			// Je recupere toutes les depenses de cet event
			List<Depense> listeDepense = (List<Depense>) Accesseur.getListeFiltre(Depense.class,
					"idEvent='" + idEvent + "'");
			double montantEvent = 0;
			for (Depense depense : listeDepense) {
				montantEvent += depense.getMontant();
			}
			ArrayList<UserAvecDepense> listeUserAvecDepense = new ArrayList<>();
			for (User user : listeUser) {
				double montantPaye = 0;
				double montantDu = 0;
				for (Depense depense : listeDepense) {
					if (depense.getIdPayeur().equals(user.getId()))
						montantPaye += depense.getMontant();
				}
				montantDu = montantEvent / listeUser.size() - montantPaye;
				UserAvecDepense userAvecDepense = new UserAvecDepense(user);
				userAvecDepense.setaPaye(montantPaye);
				userAvecDepense.setDoit(montantDu);
				listeUserAvecDepense.add(userAvecDepense);
			}

			// Traitement de la log
			return Reponse.getResponseOK(listeUserAvecDepense);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/users", "/" + idEvent + "/users",
					connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}/bilan")
	@Produces(MediaType.APPLICATION_JSON)
	public Response bilan(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un user connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			ArrayList<Mouvement> listeMouvement = bilan(idEvent);
			// Qui a pay� le plus
			// Traitement de la log
			return Reponse.getResponseOK(listeMouvement);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/bilan", "/" + idEvent + "/bilan",
					connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	public static ArrayList<Mouvement> bilan(String idEvent) throws Exception {
		List<LienEventUser> liste = (List<LienEventUser>) Accesseur.getListeFiltre(LienEventUser.class,
				"eventId='" + idEvent + "'");
		ArrayList<User> listeUser = new ArrayList<>();
		// Recupération des Event
		for (LienEventUser lien : liste) {
			listeUser.add((User) Accesseur.get(User.class, lien.getUserId()));
		}

		// Je recupere toutes les depenses de cet event
		List<Depense> listeDepense = (List<Depense>) Accesseur.getListeFiltre(Depense.class,
				"idEvent='" + idEvent + "'");
		double montantEvent = 0;
		for (Depense depense : listeDepense) {
			montantEvent += depense.getMontant();
		}
		double montantCible = montantEvent / listeUser.size();
		ArrayList<UserAvecDepense> listeUserAvecDepense = new ArrayList<>();
		for (User user : listeUser) {
			double montantPaye = 0;
			double montantDu = 0;
			for (Depense depense : listeDepense) {
				if (depense.getIdPayeur().equals(user.getId()))
					montantPaye += depense.getMontant();
			}
			montantDu = montantEvent / listeUser.size() - montantPaye;
			UserAvecDepense userAvecDepense = new UserAvecDepense(user);
			userAvecDepense.setaPaye(montantPaye);
			userAvecDepense.setDoit(montantDu);
			listeUserAvecDepense.add(userAvecDepense);
		}
		ArrayList<Mouvement> listeMouvement = new ArrayList<>();
		arrange(listeUserAvecDepense, montantCible, listeMouvement);
		return listeMouvement;
	}

	private static void arrange(ArrayList<UserAvecDepense> listeUserAvecDepense, double montantCible,
			ArrayList<Mouvement> listeMouvement) {
		boolean newMvt = false;
		for (UserAvecDepense userAvecDepenseSource : listeUserAvecDepense) {
			if (userAvecDepenseSource.getDoit() > 0) {
				// Je cherche un qui peut recevoir le tout
				for (UserAvecDepense userAvecDepenseCible : listeUserAvecDepense) {
					if (userAvecDepenseCible.getUser().getId().equals(userAvecDepenseSource.getUser().getId()))
						continue;
					if (userAvecDepenseCible.getaPaye() - montantCible >= userAvecDepenseSource.getDoit()) {
						// Il peut prendre toute la somme
						Mouvement mvt = new Mouvement();
						mvt.setIdEmetteur(userAvecDepenseSource.getUser().getId());
						mvt.setIdDestinataire(userAvecDepenseCible.getUser().getId());
						mvt.setMontant(userAvecDepenseSource.getDoit());
						userAvecDepenseSource.paye(+mvt.getMontant());
						userAvecDepenseCible.paye(-mvt.getMontant());
						listeMouvement.add(mvt);
						newMvt = true;
						break;
					}
				}
			}
		}
		if (newMvt) {
			arrange(listeUserAvecDepense, montantCible, listeMouvement);
			return;
		}
		// J'ai pas r�ussi � dispatcher en entier...
		ArrayList<UserAvecDepense> listeUserAPaye = new ArrayList<>();
		double montantMini = Double.NEGATIVE_INFINITY;
		// Je cherche un qui peut recevoir le tout
		for (UserAvecDepense userAvecDepenseCible : listeUserAvecDepense) {
			if (userAvecDepenseCible.getDoit() < 0) {
				listeUserAPaye.add(userAvecDepenseCible);
				if (userAvecDepenseCible.getDoit() > montantMini)
					montantMini = userAvecDepenseCible.getDoit();
			}
		}
		if (!listeUserAPaye.isEmpty()) {
			for (UserAvecDepense userAvecDepenseSource : listeUserAvecDepense) {
				if (userAvecDepenseSource.getDoit() > 0) {
					// Je cherche un qui peut recevoir le tout
					for (UserAvecDepense userAvecDepenseCible : listeUserAvecDepense) {
						if (userAvecDepenseCible.getUser().getId().equals(userAvecDepenseSource.getUser().getId()))
							continue;
						// J'ai un gars qui doit.
						double montantADonner = userAvecDepenseSource.getDoit() / listeUserAPaye.size();
						if (montantADonner > -montantMini)
							montantADonner = -montantMini;
						for (UserAvecDepense usr : listeUserAPaye) {
							Mouvement mvt = new Mouvement();
							mvt.setIdEmetteur(userAvecDepenseSource.getUser().getId());
							mvt.setIdDestinataire(usr.getUser().getId());
							mvt.setMontant(montantADonner);
							userAvecDepenseSource.paye(+mvt.getMontant());
							usr.paye(-mvt.getMontant());
							listeMouvement.add(mvt);
						}
						newMvt = true;
						break;
					}
				}
				if (newMvt)
					break;
			}
		}

		if (newMvt) {
			arrange(listeUserAvecDepense, montantCible, listeMouvement);
			return;
		}
	}

	@GET
	@Path("/{id}/depenses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepensesDeEventId(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un user connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Depense> liste = (List<Depense>) Accesseur.getListeFiltre(Depense.class, "idEvent='" + idEvent + "'");
			// Traitement de la log
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/depenses", "/" + idEvent + "/depenses",
					connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Path("/supprimeUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimeUSer(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// Vérification de l'accès depuis un lienEventUser connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			LienEventUser aLienEventUser = mapper.readValue(new String(data, "UTF-8"), LienEventUser.class);
			Accesseur.deleteWhere(aLienEventUser,
					"userId='" + aLienEventUser.getUserId() + "' and eventId='" + aLienEventUser.getEventId() + "'");
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.getResponseOK(aLienEventUser);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) throws Exception {

		System.out.println(fileDetails.getFileName());
		// byte[] buffer = new byte[uploadedInputStream.available()];
		// File targetFile = new File("src/main/resources/targetFile.tmp");
		File targetFile = new File("../standalone/deployments/Image.war/event/" + fileDetails.getFileName());
		long res = java.nio.file.Files.copy(uploadedInputStream, targetFile.toPath(),
				StandardCopyOption.REPLACE_EXISTING);
		System.out.println("WRITE = " + res + " dans " + targetFile.toPath());
		uploadedInputStream.close();
		return Response.ok().build();
	}
}
