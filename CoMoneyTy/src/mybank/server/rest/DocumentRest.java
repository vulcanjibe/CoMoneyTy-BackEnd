package mybank.server.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
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
import com.fasterxml.jackson.databind.module.SimpleModule;

import mybank.server.beans.LienEventUser;
import mybank.server.beans.Operation;
import mybank.server.beans.Document;
import mybank.server.beans.HistoriqueEvent;
import mybank.server.beans.User;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;
import mybank.server.rest.util.json.DateDeserializer;
@Path("/document")
public class DocumentRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un document connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<Document> liste = (List<Document>) AccesseurGenerique.getInstance().getListe(Document.class);
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
            // Vérification de l'accès depuis un document connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Document aDocument= (Document) AccesseurGenerique.getInstance().get(Document.class, strid);
            if(aDocument==null)
                throw new Exception("User inconnu");
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser);
            return Reponse.getResponseOK(aDocument);
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
            // Vérification de l'accès depuis un document connecté
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
            // Vérification de l'accès depuis un document connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            User aDocument= new User();

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser);
            return Reponse.getResponseOK(aDocument);
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
    public Response save(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializer());
        mapper.registerModule(module);
   //     mapper.setDateFormat(Utilitaire.FORMAT_DATE_COURT);
        
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un document connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Document aDocument= mapper.readValue(new String(data, "UTF-8"), Document.class);
            // Traitement de la photo
            if(aDocument.getUrl()!=null && aDocument.getUrl().length()>500)
            {
            	if(aDocument.getUrl().contains("=="))
            	{
            		int idx = aDocument.getUrl().indexOf("==");
            		String tab0 = aDocument.getUrl().substring(0,idx);
            		String tab1 = aDocument.getUrl().substring(idx+2);
            		String dataImage = tab1;
            		dataImage = dataImage.substring(dataImage.indexOf(",")+1);
            		String fileName = tab0;
           			FileOutputStream targetFile = new FileOutputStream(Utilitaire.REPERTOIRE_IMAGE+"/"+fileName);
           			byte[] rawImage = Base64.getDecoder().decode(dataImage);
        			targetFile.write(rawImage);
        			targetFile.close();
        			aDocument.setUrl(fileName);
            	}
            } else if(aDocument.getUrl()!=null && aDocument.getUrl().contains("_image%")) {
            	aDocument.setType("Photo");
            	aDocument.setUrl(aDocument.getUrl()+".png");
            }
            
           	AccesseurGenerique.getInstance().save(aDocument);
            
           	// Mise � jour du boolean sur l'Op�raiton
           	Operation aOperation = (Operation) AccesseurGenerique.getInstance().get(Operation.class, aDocument.getIdOperation());
           	aOperation.setPresenceDocument(true);
           	AccesseurGenerique.getInstance().update(aOperation);
        
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aDocument);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser);
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
            // Vérification de l'accès depuis un document connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Document aDocument= mapper.readValue(new String(data, "UTF-8"), Document.class);
            AccesseurGenerique.getInstance().update(aDocument);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser);
           return Reponse.getResponseOK(aDocument);
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
            // Vérification de l'accès depuis un document connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            Document aDocument = new Document();
            aDocument.setId(strid);
            AccesseurGenerique.getInstance().delete(aDocument);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "delete", "document/"+strid, connexionUser);
            return Reponse.getResponseOK(aDocument);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "delete","document/"+strid, connexionUser);
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
        File targetFile = new File(Utilitaire.REPERTOIRE_IMAGE+"/"+fileDetails.getFileName()); 
        java.nio.file.Files.copy(
        		uploadedInputStream, 
          targetFile.toPath(), 
          StandardCopyOption.REPLACE_EXISTING);
       
        uploadedInputStream.close();
        return Response.ok().build();
    }
}
