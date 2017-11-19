package mybank.test;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.AsyncN1qlQueryResult;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.User;
import mybank.server.rest.util.Accesseur;
import mybank.server.rest.util.AccesseurGenerique;
import mybank.server.rest.util.PasswordEncoder;
import rx.functions.Action1;

public class PasswordReinforcement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AccesseurGenerique.getInstance().init();
			List<User> list = (List<User>) AccesseurGenerique.getInstance().getListe(User.class);
	/*		for(User usr : list)
			{
				usr.setCodecourt("----");
				AccesseurGenerique.getInstance().update(usr);	
			}*/
			for(User usr : list)
			{
				String password = usr.getPassword();
				String passwordDecode = password;
				//String passwordDecode = new String(Base64.getDecoder().decode(password));
				passwordDecode="CoMoneyTy";
				System.out.println("Avant : "+passwordDecode);
				String passwordHash = PasswordEncoder.GenerePasswordHash(passwordDecode);
				System.out.println("Après : "+passwordHash);
				usr.setPassword(passwordHash);
				AccesseurGenerique.getInstance().update(usr);
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
