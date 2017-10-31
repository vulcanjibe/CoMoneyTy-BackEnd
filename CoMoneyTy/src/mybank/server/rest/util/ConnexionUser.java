package mybank.server.rest.util;

import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

import javax.ws.rs.core.HttpHeaders;

import mybank.server.beans.User;

public class ConnexionUser {

    public static final long INACTIVITE_MAX = 30 * 1000 * 60;
    User user;
    Date timeDerniereConnexion;

    static HashMap<String, ConnexionUser> LISTE_USER = new HashMap<String, ConnexionUser>();

    public static ConnexionUser getUser(String token) {
        return LISTE_USER.get(token);
    }
    
    public static int purge() {
    	int size = LISTE_USER.size();
    	LISTE_USER.clear();
    	return size;
    }

    public static ConnexionUser connect(String token, User aUser) throws Exception {
        String cle = token;
        ConnexionUser connexionUser = new ConnexionUser(aUser);
        connexionUser.setTimeDerniereConnexion(new Date());
        LISTE_USER.put(cle, connexionUser);
        UserStore us = new UserStore();
        us.idUser=aUser.getId();
        us.timestamp=new Date();
        us.id = token;
        try {
        	AccesseurGenerique.getInstance().save(us);
        } catch(Exception e)
        {
        	// DÈj‡ connectÈ???
        	us = (UserStore) AccesseurGenerique.getInstance().get(UserStore.class, token);
        	if(us!=null)
        	{
        		aUser = (User)AccesseurGenerique.getInstance().get(User.class,us.getIdUser());
        		connexionUser = new ConnexionUser(aUser);
        		connexionUser.setTimeDerniereConnexion(new Date());
        		LISTE_USER.put(cle, connexionUser);
        	}
        	
        }
        return connexionUser;
    }

    public void deconnect() {
        for (String token : LISTE_USER.keySet()) {
            ConnexionUser conn = LISTE_USER.get(token);
            if (conn.getUser().getId() == user.getId()) {
                LISTE_USER.remove(token);
                return;
            }
        }
    }

    public ConnexionUser(User user) {
        super();
        this.user = user;
        this.timeDerniereConnexion = new Date();
    }

    @Override
    public String toString() {
        return "ConnexionUser [user=" + user + ", timeDerniereConnexion="
                + timeDerniereConnexion + "]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimeDerniereConnexion() {
        return timeDerniereConnexion;
    }

    public void setTimeDerniereConnexion(Date timeDerniereConnexion) {
        this.timeDerniereConnexion = timeDerniereConnexion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConnexionUser other = (ConnexionUser) obj;
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else {
            return user.getId() == other.user.getId();
        }

        return true;
    }

    /**
     * @param headers
     * @throws Exception
     */
    public static ConnexionUser verificationConnexionUser(HttpHeaders headers)
            throws Exception {
		// Bypass pour le d√©veloppement
        // Si l'appel se fait en local, pas de verification de l'utilisateur
	/*	if (headers.getRequestHeader("host").get(0).contains("localhost")) {
         connexionAdmKLIF.getUser().setId(1);
         return connexionAdmKLIF;
         } */
        if (headers.getRequestHeaders().get("authorization") != null
                && !headers.getRequestHeaders().get("authorization").isEmpty()) {
            String token = headers.getRequestHeaders().get("authorization")
                    .get(0);
            token = token.substring(token.indexOf("=") + 1);
            ConnexionUser user = getUser(token);
            if (user == null) {
            	// Stateless
            	// Je cherche cette connection dans la base
            	UserStore theUserStore = (UserStore)AccesseurGenerique.getInstance().get(UserStore.class, token);
            	User theUser = (User)AccesseurGenerique.getInstance().get(User.class, theUserStore.idUser);
            	user = connect(token, theUser);
            	user.setTimeDerniereConnexion(theUserStore.getTimestamp());
            }

            if ((new Date().getTime() - user.getTimeDerniereConnexion()
                    .getTime()) > ConnexionUser.INACTIVITE_MAX) {
                user.deconnect();
                throw new Exception(
                        "User d√©connect√© : Temps limite d'inactivit√© ("
                        + ConnexionUser.INACTIVITE_MAX / 1000 / 60
                        + "mn) dÈpassÈ©...");
            }
            user.setTimeDerniereConnexion(new Date());
            UserStore us = new UserStore();
            us.id=token;
            us.idUser=user.getUser().getId();
            us.timestamp=user.getTimeDerniereConnexion();
            AccesseurGenerique.getInstance().update(us);
            return user;
        } else {
            throw new Exception("User non connect√©");
        }
    }

}
