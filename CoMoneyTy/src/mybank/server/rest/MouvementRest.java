package mybank.server.rest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import mybank.server.beans.HistoriqueEvent;
import mybank.server.beans.LienEventUser;
import mybank.server.beans.Mouvement;
import mybank.server.beans.Operation;
import mybank.server.beans.User;
import mybank.server.beans.javascript.Ordre;
import mybank.server.beans.type.TypeOperation;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;
import mybank.server.rest.util.json.DateDeserializer;
@Path("/mouvement")
public class MouvementRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<Mouvement> liste = (List<Mouvement>) AccesseurGenerique.getInstance().getListe(Mouvement.class);
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
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Mouvement aMouvement= (Mouvement) AccesseurGenerique.getInstance().get(Mouvement.class, strid);
            if(aMouvement==null)
                throw new Exception("User inconnu");
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser);
            return Reponse.getResponseOK(aMouvement);
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
            // Vérification de l'accès depuis un mouvement connecté
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

    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            User aMouvement= new User();

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser);
            return Reponse.getResponseOK(aMouvement);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "create", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save1(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializer());
        mapper.registerModule(module);
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Mouvement aMouvement= mapper.readValue(new String(data, "UTF-8"), Mouvement.class);
            if(aMouvement.getIdDestinataire()==null)
            {
            	//Je le partage � tous
            	// R�cup�ration de tous les participants
                List<LienEventUser> liste = (List<LienEventUser>) AccesseurGenerique.getInstance().getListeFiltre(LienEventUser.class, "eventId='"+aMouvement.getIdEvent()+"'");
                double montantPartage = aMouvement.getMontant()/liste.size(); 
               // Recupération des Event
                for(LienEventUser lien : liste) {
                	Mouvement newMouvement = new Mouvement();
                	newMouvement.setCommentaire(aMouvement.getCommentaire());
                	newMouvement.setIdDestinataire(lien.getUserId());
                	newMouvement.setIdEmetteur(aMouvement.getIdEmetteur());
                	newMouvement.setIdEvent(aMouvement.getIdEvent());
                	newMouvement.setMontant(montantPartage);
                	newMouvement.setDate(aMouvement.getDate());
                	AccesseurGenerique.getInstance().save(newMouvement);
                }
            } else {
            	AccesseurGenerique.getInstance().save(aMouvement);
            }
            
        
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aMouvement);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
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
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Mouvement aMouvement= mapper.readValue(new String(data, "UTF-8"), Mouvement.class);
         
           	AccesseurGenerique.getInstance().save(aMouvement);
            
           	HistoriqueEvent.historise(connexionUser, aMouvement, "Mise en bilan de l'event",aMouvement.getIdEvent());
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aMouvement);
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
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Mouvement aMouvement= mapper.readValue(new String(data, "UTF-8"), Mouvement.class);
            AccesseurGenerique.getInstance().update(aMouvement);
            // Traitement de la log
            HistoriqueEvent.historise(connexionUser, aMouvement, "Mise � jour du mouvement",aMouvement.getIdEvent());
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aMouvement);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
            return Reponse.reponseKO(e);
        }
    }

    @POST
    @Path("/reglementParVirement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reglementParVirement(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializer());
        mapper.registerModule(module);
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Ordre aOrdre = mapper.readValue(new String(data, "UTF-8"), Ordre.class);
            Mouvement mouvement = aOrdre.getMouvement();
            mouvement.setEtat("R�alis�");
            AccesseurGenerique.getInstance().update(mouvement);
            
            // Cr�ation de l'op�ration
            User emetteur = connexionUser.getUser();
            User destinataire = aOrdre.getEmetteur();
            Operation aOperationEmis = new Operation(emetteur.getId(), new Date(), "Virement �mis pour "+aOrdre.getEvent().getLibelle(), -mouvement.getMontant());
            aOperationEmis.setIbanDestinataire(destinataire.getIban());
            aOperationEmis.setIbanEmetteur(emetteur.getIban());
            aOperationEmis.setTypeOperation(new TypeOperation(1, "Virement �mis"));
  
            Operation aOperationRecu = new Operation(destinataire.getId(), new Date(), "Virement recu pour "+aOrdre.getEvent().getLibelle(), mouvement.getMontant());
            aOperationRecu.setIbanDestinataire(destinataire.getIban());
            aOperationRecu.setIbanEmetteur(emetteur.getIban());
            aOperationEmis.setTypeOperation(new TypeOperation(1, "Virement recu"));
  
            AccesseurGenerique.getInstance().save(aOperationRecu);
            AccesseurGenerique.getInstance().save(aOperationEmis);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "reglementParVirement", data, connexionUser);
           return Reponse.getResponseOK(mouvement);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "reglementParVirement", data, connexionUser);
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
            // Vérification de l'accès depuis un mouvement connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            User aMouvement= mapper.readValue(new String(data, "UTF-8"), User.class);
            AccesseurGenerique.getInstance().delete(aMouvement);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
            return Reponse.getResponseOK(aMouvement);
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
        byte[] buffer = new byte[uploadedInputStream.available()];
        //File targetFile = new File("src/main/resources/targetFile.tmp");
        File targetFile = new File("../standalone/deployments/Image.war/mouvement/"+fileDetails.getFileName()); 
        java.nio.file.Files.copy(
        		uploadedInputStream, 
          targetFile.toPath(), 
          StandardCopyOption.REPLACE_EXISTING);
       
        uploadedInputStream.close();
        return Response.ok().build();
    }
}
