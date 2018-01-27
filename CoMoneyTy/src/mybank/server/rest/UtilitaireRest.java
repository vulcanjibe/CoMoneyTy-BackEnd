package mybank.server.rest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
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

import mybank.server.beans.Depense;
import mybank.server.beans.Event;
import mybank.server.beans.GenerateurTest;
import mybank.server.beans.LienEventUser;
import mybank.server.beans.Mouvement;
import mybank.server.beans.User;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.ConnexionUser;
import mybank.server.rest.util.Index;
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
    @Path("/checkPerformance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkPerformance(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
        	
            // Vérification de l'accès depuis un depense connecté
            Date dateReceptionServeur = new Date();
            
            Date date = new Date();
    		// Vérification de l'accès depuis un user connecté
			connexionUser = ConnexionUser.verificationConnexionUser(headers);
			String idUser = connexionUser.getUser().getId();
			// On cherche les liens EventUset
			List<LienEventUser> liste = (List<LienEventUser>) AccesseurGenerique.getInstance().getListeFiltre(LienEventUser.class,
					"userId='" + idUser + "'");
			System.err.println("RECUP LISTE EVENT : "+(new Date().getTime()-date.getTime()));
            date = new Date();
			ArrayList<Event> listeEvent = new ArrayList<>();
			// Recupération des Event
			int nb = 1;
			for (LienEventUser lien : liste) {
				Event event = (Event) AccesseurGenerique.getInstance().get(Event.class, lien.getEventId());

				if(lien.getRoles()==null || lien.getRoles().isEmpty()) {
					
					lien.setRoles(new ArrayList<>());
					lien.getRoles().add("Participant");
				}
				
				event.setRoles(lien.getRoles());
				listeEvent.add(event);
				System.err.println("   EVENT "+(nb++)+" : "+(new Date().getTime()-date.getTime()));
	            date = new Date();

			}
            System.err.println("FIN ENRICHISSEMENT LISTE EVENT : "+(new Date().getTime()-date.getTime()));
            date = new Date();

			// Pour chaque event je calcule le montant
			for (Event event : listeEvent) {
				List<Depense> listeDepense = (List<Depense>) AccesseurGenerique.getInstance().getListeFiltre(Depense.class,
						"idEvent='" + event.getId() + "'");
				double montantEvent = 0;
				double montantPaye = 0;
				for (Depense depense : listeDepense) {
					montantEvent += depense.getMontant();
					if (depense.getIdPayeur().equals(idUser))
						montantPaye += depense.getMontant();
				}
				event.setMontantTotal(montantEvent);

				List<LienEventUser> listeUser = (List<LienEventUser>) AccesseurGenerique.getInstance().getListeFiltre(LienEventUser.class,
						"eventId='" + event.getId() + "'");
				double montantDu = (montantEvent / listeUser.size()) - montantPaye;
				event.setMontantDu(montantDu);
				event.setMontantDepense(montantPaye);
			}

            System.err.println("AJOUT DEPENSE : "+(new Date().getTime()-date.getTime()));
            date = new Date();

			// Check de la cloture des event + prise en compte des paiements
			for (Event event : listeEvent) {
				List<Mouvement> listeMouvement = (List<Mouvement>) AccesseurGenerique.getInstance().getListeFiltre(Mouvement.class,
						"idEvent='" + event.getId() + "'");
				if (event.getEtat().equalsIgnoreCase("En cours de solde")) {
					boolean tousRealise = true;
					for (Mouvement mouvement : listeMouvement) {
						if (!mouvement.getEtat().equals("R�alis�")) {
							tousRealise = false;
							break;
						}
					}
					if (tousRealise) {
						event.setEtat("Clos");
						AccesseurGenerique.getInstance().update(event);
					}
				}
				for (Mouvement mouvement : listeMouvement) {
					if (mouvement.getEtat().equals("R�alis�")) {
						if (mouvement.getIdEmetteur().equals(idUser)) {
							event.setMontantDepense(event.getMontantDepense() + mouvement.getMontant());
							event.setMontantDu(event.getMontantDu() - mouvement.getMontant());
						}
						if (mouvement.getIdDestinataire().equals(idUser)) {
							event.setMontantDepense(event.getMontantDepense() - mouvement.getMontant());
							event.setMontantDu(event.getMontantDu() + mouvement.getMontant());
						}

					}
				}
			}
			
            System.err.println("AJOUT MOUVEMENT : "+(new Date().getTime()-date.getTime()));
            date = new Date();

			 Date dateFinTraitement = new Date();
			 HashMap<String,Long> hash = new HashMap<>();
			 hash.put("start", dateReceptionServeur.getTime());
			 hash.put("stop", dateFinTraitement.getTime());
             return Reponse.getResponseOK(hash);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "purgeServeur", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }   
    @GET
    @Path("/checkReseau")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkReseau(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
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
    @GET
    @Path("/indexManquant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIndexManquant(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
             return Reponse.getResponseOK(Accesseur.INDEXES);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getIndexManquant", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    @GET
    @Path("/rechercheIndexManquant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtatIndexManquant(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
        	String rep = "ON";
        	if(! Accesseur.RECUPERATION_INDEX) 
        		rep="OFF";
             return Reponse.getResponseOK(rep);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getIndexManquant", "", connexionUser);
            return Reponse.reponseKO(e);
        }
    }
    @GET
    @Path("/toggleRechercheIndexManquant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response toggleRechercheIndexManquant(@Context HttpHeaders headers, @Context UriInfo uriInfo) {
        ConnexionUser connexionUser = null;
        try {
            // Vérification de l'accès depuis un depense connecté
        	Accesseur.RECUPERATION_INDEX = ! Accesseur.RECUPERATION_INDEX;
        	String rep = "ON";
        	if(! Accesseur.RECUPERATION_INDEX) {
        		rep="OFF";
        		Accesseur.INDEXES=new HashMap<String,ArrayList<Index>>();
        	}
             return Reponse.getResponseOK(rep);
        } catch (Exception e) {
            // Traitement de l'exception
            Utilitaire.exceptionRest(e, this.getClass(), "getIndexManquant", "", connexionUser);
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
