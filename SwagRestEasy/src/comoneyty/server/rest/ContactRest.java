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

import comoneyty.server.beans.Contact;
import comoneyty.server.beans.Objetlie;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.RetourAppel;
import comoneyty.server.rest.util.Utilitaire;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/contact")
public class ContactRest {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Contact",summary="Lecture d'un contact via son id",
	description="Permet de récuperer un contact à partir de son id",
	responses= { @ApiResponse(responseCode="200",description="Le contact demandé",content=@Content(mediaType="application/json",schema=@Schema(implementation=Contact.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Contact getById(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'id contact", required = true) @PathParam("id") String strid)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			
			// Récupération de l'contact en base
			Contact aContact = (Contact) new Accesseur<Contact>().get(Contact.class, strid);
			if (aContact == null)
				throw new Exception("Contact inconnu");
			return aContact;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
			throw e;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Contact",summary="Sauvegarde d'un nouvel contact",
	description="Permet de sauvegarder un contact. En retour, l'opération renvoie l'contact avec son nouvel id",
	responses= { @ApiResponse(responseCode="200",description="Le contact sauvegardé avec son id",content=@Content(mediaType="application/json",schema=@Schema(implementation=Contact.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public Contact save(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'contact à sauvegarder (Sans id renseigné)", required = true) Contact contact)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(contact.getId()!=null)
				throw new Exception("Impossible de créer un contact qui a déjà  un ID!");
			
			// Sauvegarde de l'contact en base
			new Accesseur<Contact>().save(contact);
			
			Utilitaire.loggingRest(this.getClass(), "save", contact, connexionUser);
			return contact;
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "save", "Création contact", connexionUser);
			throw e;
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Contact",summary="Sauvegarde d'un contact déjà enregistré",
	description="Permet de sauvegarder les modifications sur un contact déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel update(@Context HttpHeaders headers, @Context UriInfo uriInfo, @Parameter(description = "L'contact à enregistrer (avec id renseigné)", required = true) Contact contact)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(contact.getId()==null)
				throw new Exception("Impossible d'updater un contact sans ID!");
			
			// Update de l'contact en base
			new Accesseur<Contact>().update(contact);
			Utilitaire.loggingRest(this.getClass(), "update", contact, connexionUser);
			return new RetourAppel(0, "Le contact "+contact.getId()+" a été mis à  jour");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "upadte", "Update contact", connexionUser);
			throw e;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@io.swagger.v3.oas.annotations.Operation(tags="Contact",summary="Suppression d'un contact déjà enregistré",
	description="Permet de supprimer un contact déjà enregistré.",
	responses= { @ApiResponse(responseCode="200",description="Code retour 0",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class))),
			@ApiResponse(responseCode="500",description="Une erreur est survenue",content=@Content(mediaType="application/json",schema=@Schema(implementation=RetourAppel.class)))
	})
	public RetourAppel delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@Parameter(description = "L'contact à supprimer (avec id renseigné)", required = true) Contact contact)  throws Exception {
		ConnexionUser connexionUser = null;
		try {
			// Vérification des droits utilisateurs
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			if(contact.getId()==null)
				throw new Exception("Impossible de supprimer un contact sans ID!");
			
			// Suppression de l'contact en base
			new Accesseur<Contact>().delete(contact);
			return new RetourAppel(0, "L'contact "+contact.getId()+" a été supprimé");
		} catch (Exception e) {
			// Traitement de l'exception
			Utilitaire.exceptionRest(e, this.getClass(), "delete", "Suppression contact", connexionUser);
			throw e;
		}
	}

}
