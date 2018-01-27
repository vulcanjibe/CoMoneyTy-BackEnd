package mybank.server.rest.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
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
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.ObjetId;

public class Accesseur extends AccesseurGenerique {
	
	
	private static final PersistTo PERSISTO = PersistTo.NONE;
	public static Bucket BUCKET = null;
	public final static String BUCKET_NAME = "comoneyty";
	public static HashMap<String,ArrayList<Index>> INDEXES = new HashMap<>();
	public static boolean RECUPERATION_INDEX = false;
	public  void deleteAll(Class aClass)
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"'",params);
		N1qlQueryResult result = BUCKET.query(query);
	
	}
	public  void deleteAll()
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
			
			N1qlQuery query1 = N1qlQuery.simple(requete,params);
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
	

	
	public  void init() {
		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
	            .connectTimeout(30000) //10000ms = 10s, default is 5s
	            .build();
	
		Cluster cluster = CouchbaseCluster.create(env,"couchbase.home");
		cluster.authenticate("comoneyty", "robbynaish");
		BUCKET = cluster.openBucket(BUCKET_NAME,30,TimeUnit.SECONDS);
		
		N1qlQuery query = N1qlQuery.simple("select * from system:indexes");
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		for(N1qlQueryRow row : list)
		{
			JsonObject ind = row.value().getObject("indexes");
			System.out.println("INDEX : "+ind.get("name")+" : "+ind.get("index_key")+" ON "+ind.get("condition"));
		}
		//REPOSITORY = BUCKET.repository();
	}
	public  void save(ObjetId obj) throws Exception
	{
		if(obj.getId()==null) {
			String docId = UUID.randomUUID().toString();
			obj.setId(docId);
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		BUCKET.insert(RawJsonDocument.create(obj.getNomClasse()+"::"+obj.getId(), json),PERSISTO);
		
		
	}
	
	public   void update(ObjetId obj) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		BUCKET.upsert(RawJsonDocument.create(obj.getNomClasse()+"::"+obj.getId(), json),PERSISTO);
	}
	
	
	public   ObjetId get(Class aClass,String idObj) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("select * from `"+BUCKET_NAME+"` where id = '"+idObj+ "' and nomClasse='"+aClass.getSimpleName()+"'");
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		for(N1qlQueryRow row : list)
		{
			JsonObject json = row.value().getObject(BUCKET_NAME);
			//json.getObject("beer-sample")
			ObjectMapper mapper = new ObjectMapper();
			ObjetId user = (ObjetId) mapper.readValue(json.toString(),aClass);
			return user;
		}
		return null;
	}
	private  N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
	public   ArrayList getListe(Class aClass) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("select * from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"'",params);
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		ArrayList<Object> liste = new ArrayList<>();
		for(N1qlQueryRow row : list)
		{
			JsonObject json = row.value().getObject(BUCKET_NAME);
			//json.getObject("beer-sample")
			ObjectMapper mapper = new ObjectMapper();
			Object user = mapper.readValue(json.toString(),aClass);
			liste.add(user);
		}
		return liste;
	}
	
	public    ArrayList getListeFiltre(Class aClass,String filtre) throws Exception
	{
		String requete = "select * from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"'";
		requete+=" and "+filtre;
		/* Traitement pour détecter les indexs manquants */
		if(RECUPERATION_INDEX) {
			String in = aClass.getSimpleName();
			TreeSet<String> listeOut = new TreeSet<>();
			String tab[] = filtre.split("=");
			int cpt=0;
			for(String s : tab) {
				if(cpt%2==0) {
					s=s.trim();
					if(s.contains(" ")) {
						String val = s.substring(s.indexOf(" ")+1);
						listeOut.add(val);
					} else listeOut.add(s);
				}
				cpt++;
			}
			String out="";
			for(String s: listeOut)
			{
				out+=s+";";
			}
			if(INDEXES.get(in)==null) {
				INDEXES.put(in, new ArrayList<>());
			}
			ArrayList<Index> listeIndex = INDEXES.get(in);
			Index index = new Index();
			index.setChaine(out.toLowerCase());
			index.setNombreAppel(1);
			if(!listeIndex.contains(index)) {
				listeIndex.add(index);
			} else {
				int idx = listeIndex.indexOf(index);
				listeIndex.get(idx).addAppel();
			}
		}
		N1qlQuery query = N1qlQuery.simple(requete,params);
		N1qlQueryResult result = BUCKET.query(query);
		List<N1qlQueryRow> list = result.allRows();
		ArrayList<Object> liste = new ArrayList<>();
		for(N1qlQueryRow row : list)
		{
			JsonObject json = row.value().getObject(BUCKET_NAME);
			//json.getObject("beer-sample")
			ObjectMapper mapper = new ObjectMapper();
			Object user = mapper.readValue(json.toString(),aClass);
			liste.add(user);
		}
		return liste;
	}

	public   void delete(ObjetId obj) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"` where id = '"+obj.getId()+ "' and nomClasse='"+obj.getNomClasse()+"'");
		N1qlQueryResult result = BUCKET.query(query);
		if(result.info().mutationCount()!=1)
			throw new Exception("Souci dans le delete de "+obj);
		
	}

	public   void deleteWhere(Class aClass,String request) throws Exception
	{
		N1qlQuery query = N1qlQuery.simple("delete from `"+BUCKET_NAME+"` where nomClasse='"+aClass.getSimpleName()+"' and "+request);
		N1qlQueryResult result = BUCKET.query(query);
		if(result.info().mutationCount()!=1)
			throw new Exception("Souci dans le delete de "+aClass.getSimpleName());
		
	}
	@Override
	public void sauvegarde() throws Exception {
		// TODO Auto-generated method stub
		
	}
}