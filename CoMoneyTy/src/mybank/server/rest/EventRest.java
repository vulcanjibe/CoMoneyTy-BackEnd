package mybank.server.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import mybank.server.beans.Depense;
import mybank.server.beans.Event;
import mybank.server.beans.HistoriqueEvent;
import mybank.server.beans.LienEventUser;
import mybank.server.beans.Message;
import mybank.server.beans.Mouvement;
import mybank.server.beans.User;
import mybank.server.beans.javascript.CommandeAddDelParticipant;
import mybank.server.beans.javascript.Ordre;
import mybank.server.beans.javascript.UserAvecDepense;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;
import mybank.server.rest.util.json.DateDeserializer;

@Path("/event")
public class EventRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			List<Event> liste = (List<Event>) AccesseurGenerique.getInstance().getListe(Event.class);
			// Traitement de la log
			// Utilitaire.loggingRest(this.getClass(), "save", "", connexionUser);
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String strid) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event aEvent = (Event) AccesseurGenerique.getInstance().get(Event.class, strid);
			if (aEvent == null)
				throw new Exception("User inconnu");
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser);
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			return Reponse.reponseKO(e);
		}
	}
	@GET
	@Path("/{idEvent}/{idUser}/toggleRole")
	@Produces(MediaType.APPLICATION_JSON)
	public Response toggleRoleUser(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("idEvent") String idEvent,@PathParam("idUser") String idUser) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			// Recuperation du lienUSerEvent
			ArrayList<LienEventUser> liste = AccesseurGenerique.getInstance().getListeFiltre(LienEventUser.class,"userId='"+idUser+"' and eventId='"+idEvent+"'" );
			if(liste.isEmpty() || liste.size()>1)
				throw new Exception("L'affectation de ce user ‡ l'event n'existe plus!!");
			LienEventUser lien = liste.get(0);
			User user = (User)AccesseurGenerique.getInstance().get(User.class, idUser);
			String text=" du rÙle de crÈateur sur l'event pour "+user.getPrenom();
			if(lien.getRoles().contains("Createur")) {
				lien.getRoles().remove("Createur");
				text = "Suppression"+text;
			} else {
				lien.getRoles().add("Createur");
				text = "Ajout"+text;
			}
			
			AccesseurGenerique.getInstance().update(lien);
			HistoriqueEvent.historise(connexionUser, lien, text, idEvent);
			return Reponse.getResponseOK(lien);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{idEvent}/{idUser}/toggleRole", idEvent+"/"+idUser+"/toggleRole", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListe(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {

		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			String clauseWhere = new String(data, "UTF-8");
			List<User> liste = (List<User>) AccesseurGenerique.getInstance().getListeFiltre(User.class, clauseWhere);

			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "getListe", data, connexionUser);
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getListe", data, connexionUser);
			return Reponse.reponseKO(e);
		}

	}

	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			User aEvent = new User();

			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser);
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "create", "", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event aEvent = mapper.readValue(new String(data, "UTF-8"), Event.class);
			aEvent.setEtat("Ouvert");
			aEvent.setRoles(null);
			// Traitement de la photo
			if (aEvent.getUrlPhoto() != null && aEvent.getUrlPhoto().length() > 50) {
				if (aEvent.getUrlPhoto().contains("==")) {
					int idx = aEvent.getUrlPhoto().indexOf("==");
					String tab0 = aEvent.getUrlPhoto().substring(0, idx);
					String tab1 = aEvent.getUrlPhoto().substring(idx + 2);
					String dataImage = tab1;
					dataImage = dataImage.substring(dataImage.indexOf(",") + 1);
					String fileName = tab0;
					FileOutputStream targetFile = new FileOutputStream(
							"../standalone/deployments/Image.war/" + fileName);
					byte[] rawImage = Base64.getDecoder().decode(dataImage);
					targetFile.write(rawImage);
					targetFile.close();
					aEvent.setUrlPhoto(fileName);
				}
			}

			AccesseurGenerique.getInstance().save(aEvent);

			// Lien avec l'utilisateur
			LienEventUser lien = new LienEventUser(connexionUser.getUser().getId(), aEvent.getId());
			ArrayList<String> roles = new ArrayList<>();
			roles.add("Createur");
			roles.add("Participant");
			lien.setRoles(roles);
			AccesseurGenerique.getInstance().save(lien);
			// Traitement de la log
			ArrayList<String> listeRole = new ArrayList<String>();
			listeRole.add("Createur");
			aEvent.setRoles(listeRole);
			HistoriqueEvent.historise(connexionUser, aEvent, "CrÈation de l'event", aEvent.getId());
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String strid,
			byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		SimpleModule module = new SimpleModule();
		module.addDeserializer(java.util.Date.class, new DateDeserializer());
		mapper.registerModule(module);
		try {
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event aEvent = mapper.readValue(new String(data, "UTF-8"), Event.class);
			aEvent.setRoles(null);
			AccesseurGenerique.getInstance().update(aEvent);
			// Traitement de la log

			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
			HistoriqueEvent.historise(connexionUser, aEvent, "Mis ‡ jour de l'event", aEvent.getId());
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
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
			// V√©rification de l'acc√®s depuis un event connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			User aEvent = mapper.readValue(new String(data, "UTF-8"), User.class);
			AccesseurGenerique.getInstance().delete(aEvent);
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
			return Reponse.getResponseOK(aEvent);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
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
			// V√©rification de l'acc√®s depuis un user connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<LienEventUser> liste = (List<LienEventUser>) AccesseurGenerique.getInstance()
					.getListeFiltre(LienEventUser.class, "eventId='" + idEvent + "'");
			
			ArrayList<UserAvecDepense> listeUserAvecDepense = extraitUserAvecDepense(idEvent, liste);

			
			// Traitement des paiements de personne ‡ personne

			// Traitement de la log
			return Reponse.getResponseOK(listeUserAvecDepense);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/users", "/" + idEvent + "/users", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	private ArrayList<UserAvecDepense> extraitUserAvecDepense(String idEvent, List<LienEventUser> liste)
			throws Exception {
		
		ArrayList<UserAvecDepense> listeUserAvecDepense = new ArrayList<>();
		// Recup√©ration des Event
		for (LienEventUser lien : liste) {
			User user = (User) AccesseurGenerique.getInstance().get(User.class, lien.getUserId());
			UserAvecDepense userAvecDepense = new UserAvecDepense(user);
			
			
			
			if(lien.getRoles()==null || lien.getRoles().isEmpty()) {
				
				lien.setRoles(new ArrayList<>());
				lien.getRoles().add("Participant");
			}
			
			userAvecDepense.setRoles(lien.getRoles());
			listeUserAvecDepense.add(userAvecDepense);
		}

		// Je recupere toutes les depenses de cet event
		List<Depense> listeDepense = (List<Depense>) AccesseurGenerique.getInstance().getListeFiltre(Depense.class,
				"idEvent='" + idEvent + "'");
		double montantEvent = 0;
		for (Depense depense : listeDepense) {
			montantEvent += depense.getMontant();
		}

		List<Mouvement> listeMouvement = (List<Mouvement>) AccesseurGenerique.getInstance()
				.getListeFiltre(Mouvement.class, "idEvent='" + idEvent + "' and etat='RÈalisÈ'");

		
		for (UserAvecDepense userAvecDepense : listeUserAvecDepense) {
			double montantPaye = 0;
			double montantDu = 0;
			for (Depense depense : listeDepense) {
				if (depense.getIdPayeur().equals(userAvecDepense.getUser().getId()))
					montantPaye += depense.getMontant();
			}
			for (Mouvement mouvement : listeMouvement) {
				if (mouvement.getIdEmetteur().equals(userAvecDepense.getUser().getId()))
					montantPaye += mouvement.getMontant();
				if (mouvement.getIdDestinataire().equals(userAvecDepense.getUser().getId()))
					montantPaye -= mouvement.getMontant();
			}

			montantDu = montantEvent / listeUserAvecDepense.size() - montantPaye;
			
			userAvecDepense.setaPaye(montantPaye);
			userAvecDepense.setDoit(montantDu);
			
		}
		return listeUserAvecDepense;
	}

	@GET
	@Path("/{id}/bilan")
	@Produces(MediaType.APPLICATION_JSON)
	public Response bilan(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un user connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			ArrayList<Mouvement> listeMouvement = bilan(idEvent);
			// Qui a payÈ le plus
			// Traitement de la log
			return Reponse.getResponseOK(listeMouvement);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/bilan", "/" + idEvent + "/bilan", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}/validebilan")
	@Produces(MediaType.APPLICATION_JSON)
	public Response valideBilan(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un user connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			Event event = (Event) AccesseurGenerique.getInstance().get(Event.class, idEvent);
			if (event.getEtat().equals("En cours de solde")) {
				throw new Exception("L'event a ÈtÈ dÈj‡ validÈ par un autre membre!");
			}
			event.setEtat("En cours de solde");
			AccesseurGenerique.getInstance().update(event);
			User emetteur = connexionUser.getUser();
			// On cherche les liens EventUset
			ArrayList<Mouvement> listeMouvement = bilan(idEvent);
			for (Mouvement mouvement : listeMouvement) {
				mouvement.setEtat("Transmis");
				mouvement.setDate(new Date());
				mouvement.setIdEvent(idEvent);
				mouvement.setCommentaire("Demande de paiement pour " + event.getLibelle());
				AccesseurGenerique.getInstance().save(mouvement);
				// ENvoie d'un message
				Message aMessage = new Message(
						"Demande de paiement de " + new DecimalFormat("0.00").format(mouvement.getMontant())
								+ "Ä pour l'event " + event.getLibelle(),
						"Bonjour, tu me dois " + new DecimalFormat("0.00").format(mouvement.getMontant())
								+ "Ä pour l'event " + event.getLibelle() + " du "
								+ Utilitaire.FORMAT_DATE_COURT.format(event.getDate())
								+ ". Tu trouveras la demande dans ton carnet d'ordre! Merci d'avance.",
						emetteur);
				User user = (User) AccesseurGenerique.getInstance().get(User.class, mouvement.getIdEmetteur());
				aMessage.setDestinataire(user);

				User userDest = (User) AccesseurGenerique.getInstance().get(User.class, mouvement.getIdDestinataire());
				Ordre ordre = new Ordre(mouvement, userDest, event);

				aMessage.setMessageCache(Reponse.getJson(ordre));
				AccesseurGenerique.getInstance().save(aMessage);

			}
			HistoriqueEvent.historise(connexionUser, event, "Mise en bilan de l'event", event.getId());
			return Reponse.getResponseOK(listeMouvement);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/bilan", "/" + idEvent + "/bilan", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	public static ArrayList<Mouvement> bilan(String idEvent) throws Exception {
		List<LienEventUser> liste = (List<LienEventUser>) AccesseurGenerique.getInstance()
				.getListeFiltre(LienEventUser.class, "eventId='" + idEvent + "'");
		ArrayList<User> listeUser = new ArrayList<>();
		// Recup√©ration des Event
		for (LienEventUser lien : liste) {
			listeUser.add((User) AccesseurGenerique.getInstance().get(User.class, lien.getUserId()));
		}

		// Je recupere toutes les depenses de cet event
		List<Depense> listeDepense = (List<Depense>) AccesseurGenerique.getInstance().getListeFiltre(Depense.class,
				"idEvent='" + idEvent + "'");
		double montantEvent = 0;
		for (Depense depense : listeDepense) {
			montantEvent += depense.getMontant();
		}
		double montantCible = montantEvent / listeUser.size();
		List<Mouvement> listeMouvementRealise = (List<Mouvement>) AccesseurGenerique.getInstance()
				.getListeFiltre(Mouvement.class, "idEvent='" + idEvent + "' and etat='RÈalisÈ'");

		ArrayList<UserAvecDepense> listeUserAvecDepense = new ArrayList<>();
		for (User user : listeUser) {
			double montantPaye = 0;
			double montantDu = 0;
			for (Depense depense : listeDepense) {
				if (depense.getIdPayeur().equals(user.getId()))
					montantPaye += depense.getMontant();
			}
			for (Mouvement mouvement : listeMouvementRealise) {
				if (mouvement.getIdEmetteur().equals(user.getId()))
					montantPaye += mouvement.getMontant();
				if (mouvement.getIdDestinataire().equals(user.getId()))
					montantPaye -= mouvement.getMontant();
			}

			montantDu = montantEvent / listeUser.size() - montantPaye;
			UserAvecDepense userAvecDepense = new UserAvecDepense(user);
			userAvecDepense.setaPaye(montantPaye);
			userAvecDepense.setDoit(montantDu);
			listeUserAvecDepense.add(userAvecDepense);
		}
		ArrayList<Mouvement> listeMouvement = new ArrayList<>();
		arrange(listeUserAvecDepense, montantCible, listeMouvement);

		// Cumul
		ArrayList<Mouvement> listeMouvementCumul = new ArrayList<>();
		HashMap<String, ArrayList<Mouvement>> hash = new HashMap<>();
		for (Mouvement mvt : listeMouvement) {
			String cle = mvt.getIdEmetteur() + "-" + mvt.getIdDestinataire();
			if (!hash.containsKey(cle))
				hash.put(cle, new ArrayList<>());
			ArrayList<Mouvement> listeMvt = hash.get(cle);
			listeMvt.add(mvt);
		}

		for (String key : hash.keySet()) {
			ArrayList<Mouvement> listeMvt = hash.get(key);
			if (listeMvt.isEmpty())
				continue;
			if (listeMvt.size() == 1)
				listeMouvementCumul.add(listeMvt.get(0));
			else {
				Mouvement mvt0 = listeMvt.get(0);
				for (int i = 1; i < listeMvt.size(); i++) {
					mvt0.setMontant(mvt0.getMontant() + listeMvt.get(i).getMontant());
				}
				listeMouvementCumul.add(mvt0);
			}
		}

		return listeMouvementCumul;
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
		// J'ai pas rÈussi ‡ dispatcher en entier...
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
			// V√©rification de l'acc√®s depuis un user connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Depense> liste = (List<Depense>) AccesseurGenerique.getInstance().getListeFiltre(Depense.class,
					"idEvent='" + idEvent + "'");
			// Traitement de la log
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/depenses", "/" + idEvent + "/depenses", connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}/historique")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHistoriqueDeEventId(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un user connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<HistoriqueEvent> listeComplete = (List<HistoriqueEvent>) AccesseurGenerique.getInstance()
					.getListe(HistoriqueEvent.class);
			List<HistoriqueEvent> liste = (List<HistoriqueEvent>) AccesseurGenerique.getInstance()
					.getListeFiltre(HistoriqueEvent.class, "idEvent='" + idEvent + "'");
			// Traitement de la log
			TreeSet<HistoriqueEvent> tree = new TreeSet<>();
			tree.addAll(liste);
			return Reponse.getResponseOK(tree);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/historique", "/" + idEvent + "/historique",
					connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@GET
	@Path("/{id}/mouvements")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMouvementsDeEventId(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			@PathParam("id") String idEvent) {
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un user connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			// On cherche les liens EventUset
			List<Mouvement> liste = (List<Mouvement>) AccesseurGenerique.getInstance().getListeFiltre(Mouvement.class,
					"idEvent='" + idEvent + "'");
			// Traitement de la log
			return Reponse.getResponseOK(liste);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "/{id}/mouvements", "/" + idEvent + "/mouvements",
					connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Path("/supprimeUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimeUser(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un lienEventUser connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			LienEventUser aLienEventUser = mapper.readValue(new String(data, "UTF-8"), LienEventUser.class);
			AccesseurGenerique.getInstance().deleteWhere(LienEventUser.class,
					"userId='" + aLienEventUser.getUserId() + "' and eventId='" + aLienEventUser.getEventId() + "'");
			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
			return Reponse.getResponseOK(aLienEventUser);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
			return Reponse.reponseKO(e);
		}
	}

	@POST
	@Path("/ajoutSuppressionUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ajoutSuppressionUser(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		ConnexionUser connexionUser = null;
		try {
			// V√©rification de l'acc√®s depuis un lienEventUser connect√©
			connexionUser = ConnexionUser.verificationConnexionUser(headers);

			ArrayList<CommandeAddDelParticipant> listeAddDel = mapper.readValue(new String(data, "UTF-8"),
					new TypeReference<List<CommandeAddDelParticipant>>() {
					});

			/*
			 * Lecture prÈalable de l'event
			 */
			String idEvent = listeAddDel.get(0).getLienEventUser().getEventId();
			Event aEvent = (Event) AccesseurGenerique.getInstance().get(Event.class, idEvent);
			List<LienEventUser> liste = (List<LienEventUser>) AccesseurGenerique.getInstance()
					.getListeFiltre(LienEventUser.class, "eventId='" + idEvent + "'");
			
			// Recup√©ration des Event

			// Je commence par les DEL pour Èrifier qu'iln'y a pas de dÈpense liÈ!

			for (CommandeAddDelParticipant commandeAddDelParticipant : listeAddDel) {
				LienEventUser aLienEventUser = commandeAddDelParticipant.getLienEventUser();
				if (commandeAddDelParticipant.getCommande().equalsIgnoreCase("DEL")) {
					// Check est-ce que qq ne l'a pas dÈj‡ fait?
					boolean present = false;
					for (LienEventUser lien : liste) {
						if (lien.getEventId().equals(aLienEventUser.getEventId())
								&& lien.getUserId().equals(aLienEventUser.getUserId())) {
							aLienEventUser = lien;
							present = true;
							break;
						}
					}

					// Recherche de mouvement ou de dÈpense pour ce user et cet event
					List<Depense> listeDepense = AccesseurGenerique.getInstance().getListeFiltre(Depense.class,
							"idEvent='" + idEvent + "' and idPayeur='" + aLienEventUser.getUserId() + "'");
					if (!listeDepense.isEmpty()) {
						User userRecherche = (User) AccesseurGenerique.getInstance().get(User.class,
								aLienEventUser.getUserId());
						throw new Exception("Impossible de retirer le user " + userRecherche.getNom() + " "
								+ userRecherche.getPrenom() + " car il a des dÈpenses sur l'event");
					}
					List<Mouvement> listeMouvement = AccesseurGenerique.getInstance().getListeFiltre(Mouvement.class,
							"idEvent='" + idEvent + "' and idEmetteur='" + aLienEventUser.getUserId() + "'");
					if (!listeMouvement.isEmpty()) {
						User userRecherche = (User) AccesseurGenerique.getInstance().get(User.class,
								aLienEventUser.getUserId());
						throw new Exception("Impossible de retirer le user " + userRecherche.getNom() + " "
								+ userRecherche.getPrenom() + " car il a reÁu des paiements sur l'event");
					}
					listeMouvement = AccesseurGenerique.getInstance().getListeFiltre(Mouvement.class,
							"idEvent='" + idEvent + "' and idDestinataire='" + aLienEventUser.getUserId() + "'");
					if (!listeMouvement.isEmpty()) {
						User userRecherche = (User) AccesseurGenerique.getInstance().get(User.class,
								aLienEventUser.getUserId());
						throw new Exception("Impossible de retirer le user " + userRecherche.getNom() + " "
								+ userRecherche.getPrenom() + " car il a effectuer des paiements sur l'event");
					}

					if (present)
						AccesseurGenerique.getInstance().deleteWhere(LienEventUser.class, "userId='"
								+ aLienEventUser.getUserId() + "' and eventId='" + aLienEventUser.getEventId() + "'");
					for (int i = 0; i < liste.size(); i++) {
						LienEventUser aLienEventUserExistant = liste.get(i);
						if (aLienEventUser.getUserId().equals(aLienEventUserExistant.getUserId()))
							liste.remove(i);
					}
				}

			}

			for (CommandeAddDelParticipant commandeAddDelParticipant : listeAddDel) {
				LienEventUser aLienEventUser = commandeAddDelParticipant.getLienEventUser();
				if (commandeAddDelParticipant.getCommande().equalsIgnoreCase("ADD")) {
					// Check est-ce que qq ne l'a pas dÈj‡ fait?
					boolean dejaLa = false;
					for (LienEventUser lien : liste) {
						if (lien.getEventId().equals(aLienEventUser.getEventId())
								&& lien.getUserId().equals(aLienEventUser.getUserId())) {
							aLienEventUser = lien;
							dejaLa = true;
							break;
						}
					}
					if (!dejaLa) {
						ArrayList<String> roles = new ArrayList<>();
						roles.add("Participant");
						aLienEventUser.setRoles(roles);
						AccesseurGenerique.getInstance().save(aLienEventUser);
					}
					liste.add(aLienEventUser);

				}

			}
		

			ArrayList<UserAvecDepense> listeUserAvecDepense = extraitUserAvecDepense(idEvent, liste);

			for (CommandeAddDelParticipant commandeAddDelParticipant : listeAddDel) {
				HistoriqueEvent.historise(connexionUser, commandeAddDelParticipant, "Ajout/Suppression de participant",
						idEvent);
			}

			// Traitement de la log
			return Reponse.getResponseOK(listeUserAvecDepense);

		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
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
