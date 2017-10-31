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
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.ObjetId;

public abstract class AccesseurGenerique {
	
	public static AccesseurGenerique getInstance() {
		return new Accesseur();
		
	}
	public abstract void deleteAll(Class aClass);
	public abstract void deleteAll();
	public abstract void init();
	public abstract void sauvegarde() throws Exception;
	public abstract void save(ObjetId obj) throws Exception;
	public abstract  void update(ObjetId obj) throws Exception;
	public abstract  ObjetId get(Class aClass,String idObj) throws Exception;
	public abstract  ArrayList getListe(Class aClass) throws Exception;
	public abstract   ArrayList getListeFiltre(Class aClass,String filtre) throws Exception;
	public abstract  void delete(ObjetId obj) throws Exception;
	public abstract  void deleteWhere(Class aClass,String request) throws Exception;
}