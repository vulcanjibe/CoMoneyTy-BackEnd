package comoneyty.server.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import comoneyty.server.beans.Credential;
import comoneyty.server.beans.User;
import comoneyty.server.beans.javascript.SimpleCredential;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.connexion.UserStore;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.PasswordEncoder;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/login")
public class LoginRest {

	private static final int NOMBRE_TENTATIVE = 3;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = "Login", summary = "Login API pour récupérer un token d'identification", description = "Permet d'obtenir un token d'identification pour la connexion aux APIs CoMoneyTy", responses = {
			@ApiResponse(responseCode = "200", description = "Le token d'identification", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetourAppel.class))),
			@ApiResponse(responseCode = "500", description = "Une erreur est survenue", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetourAppel.class))) })
	public RetourAppel login(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Context HttpServletRequest req,
			@Parameter(description = "Les credentials de connexion", required = true) SimpleCredential simplecredential)
			throws Exception {
		ConnexionUser connexionUser = null;
		try {
			Credential credential = new Credential();
			credential.setLogin(simplecredential.getLogin());
			credential.setPassword(simplecredential.getPassword());

			if (credential.getLogin() == null || credential.getPassword() == null)
				throw new Exception("Il faut préciser le login et le password");

			// On vérifie si le user est bien reconnu via son login
			// Si on ne le trouve pas on recherche par mail et par téléphone
			List<Credential> list = new Accesseur<Credential>().getListeFiltre(Credential.class,
					"login='" + credential.getLogin() + "'");

			if (list.isEmpty()) {
				list = new Accesseur<Credential>().getListeFiltre(Credential.class,
						"email='" + credential.getLogin() + "'");
				if (credential.getLogin().length() >= 10) {
					if (list.isEmpty()) {
						String phone = credential.getLogin();
						phone = phone.replaceAll(" ", "");
						phone = phone.replaceAll("\\\\.", "");
						phone = phone.substring(phone.length() - 9);
						phone = "0" + phone;
						list = new Accesseur<Credential>().getListeFiltre(Credential.class, "phone='" + phone + "'");
						if (list.isEmpty())
							throw new Exception("Le user " + credential.getLogin() + " n'existe pas");
					}
				} else
					throw new Exception("Le user " + credential.getLogin() + " n'existe pas");
			}

			// Si on arrive ici, c'est qu'on a bien trouvé une correspondance
			// via le login, le mail ou le téléphone
			Credential cred = list.get(0);

			// On check si le compte est bloqué ou non
			if (cred.getNbTentative() == 0)
				throw new Exception(
						"Le compte " + credential.getLogin() + " a été bloqué suite à plusieurs connexions erronnées!");

			boolean trouve = false;

			// On vérifie le password
			String storedPassword = cred.getPassword();

			if (PasswordEncoder.validatePassword(credential.getPassword(), storedPassword)) {
				trouve = true;
				credential = cred;
			}

			/*
			 * A virer en production Utiliser pour le "swap" d'utilisateur via l'IHM
			 */
			if (!trouve && credential.getPassword().equalsIgnoreCase("BRUTEFORCE!!!")) {
				trouve = true;
				credential = cred;
			}
			if (!trouve) {
				// J'ai bien le user mais le password est erroné
				// On enregistre la tentative dans les connexions suspectes
				cred.setNbTentative(cred.getNbTentative() - 1);
				new Accesseur<Credential>().update(cred);

				// Temporisation pour perturber les robots
				Thread.sleep((NOMBRE_TENTATIVE - cred.getNbTentative()) * 500);
				throw new Exception("Mot de passe incorrect - Tentative restante : " + cred.getNbTentative());
			}

			if (cred.getNbTentative() != 3) {
				// On remet à jour le compteur d'essai
				cred.setNbTentative(NOMBRE_TENTATIVE);
				new Accesseur<Credential>().update(cred);
			}
			// Récuperation du User
			User user = new Accesseur<User>().get(User.class, credential.getIdUser());
			
			String key = UUID.randomUUID().toString().toUpperCase();
			
			// Vérification qu'il n'est pas déjà connecté par ailleurs
			ArrayList<UserStore> listeUs = new Accesseur<UserStore>().getListeFiltre(UserStore.class,
					"idUser='" + credential.getIdUser() + "'");
			if (!listeUs.isEmpty()) {
				UserStore us = listeUs.get(0);
				key=us.getId();
			}
			

			/* Sauvegarde du user dans la LISTE_USER */
			connexionUser = ConnexionUser.connect(key, user, req.getRemoteAddr());

			// Traitement de la log
			Utilitaire.loggingRest(this.getClass(), "login", "", connexionUser);
			return new RetourAppel(200, "Connexion effectuée - Token Bearer = " + key);
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création event", connexionUser);
			throw e;
		}
	}

}
