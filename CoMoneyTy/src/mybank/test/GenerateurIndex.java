package mybank.test;

import java.io.File;
import java.util.ArrayList;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

import mybank.server.beans.GenerateurTest;
import mybank.server.rest.util.Accesseur;

public class GenerateurIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File directory = new File(new GenerateurTest().getClass().getResource("listeOperationTest.txt").getFile());
		directory = directory.getParentFile();
		File[] liste =  directory.listFiles();
		ArrayList<String> indexes = new ArrayList<>();
		for(File file : liste) {
			if(file.getName().endsWith(".class")) {
				String nom = file.getName().substring(0,file.getName().lastIndexOf("."));
				indexes.add("CREATE INDEX `idx_nomClasse_"+nom+"_id` ON `comoneyty`(nomClasse,id) where nomClasse='"+nom+"' USING GSI");
				indexes.add("CREATE INDEX `idx_nomClasse_"+nom+"` ON `comoneyty`(nomClasse) where nomClasse='"+nom+"' USING GSI;");
				
				
			}
		}
		
		Accesseur.getInstance().init();
		for(String str : indexes)
		{
			N1qlQuery query = N1qlQuery.simple(str);
			N1qlQueryResult result = Accesseur.BUCKET.query(query);
		}
	}

}
