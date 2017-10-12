package mybank.server.rest.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.ObjetId;

public class Accesseur {
	
	
	private static final PersistTo PERSISTO = PersistTo.MASTER;
	//static Repository REPOSITORY = null;
	public static Bucket BUCKET = null;
	static HashMap<String,Integer> HASHID = new HashMap<>();
	public final static String BUCKET_NAME = "beer-sample";
	
	
	public static void deleteAll(Class aClass)
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"'");
		N1qlQueryResult result = BUCKET.query(query);
	
	}
	public static void deleteAll()
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"`");
		N1qlQueryResult result = BUCKET.query(query);
		boolean ok = false;
		while(!ok)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String requete = "select * from `"+BUCKET_NAME+"`";
			
			N1qlQuery query1 = N1qlQuery.simple(requete);
			N1qlQueryResult result1 = Accesseur.BUCKET.query(query);
			List<N1qlQueryRow> list = result1.allRows();
			if(list.isEmpty())
			{
				ok=true;
				System.out.println("Delete Completed");
			}
			else
				System.out.println("Waiting for delete...");
		}

	}
	
	public static void init() {
		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
                .connectTimeout(30000) //10000ms = 10s, default is 5s
                .build();

		Cluster cluster = CouchbaseCluster.create(env,"couchbase.home");
		BUCKET = cluster.openBucket(BUCKET_NAME,30,TimeUnit.SECONDS);
		//REPOSITORY = BUCKET.repository();
	}
	
/*	public static void initHashID()
	{
		N1qlQuery query = N1qlQuery.simple("select max(id) as max,nomClasse from `"+BUCKET_NAME+"` where nomClasse like \"mybank%\" group by nomClasse");
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		for(N1qlQueryRow row : list) {
			JsonObject json = row.value();
			if(json.containsKey("nomClasse"))
				HASHID.put(json.getString("nomClasse"), new Integer(json.getInt("max")+1));
		}
		
	}
*/
	
	public static void save(ObjetId obj) throws Exception
	{
		if(obj.getId()==null) {
			String docId = UUID.randomUUID().toString();
			obj.setId(docId);
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		BUCKET.insert(RawJsonDocument.create(obj.getNomClasse()+"::"+obj.getId(), json),PERSISTO);
		
		
	}
	
	public static  void update(ObjetId obj) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		BUCKET.upsert(RawJsonDocument.create(obj.getNomClasse()+"::"+obj.getId(), json),PERSISTO);
	}
	
	
	public static  ObjetId get(Class aClass,String idObj) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("select * from `"+BUCKET_NAME+"` where id = '"+idObj+ "' and nomClasse='"+aClass.getSimpleName()+"'");
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		for(N1qlQueryRow row : list)
		{
			JsonObject json = row.value().getObject("beer-sample");
			//json.getObject("beer-sample")
			ObjectMapper mapper = new ObjectMapper();
			ObjetId user = (ObjetId) mapper.readValue(json.toString(),aClass);
			return user;
		}
		return null;
	}
	
	public static  ArrayList getListe(Class aClass) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("select * from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"'");
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		ArrayList<Object> liste = new ArrayList<>();
		for(N1qlQueryRow row : list)
		{
			JsonObject json = row.value().getObject("beer-sample");
			//json.getObject("beer-sample")
			ObjectMapper mapper = new ObjectMapper();
			Object user = mapper.readValue(json.toString(),aClass);
			liste.add(user);
		}
		return liste;
	}
	
	public static   ArrayList getListeFiltre(Class aClass,String filtre) throws Exception
	{
		String requete = "select * from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"'";
		requete+=" and "+filtre;
		
		N1qlQuery query = N1qlQuery.simple(requete);
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		ArrayList<Object> liste = new ArrayList<>();
		for(N1qlQueryRow row : list)
		{
			JsonObject json = row.value().getObject("beer-sample");
			//json.getObject("beer-sample")
			ObjectMapper mapper = new ObjectMapper();
			Object user = mapper.readValue(json.toString(),aClass);
			liste.add(user);
		}
		return liste;
	}

	public static  void delete(ObjetId obj) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"` where id = '"+obj.getId()+ "' and nomClasse='"+obj.getNomClasse()+"'");
		N1qlQueryResult result = BUCKET.query(query);
		if(result.info().mutationCount()!=1)
			throw new Exception("Souci dans le delete de "+obj);
		
	}

	public static  void deleteWhere(ObjetId obj,String request) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"` where nomClasse='"+obj.getNomClasse()+"' and "+request);
		N1qlQueryResult result = BUCKET.query(query);
		if(result.info().mutationCount()!=1)
			throw new Exception("Souci dans le delete de "+obj);
		
	}
}