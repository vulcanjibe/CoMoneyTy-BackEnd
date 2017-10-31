package mybank.server.rest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.GenerateurTest;
import mybank.server.beans.User;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;
@Path("/utilitaire")
public class UtilitaireRest {


    @GET
    @Path("/initialisation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialisation(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            GenerateurTest.initialisation();
             return Reponse.getResponseOK("OK");
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "initialisation", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }

    @GET
    @Path("/purgeServeur") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response purgeServeur(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            int res = ConnexionUser.purge();
             return Reponse.getResponseOK("Purge : "+res);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "purgeServeur", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    @POST
    @Path("/controleRecoverPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controleRecoverPassword(@Context HttpHeaders headers, @Context UriInfo uriInfo,byte[] data) {
        ConnexionUser connexionUser = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Vérification de l'accès depuis un depense connecté
        	String verif = new String(data, "UTF-8");
        	String[] tab = verif.split("<-->");
        	String mail = tab[0];
        	String phone = tab[1];
			phone = phone.replaceAll(" ", "");
			phone = phone.replaceAll("\\\\.", " ");
			phone=phone.substring(phone.length()-9);
			phone="0"+phone;
        	List<User> list = (List<User>) AccesseurGenerique.getInstance().getListeFiltre(User.class, "phone='" + phone + "' and email='" + mail + "'");

        	if(list.isEmpty())
        		throw new Exception("Il n'existe pas d'utilisateur avec ce t�l�phone et ce mail!");
        	User aUser = list.get(0); 
        	String passwordDecode = new String(Base64.getDecoder().decode(aUser.getPassword().getBytes()));
			aUser.setPassword(passwordDecode);
	
             return Reponse.getResponseOK(aUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "purgeServeur", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    @GET
    @Path("/checkServeur")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkInfra(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
            Date dateReceptionServeur = new Date();
             return Reponse.getResponseOK("ServeurOK:"+dateReceptionServeur.getTime());
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "purgeServeur", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    @GET
    @Path("/checkDatabase")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkDatabase(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
        	Date dateReceptionServeur = new Date();
            List<User> list = AccesseurGenerique.getInstance().getListe(User.class);
            if(list.isEmpty())
            	throw new Exception("Impossible de r�cup�rer la liste des users");
            Date dateReceptionResultat = new Date();
             return Reponse.getResponseOK("CouchbaseOK:"+dateReceptionServeur.getTime()+":"+dateReceptionResultat.getTime());
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "purgeServeur", "", connexionUser);
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
        File targetFile = new File("../standalone/deployments/Image.war/depense/"+fileDetails.getFileName()); 
        java.nio.file.Files.copy(
        		uploadedInputStream, 
          targetFile.toPath(), 
          StandardCopyOption.REPLACE_EXISTING);
       
        uploadedInputStream.close();
        return Response.ok().build();
    }
}
