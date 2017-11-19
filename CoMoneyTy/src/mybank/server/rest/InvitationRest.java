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

import mybank.server.beans.Invitation;
import mybank.server.beans.Message;
import mybank.server.beans.Relation;
import mybank.server.beans.User;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;
import mybank.server.rest.util.json.DateDeserializer;
@Path("/invitation")
public class InvitationRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un invitation connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<Invitation> liste = (List<Invitation>) AccesseurGenerique.getInstance().getListe(Invitation.class);
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
            // Vérification de l'accès depuis un invitation connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Invitation aInvitation= (Invitation) AccesseurGenerique.getInstance().get(Invitation.class, strid);
            if(aInvitation==null)
                throw new Exception("User inconnu");
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser);
            return Reponse.getResponseOK(aInvitation);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getById", strid, connexionUser);
            return Reponse.reponseKO(e);
        }
    }

    @GET
    @Path("/{id}/confirm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirm(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String strid) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un invitation connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
          //  strid=strid.substring(strid.indexOf("=")+1);
            Invitation aInvitation= (Invitation) AccesseurGenerique.getInstance().get(Invitation.class, strid);
            aInvitation.setEtatReponse("Confirmee");
            AccesseurGenerique.getInstance().update(aInvitation);
            // Je met en relation les 2 users
            String idUser1 = aInvitation.getIdUser();
            String idUser2 = connexionUser.getUser().getId();
            
            Relation aRelation1 = new Relation(idUser1, idUser2);
            AccesseurGenerique.getInstance().save(aRelation1);
            Relation aRelation2 = new Relation(idUser2, idUser1);
            AccesseurGenerique.getInstance().save(aRelation2);
            
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser);
            return Reponse.getResponseOK(aInvitation);
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
            // Vérification de l'accès depuis un invitation connecté
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
            // Vérification de l'accès depuis un invitation connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Invitation aInvitation= mapper.readValue(new String(data, "UTF-8"), Invitation.class);
            if(aInvitation.getDate()==null)
            	aInvitation.setDate(new Date());
            String phone = aInvitation.getContact().getPhoneNumber(); 
			phone = phone.replaceAll(" ", "");
			phone = phone.replaceAll("\\\\.", "");
			phone = phone.substring(phone.length() - 9);
			phone = "0" + phone;
            aInvitation.getContact().setPhoneNumber(phone);
            	
           	AccesseurGenerique.getInstance().save(aInvitation);
            
           	// J'envoie un message d'invitation => Non car je connais pas son id!
//           	Message message = new Message("Invitation � rejoindre "+connexionUser.getUser().getPrenom(), connexionUser.getUser(), aInvitation.getContact().getId());
//           	AccesseurGenerique.getInstance().save(message);
           	
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aInvitation);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
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
            // Vérification de l'accès depuis un invitation connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Invitation aInvitation = new Invitation();
            aInvitation.setId(strid);
            AccesseurGenerique.getInstance().delete(aInvitation);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "delete", "invitation/"+strid, connexionUser);
            return Reponse.getResponseOK(aInvitation);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "delete","invitation/"+strid, connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    

   

}
