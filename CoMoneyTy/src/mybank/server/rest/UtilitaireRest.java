package mybank.server.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.CacheType;
import mybank.server.beans.Communaute;
import mybank.server.beans.Compte;
import mybank.server.beans.LienCommunauteUser;
import mybank.server.beans.Message;
import mybank.server.beans.Mouvement;
import mybank.server.beans.ObjetId;
import mybank.server.beans.Role;
import mybank.server.beans.TypeObjet;
import mybank.server.beans.User;
import mybank.server.beans.js.AppelDeFond;
import mybank.server.beans.js.ContactRetenu;
import mybank.server.beans.js.FiltreBalance;
import mybank.server.beans.js.Invitation;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.EdgeCytoscape;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.RetourAppel;
import mybank.server.rest.util.SolveurBalance;
import mybank.server.rest.util.Utilitaire;

@Path("/utilitaire")
public class UtilitaireRest {


	static String ENTITY = "user";

	
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Object test(@Context HttpHeaders headers,
            @Context UriInfo uriInfo) {
        try {
            String json = "{" + 
            		"  \"items\": [" + 
            		"    {" + 
            		"      \"name\": \"Anniversaire Tony\"," + 
            		"      \"image\": \"./assets/images/lists/200x200EventCrossfit.png\"," + 
            		"      \"date\": \"01/02/2017\"," + 
            		"      \"solde\": 100," + 
            		"      \"description\": \".......\"" + 
            		"    }," + 
            		"    {" + 
            		"      \"name\": \"Soir�e foot\"," + 
            		"      \"image\": \"./assets/images/lists/200x200EventUSOpen.png\"," + 
            		"      \"date\": \"04/03/2017\"," + 
            		"      \"solde\": -10," + 
            		"      \"description\": \".......\"" + 
            		"    }," + 
            		"    {" + 
            		"      \"name\": \"Sortie ski\"," + 
            		"      \"image\": \"./assets/images/lists/200x200TBT.png\"," + 
            		"      \"date\": \"19/05/2017\"," + 
            		"      \"solde\": 100," + 
            		"      \"description\": \".......\"" + 
            		"    }," + 
            		"    {" + 
            		"      \"name\": \"Restaurant\"," + 
            		"      \"image\": \"./assets/images/lists/200x200BritishOpen.png\"," + 
            		"      \"date\": \"11/04/2017\"," + 
            		"      \"solde\": -50," + 
            		"      \"description\": \".......\"" + 
            		"    }," + 
            		"    {" + 
            		"      \"name\": \"Restaurant\"," + 
            		"      \"image\": \"./assets/images/lists/200x200FIFA.png\"," + 
            		"      \"date\": \"15/02/2017\"," + 
            		"      \"solde\": 10," + 
            		"      \"description\": \".......\"" + 
            		"    }" + 
            		"  ]" + 
            		"}";
            return Reponse.getResponseJsonOK(json);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "types", "", null);
            return Reponse.reponseKO(e);
        }
    }

	
    @GET
    @Path("/types")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getTypes(@Context HttpHeaders headers,
            @Context UriInfo uriInfo) {
        try {
            List<?> liste =Accesseur.getListe(CacheType.class);
            return Reponse.getResponseOK(liste);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "types", "", null);
            return Reponse.reponseKO(e);
        }
    }

    @PUT
    @Path("/generatecachetype")
    @Produces(MediaType.APPLICATION_JSON)
    public Object generatecache(@Context HttpHeaders headers,
            @Context UriInfo uriInfo, byte[] data) {

        ObjectMapper mapper = new ObjectMapper();
        try {
          
            return "done";
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "login", "", null);
            return Reponse.reponseKO(e);
        }

    }

  

  
 
 


}
