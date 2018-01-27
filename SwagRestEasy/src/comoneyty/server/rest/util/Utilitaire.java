package comoneyty.server.rest.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import comoneyty.server.beans.User;
import comoneyty.server.rest.connexion.ConnexionUser;
import comoneyty.server.rest.util.Reponse;


public class Utilitaire {
	public static String REPERTOIRE_IMAGE = "D:\\CoMoneyTy\\upload-image";
	//public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
	public static final SimpleDateFormat FORMAT_DATE_COURT = new SimpleDateFormat("dd/MM/yy");
	public static final SimpleDateFormat FORMAT_DATE_HEURE = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	public static final SimpleDateFormat FORMAT_DATE_STANDARD = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMAT_HEURE_COURT = new SimpleDateFormat("HH:mm");	
	public static final SimpleDateFormat FORMAT_TIMESTAMP_FICHIER = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");	
	public static final SimpleDateFormat FORMAT_JOUR_FICHIER = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");	
	public static String majusculePremiereLettre(String name) {
		return name.substring(0, 1).toUpperCase()
				+ name.substring(1).toLowerCase();
	}
	public static String minusculePremiereLettre(String name) {
		return name.substring(0, 1).toLowerCase()
				+ name.substring(1);
	}
        // Traitement des exceptions REST
        public static void exceptionRest(Exception e,Class entite,String method, byte[] data,ConnexionUser connexionUser)
        {
        	e.printStackTrace();
            try {
                String str = new String(data, "UTF-8");
                String nom="Unknown";
                if(connexionUser!=null)
                	if(connexionUser.getUser()!=null)
                		nom=connexionUser.getUser().getNom();
                Logger.getLogger(entite.getName()).log(Level.SEVERE, "User : "+nom+" - "+method+" - "+str);
            } catch (Exception ex) {
                Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, "conversion data impossible", ex);
            }
        }

        // Traitement des logs REST
        public static void loggingRest(Class entite,String method, byte[] data,ConnexionUser connexionUser)
        {
            try {
                String str = new String(data, "UTF-8");
                String nom = "unknown";
                if(connexionUser!=null)
                	if(connexionUser.getUser()!=null)
                		nom=connexionUser.getUser().getNom();
                Logger.getLogger(entite.getName()).log(Level.WARNING, "User : "+nom+" - "+method+" - "+str);
            } catch (Exception ex) {
                Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, "conversion data impossible", ex);
            }
        }
        
        public static void loggingRest(Class entite,String method, Object obj,ConnexionUser connexionUser)
        {
        	loggingRest( entite, method, obj.toString(),connexionUser);
        }
      // Traitement des exceptions REST
        public static void exceptionRest(Exception e,Class entite,String method, String data,ConnexionUser connexionUser)
        {
            exceptionRest(e,entite,method,data.getBytes(),connexionUser);
        }

        // Traitement des logs REST
        public static void loggingRest(Class entite,String method, String data,ConnexionUser connexionUser)
        {
            loggingRest( entite, method, data.getBytes(),connexionUser);
        }
}
