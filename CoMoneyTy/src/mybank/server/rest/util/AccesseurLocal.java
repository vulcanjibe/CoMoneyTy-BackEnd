package mybank.server.rest.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.beans.GenerateurTest;
import mybank.server.beans.ObjetId;

public class AccesseurLocal extends AccesseurGenerique {

	static HashMap<String, String> COUCHBASE = new HashMap<>();

	public void deleteAll(Class aClass) {
		for (String key : COUCHBASE.keySet()) {

			if (key.startsWith(aClass.getSimpleName() + "::"))
				COUCHBASE.remove(key);
		}

	}

	public void deleteAll() {
		COUCHBASE.clear();
	}

	public void init() {
		COUCHBASE = new HashMap<>();
		if (new File("couchbase_save.json").exists()) {
			try {
				BufferedReader myBuff = new BufferedReader(new FileReader("couchbase_save.json"));
				String str = myBuff.readLine();
				while (str != null) {
					String[] tab = str.split("<-->");
					COUCHBASE.put(tab[0], tab[1]);
					str = myBuff.readLine();

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				GenerateurTest.initialisation();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void save(ObjetId obj) throws Exception {
		if (obj.getId() == null) {
			String docId = UUID.randomUUID().toString();
			obj.setId(docId);
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		String key = obj.getNomClasse() + "::" + obj.getId();
		if (COUCHBASE.containsKey(key))
			throw new Exception("Document already exist on key " + key);

		COUCHBASE.put(key, json);
	}

	public void update(ObjetId obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		String key = obj.getNomClasse() + "::" + obj.getId();
		COUCHBASE.put(key, json);
	}

	public ObjetId get(Class aClass, String idObj) throws Exception {
		String key = aClass.getSimpleName() + "::" + idObj;
		String json = COUCHBASE.get(key);
		ObjectMapper mapper = new ObjectMapper();
		return (ObjetId) mapper.readValue(json, aClass);
	}

	public ArrayList getListe(Class aClass) throws Exception {
		ArrayList array = new ArrayList<>();
		for (String key : COUCHBASE.keySet()) {
			if (key.startsWith(aClass.getSimpleName() + "::")) {
				String json = COUCHBASE.get(key);
				ObjectMapper mapper = new ObjectMapper();
				array.add(mapper.readValue(json, aClass));
			}
		}
		return array;

	}

	public void sauvegarde() throws Exception {
		BufferedWriter myBuff = new BufferedWriter(new FileWriter("couchbase_save.json"));
		for (String key : COUCHBASE.keySet()) {
			String str = key + "<-->" + COUCHBASE.get(key);
			myBuff.write(str + "\n");
		}
		myBuff.close();

	}

	public ArrayList getListeFiltre(Class aClass, String filtre) throws Exception {
		ArrayList array = new ArrayList<>();
		for (String key : COUCHBASE.keySet()) {
			if (key.startsWith(aClass.getSimpleName() + "::")) {
				String json = COUCHBASE.get(key);
				// filtrage
				String[] tab = filtre.split(" and ");
				boolean toutOK = true;
				for (String filtreur : tab) {
					if (filtreur.contains("=")) {
						String[] champs = filtreur.split("=");
						String valeur = champs[1].trim();
						if (valeur.startsWith("'"))
							valeur = valeur.substring(1);
						if (valeur.endsWith("'"))
							valeur = valeur.substring(0, valeur.length() - 1);

						String filtreJson = "\"" + champs[0].trim() + "\":\"" + valeur + "\"";
						if (valeur.equals("true") || valeur.equals("false"))
							filtreJson = "\"" + champs[0].trim() + "\":" + valeur + "";
						if (champs[0].contains(".")) {
							// On deserialize?
							String val = champs[0];
							String valueHaute = champs[0].split("\\.")[0];
							String valueBasse = champs[0].split("\\.")[1];
							ObjectMapper mapper = new ObjectMapper();
							JsonNode node =mapper.readTree(json);
							String str = node.get(valueHaute).get(valueBasse).toString();
							if (str.startsWith("\""))
								str = str.substring(1);
							if (str.endsWith("\""))
								str = str.substring(0, str.length() - 1);
							if (!str.equals(valeur))
								toutOK = false;
						} else if (json.indexOf(filtreJson) < 0)
							toutOK = false;
					} else if (filtreur.contains("is")) {
						String[] champs = filtreur.split("is");
						String valeur= champs[1].trim();
						boolean not = false;
						if(valeur.startsWith("not"))
						{
							not=true;
							valeur=valeur.substring(4);
						}
						String filtreJson = "\"" + champs[0].trim() + "\":" + valeur + "";
						if(not)
						{
							if (json.indexOf(filtreJson) >= 0)
								toutOK = false;
						} else {
							if (json.indexOf(filtreJson) < 0)
								toutOK = false;
						}
					} else {
						throw new Exception("Filtre non pris en charge : " + filtre);
					}
				}
				if (toutOK) {
					ObjectMapper mapper = new ObjectMapper();
					array.add(mapper.readValue(json, aClass));
				}
			}
		}

		return array;
	}

	public void delete(ObjetId obj) throws Exception {

		String key = obj.getNomClasse() + "::" + obj.getId();
		COUCHBASE.remove(key);
	}

	public void deleteWhere(Class aClass, String request) throws Exception {

	}
}