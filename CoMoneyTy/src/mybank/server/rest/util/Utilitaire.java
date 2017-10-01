package mybank.server.rest.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mybank.server.beans.User;
import mybank.server.rest.util.Reponse;


public class Utilitaire {
	//public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
	public static final SimpleDateFormat FORMAT_DATE_COURT = new SimpleDateFormat("dd/MM/yy");
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
        public static void exceptionRest(Exception e,Class entite,String method, byte[] data,User user)
        {
            try {
                String str = new String(data, "UTF-8");
                String nom="Server";
                if(user!=null)
                	nom=user.getNom();
                Logger.getLogger(entite.getName()).log(Level.SEVERE, "User : "+nom+" - "+method+" - "+str);
            } catch (Exception ex) {
                Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, "conversion data impossible", ex);
            }
        }

        // Traitement des logs REST
        public static void loggingRest(Class entite,String method, byte[] data,User user)
        {
            try {
                String str = new String(data, "UTF-8");
                Logger.getLogger(entite.getName()).log(Level.WARNING, "User : "+user.getNom()+" - "+method+" - "+str);
            } catch (Exception ex) {
                Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, "conversion data impossible", ex);
            }
        }
      // Traitement des exceptions REST
        public static void exceptionRest(Exception e,Class entite,String method, String data,User user)
        {
            exceptionRest(e,entite,method,data.getBytes(),user);
        }

        // Traitement des logs REST
        public static void loggingRest(Class entite,String method, String data,User user)
        {
            loggingRest( entite, method, data.getBytes(),user);
        }
}
