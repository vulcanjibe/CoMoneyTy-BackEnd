package mybank.server.rest;

import java.util.Date;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import mybank.server.beans.Message;
import mybank.server.beans.Message;
import mybank.server.beans.Relation;
import mybank.server.beans.User;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;
import mybank.server.rest.util.json.DateDeserializer;
@Path("/message")
public class MessageRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un message connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<Message> liste = (List<Message>) AccesseurGenerique.getInstance().getListe(Message.class);
            // Traitement de la log
      //      Utilitaire.loggingRest(this.getClass(), "save", "", connexionUser);
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
            // Vérification de l'accès depuis un message connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Message aMessage= (Message) AccesseurGenerique.getInstance().get(Message.class, strid);
            if(aMessage==null)
                throw new Exception("User inconnu");
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser);
            return Reponse.getResponseOK(aMessage);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
            return Reponse.reponseKO(e);
        }
    }

  

    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListe(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {

        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un message connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            String clauseWhere =new String(data, "UTF-8");
            List<User> liste = (List<User>) AccesseurGenerique.getInstance().getListeFiltre(User.class,clauseWhere );

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getListe", data, connexionUser);
            return Reponse.getResponseOK(liste);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getListe", data, connexionUser);
            return Reponse.reponseKO(e);
        }

    }

  
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializer());
        mapper.registerModule(module);
   //     mapper.setDateFormat(Utilitaire.FORMAT_DATE_COURT);
        
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un message connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Message aMessage= mapper.readValue(new String(data, "UTF-8"), Message.class);
            if(aMessage.getDate()==null)
            	aMessage.setDate(new Date());
           	AccesseurGenerique.getInstance().save(aMessage);

            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aMessage);
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
    public Response update(@Context HttpHeaders headers, @Context UriInfo uriInfo,  @PathParam("id") String strid, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializer());
        mapper.registerModule(module);
   //     mapper.setDateFormat(Utilitaire.FORMAT_DATE_COURT);
        
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un message connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Message aMessage= mapper.readValue(new String(data, "UTF-8"), Message.class);
            if(aMessage.getDate()==null)
            	aMessage.setDate(new Date());
           	AccesseurGenerique.getInstance().update(aMessage);

            Utilitaire.loggingRest(this.getClass(), "update", data, connexionUser);
           return Reponse.getResponseOK(aMessage);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "update", data, connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Context HttpHeaders headers, @Context UriInfo uriInfo,  @PathParam("id") String strid) {
        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un message connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Message aMessage = new Message();
            aMessage.setId(strid);
            AccesseurGenerique.getInstance().delete(aMessage);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "delete", "message/"+strid, connexionUser);
            return Reponse.getResponseOK(aMessage);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "delete","message/"+strid, connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    

   

}
