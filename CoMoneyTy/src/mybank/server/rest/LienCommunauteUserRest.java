package mybank.server.rest;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.LienCommunauteUser;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;

@Path("/liencommunauteuser")
public class LienCommunauteUserRest {

    static String ENTITY = "liencommunauteuser";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<LienCommunauteUser> liste = (List<LienCommunauteUser>)Accesseur.getListe(LienCommunauteUser.class);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", "", connexionUser.getUser());
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
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienCommunauteUser aLienCommunauteUser = (LienCommunauteUser)Accesseur.get(LienCommunauteUser.class, strid);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser.getUser());
            return Reponse.getResponseOK(aLienCommunauteUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListe(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {

        ObjectMapper mapper = new ObjectMapper();;
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
         //   Filtre filtre = mapper.readValue(new String(data, "UTF-8"), Filtre.class);
           List<LienCommunauteUser> liste = (List<LienCommunauteUser>)Accesseur.getListeFiltre(LienCommunauteUser.class, "A CODER");

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
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienCommunauteUser aLienCommunauteUser = new LienCommunauteUser();

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser.getUser());
            return Reponse.getResponseOK(aLienCommunauteUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "create", "", connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }

    @PUT
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienCommunauteUser aLienCommunauteUser = mapper.readValue(new String(data, "UTF-8"), LienCommunauteUser.class);
            Accesseur.save(aLienCommunauteUser);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
           return Reponse.getResponseOK(aLienCommunauteUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,@PathParam("id") String strid) {
        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienCommunauteUser aLienCommunauteUser = (LienCommunauteUser)Accesseur.get(LienCommunauteUser.class, strid);
            Accesseur.delete(aLienCommunauteUser);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "delete", strid, connexionUser.getUser());
            return Reponse.getResponseOK(aLienCommunauteUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "delete", strid, connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
}
