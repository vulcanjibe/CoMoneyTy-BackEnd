package mybank.server.rest;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

import mybank.server.beans.Depense;
import mybank.server.beans.Event;
import mybank.server.beans.LienEventUser;
import mybank.server.beans.Operation;
import mybank.server.beans.Relation;
import mybank.server.beans.User;
import mybank.server.beans.javascript.OperationAvecDepense;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Reponse;
import mybank.server.rest.util.Utilitaire;

@Path("/user")
public class UserRest {

    static String ENTITY = "user";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpHeaders headers, @Context UriInfo uriInfo) {

        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
         //   connexionUser = ConnexionUser.verificationConnexionUser(headers);
            List<User> liste = (List<User>) Accesseur.getListe(User.class);
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
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            User aUser = (User) Accesseur.get(User.class, strid);
            if(aUser==null)
                throw new Exception("User inconnu");
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "getById", strid, connexionUser.getUser());
            return Reponse.getResponseOK(aUser);
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
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            String clauseWhere =new String(data, "UTF-8");
            List<User> liste = (List<User>) Accesseur.getListeFiltre(User.class, clauseWhere);

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
            User aUser = new User();

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "create", "", connexionUser.getUser());
            return Reponse.getResponseOK(aUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "create", "", connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpHeaders headers, @Context UriInfo uriInfo, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
         
            User aUser = mapper.readValue(new String(data, "UTF-8"), User.class);
            if(aUser.getUrlAvatar()==null)
            	aUser.setPrenom("standard.png");
            Accesseur.save(aUser);
            String key = UUID.randomUUID().toString().toUpperCase();
            connexionUser = ConnexionUser.connect(key, aUser);
            HashMap<String, Object> maHash = new HashMap<String, Object>();
            maHash.put("id", key);
            maHash.put("user", aUser);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
           return Reponse.getResponseOK(maHash);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
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
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            User aUser = mapper.readValue(new String(data, "UTF-8"), User.class);
            Accesseur.delete(aUser);
            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "save", data, connexionUser.getUser());
            return Reponse.getResponseOK(aUser);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "save", data, connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
    
    // METOHDE COMPLEMENTAIRE
    @GET
    @Path("/{id}/events")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventDeUserId(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String idUser) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            
            // On cherche les liens EventUset
            List<LienEventUser> liste = (List<LienEventUser>) Accesseur.getListeFiltre(LienEventUser.class, "userId='"+idUser+"'");
            ArrayList<Event> listeEvent = new ArrayList<>();
           // Recupération des Event
            for(LienEventUser lien : liste) {
            	listeEvent.add((Event) Accesseur.get(Event.class, lien.getEventId()));
            }
            
            // Pour chaque event je calcule le montant
            for(Event event : listeEvent)
            {
            	List<Depense> listeDepense = (List<Depense>) Accesseur.getListeFiltre(Depense.class, "idEvent='"+event.getId()+"'");
            	double montantEvent = 0;
            	for(Depense depense : listeDepense)
            		montantEvent+=depense.getMontant();
            	event.setMontant(montantEvent);
            }
            
            // Traitement de la log
            return Reponse.getResponseOK(listeEvent);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "/{id}/events", "/"+idUser+"/events", connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
    
    
    @GET
    @Path("/{id}/operations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationDeUserId(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String idUser) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            
            // On cherche les liens EventUset
            List<Operation> liste = (List<Operation>) Accesseur.getListeFiltre(Operation.class, "userId='"+idUser+"'");
            List<OperationAvecDepense> listeOperation = new ArrayList<>();
            List<Depense> listeDep = (List<Depense>) Accesseur.getListeFiltre(Depense.class, "idPayeur='"+idUser+"'  and idOperation is not null");
            for(Operation operation : liste) {
            	// Je regarde si elle est li�e � une d�pense
            	Depense depTrouve=null;
            	for(Depense dep : listeDep)
            	{
            		if(dep.getIdOperation()!=null && dep.getIdOperation().equals(operation.getId())) {
	    				depTrouve=dep;
	    				break;
            		}
            	}
            	OperationAvecDepense opeAvecDep = new OperationAvecDepense(operation,depTrouve);
            	
            		
            	listeOperation.add(opeAvecDep);
            }
            // Traitement de la log
            return Reponse.getResponseOK(listeOperation);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "/{id}/operations", "/"+idUser+"/operations", connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
    
    @GET
    @Path("/relations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelationDeUserId(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("id") String idUser) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un user connecté
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            
            // On cherche les liens EventUset
            List<Relation> liste = (List<Relation>) Accesseur.getListeFiltre(Relation.class, "user1Id='"+connexionUser.getUser().getId()+"'");
            ArrayList<User> listeRelation = new ArrayList<>();
           // Recupération des Event
            for(Relation lien : liste) {
            	listeRelation.add((User) Accesseur.get(User.class, lien.getUser2Id()));
            }
            // Traitement de la log
            return Reponse.getResponseOK(listeRelation);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "/{id}/events", "/"+idUser+"/events", connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(@Context HttpHeaders headers, @Context UriInfo uriInfo,
            byte[] data) {

        ObjectMapper mapper = new ObjectMapper()
                ;
        ConnexionUser connexionUser = null;
        User aUser = null;
        try {

            aUser = mapper.readValue(new String(data, "UTF-8"), User.class);
           
            List<User> list = (List<User>)Accesseur.getListeFiltre(
                    User.class, "login='"+aUser.getLogin()+"'");

            if (list.isEmpty()) {
                throw new Exception("Le user " + aUser.getLogin()
                        + " n'existe pas");
            }
            boolean trouve = false;
            for (User usr : list) {

                String password = usr.getPassword();
                String passwordEncode = Base64.getEncoder().encodeToString(aUser
                        .getPassword().getBytes());
                if (passwordEncode.equalsIgnoreCase(password)) {
                    trouve = true;
                    aUser = usr;
                    break;
                }
            }
            if (!trouve) {
                throw new Exception("Mot de passe incorrect");
            }

            Date date = new Date();
            date.setTime(date.getTime() + ConnexionUser.INACTIVITE_MAX);
            String key = UUID.randomUUID().toString().toUpperCase();

            /* Sauvegarde du user dans la LISTE_USER */
            connexionUser = ConnexionUser.connect(key, aUser);

            HashMap<String, Object> maHash = new HashMap<String, Object>();
            maHash.put("id", key);
            maHash.put("user", aUser);

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "login", "",
                    connexionUser.getUser());
            //return maHash;
            return Reponse.getResponseOK(maHash);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "login", "", aUser);
            return Reponse.reponseKO(e);
        }
    }

    
    @POST
    @Path("/login-facebook")
    @Produces(MediaType.APPLICATION_JSON)
    public Object loginFacebook(@Context HttpHeaders headers, @Context UriInfo uriInfo,
            byte[] data) {

        ObjectMapper mapper = new ObjectMapper();
        ConnexionUser connexionUser = null;
        User aUser = null;
        try {

            aUser = mapper.readValue(new String(data, "UTF-8"), User.class);
           
            
            List<User> list = (List<User>)Accesseur.getListeFiltre(
                    User.class, "id='"+aUser.getId()+"'");

            if (list.isEmpty()) {
               // Cr�ation du user facebook
            	if(aUser.getEmail()==null) {
            		aUser.setEmail("inconnu");
            		aUser.setLogin("Facebook-"+aUser.getNom()+"-"+aUser.getPrenom());
            	}
            	else {
            		aUser.setLogin(aUser.getEmail());
            	}
            	
            	Accesseur.save(aUser);
            } else
            {
            	aUser=list.get(0);
            }

            String key = UUID.randomUUID().toString().toUpperCase();

            /* Sauvegarde du user dans la LISTE_USER */
            connexionUser = ConnexionUser.connect(key, aUser);

            HashMap<String, Object> maHash = new HashMap<String, Object>();
            maHash.put("id", key);
            maHash.put("user", aUser);

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "login", "",
                    connexionUser.getUser());
            //return maHash;
            return Reponse.getResponseOK(maHash);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "login", "", aUser);
            return Reponse.reponseKO(e);
        }
    }
    
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Object logout(@Context HttpHeaders headers,
            @Context UriInfo uriInfo, byte[] data) {

        ObjectMapper mapper = new ObjectMapper()
                ;
        ConnexionUser connexionUser = null;
        try {

            User aUser = mapper
                    .readValue(new String(data, "UTF-8"), User.class);
            connexionUser = ConnexionUser.verificationConnexionUser(headers);
            connexionUser.deconnect();

            // Traitement de la log
            Utilitaire.loggingRest(this.getClass(), "login", "",
                    connexionUser.getUser());
            return aUser;
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "login", "",
                    connexionUser.getUser());
            return Reponse.reponseKO(e);
        }
    }

 
}
