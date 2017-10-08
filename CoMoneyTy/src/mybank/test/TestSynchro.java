package mybank.test;

import java.util.ArrayList;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.AsyncN1qlQueryResult;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.rest.util.Accesseur;
import rx.functions.Action1;

public class TestSynchro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Accesseur.init();
			Accesseur.deleteAll(ObjetTest.class);
			ArrayList<ObjetTest> liste = new ArrayList<>();
			ArrayList<RawJsonDocument> listeDoc = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				liste.add(new ObjetTest("Objet " + i));
			}
			for (ObjetTest obj : liste) {
				//listeDoc.add(Accesseur.save(obj));
				//listeDoc.add(Accesseur.create(obj));
			}
			
			ArrayList<ObjetTest> liste2 = Accesseur.getListe(ObjetTest.class);
			
		
			System.out.println(liste.size()+" -> "+liste2.size());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
