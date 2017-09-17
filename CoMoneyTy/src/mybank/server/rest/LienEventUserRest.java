package mybank.server.rest;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.Event;
import mybank.server.beans.LienEventUser;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;

@Path("/lienEventUser")
public class LienEventUserRest {

    static String ENTITY = "lienEventUser";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un lienEventUser connecté
         //   connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<LienEventUser> liste = (List<LienEventUser>) Accesseur.getListe(LienEventUser.class);
            // Traitement de la log
      //      Utilitaire.loggingRest(this.getClass(), "save", "", connexionUser.getUser());
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
            // Vérification de l'accès depuis un lienEventUser connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienEventUser aLienEventUser = (LienEventUser) Accesseur.get(LienEventUser.class, strid);
            if(aLienEventUser==null)
                throw new Exception("LienEventUser inconnu");
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser.getUser());
            return Reponse.getResponseOK(aLienEventUser);
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
            // Vérification de l'accès depuis un lienEventUser connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            String clauseWhere =new String(data, "UTF-8");
            List<LienEventUser> liste = (List<LienEventUser>) Accesseur.getListeFiltre(LienEventUser.class, clauseWhere);

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
            // Vérification de l'accès depuis un lienEventUser connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienEventUser aLienEventUser = new LienEventUser();

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser.getUser());
            return Reponse.getResponseOK(aLienEventUser);
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
            LienEventUser aLienEventUSer= mapper.readValue(new String(data, "UTF-8"), LienEventUser.class);
            Accesseur.save(aLienEventUSer);
            
       
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
           return Reponse.getResponseOK(aLienEventUSer);
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
            LienEventUser aEvent= mapper.readValue(new String(data, "UTF-8"), LienEventUser.class);
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

    
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un lienEventUser connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            LienEventUser aLienEventUser = mapper.readValue(new String(data, "UTF-8"), LienEventUser.class);
            Accesseur.delete(aLienEventUser);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
            return Reponse.getResponseOK(aLienEventUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
    
    
    // METHODE COMPLEMENTAIRE
}
