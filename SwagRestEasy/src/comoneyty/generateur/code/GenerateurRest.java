package comoneyty.generateur.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateurRest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//String root = "D:\\CoMoneyTy\\Dev\\SwagRestEasy\\src";
		String root = "C:\\Users\\Herve\\eclipse-workspace\\SwagRestEasy\\src";
		String rootBean = root+"\\comoneyty\\server\\beans";
		String rootRest = root+"\\comoneyty\\server\\rest";
		String modele = root+"\\comoneyty\\generateur\\code\\ModeleRest.java";
		String generation = root+"\\comoneyty\\generateur\\code\\generation.txt";
		// récupération des beans
		ArrayList<Bean> beans = new ArrayList();
		BufferedReader myBuffGeneration = new BufferedReader(new FileReader(generation));
		String str = myBuffGeneration.readLine();
		str = myBuffGeneration.readLine();
		while(str!=null) {
			String[] tab =str.split(";");
			Bean bean = new Bean();
			bean.setNom(tab[0]);
			if(tab.length>1) {
				String[] deps = tab[1] .split("-");
				for(String dep : deps)
					bean.dependances.add(dep);
			}
			beans.add(bean);
			str = myBuffGeneration.readLine();
		}
		
		ArrayList<String> lignesModele = new ArrayList();
		ArrayList<String> lignesModeleDep = new ArrayList();
		BufferedReader myBuff = new BufferedReader(new FileReader(modele));
		str = myBuff.readLine();
		boolean isDep = false;
		while(str!=null) {
			if(str.startsWith("// LIEN-DEBUT"))
				isDep=true;
			if(str.startsWith("// LIEN-FIN"))
				break;
			if(isDep)
				lignesModeleDep.add(str);
			else
				lignesModele.add(str);
			str = myBuff.readLine();
		}
		for(Bean bean : beans) {		
			String nomBean = bean.getNom();
			BufferedWriter myBuffWriter = new BufferedWriter(new FileWriter(rootRest+"\\"+nomBean+"Rest.java"));
			for(String ligne : lignesModele) {
				ligne = ligne.replaceAll("modele", nomBean.toLowerCase());
				ligne = ligne.replaceAll("Modele", nomBean);
				if(ligne.startsWith("@Path") && !bean.dependances.isEmpty()) {
					myBuffWriter.write("// import des dépendances\n");
					for(String dep : bean.dependances)
					{
						myBuffWriter.write("import comoneyty.server.beans."+dep+";\n");
					}
				}
				if(ligne.startsWith("package"))
					ligne = "package comoneyty.server.rest;\r\n";
				myBuffWriter.write(ligne+"\n");
			}
			for(String dep : bean.dependances)
			{
				for(String ligne : lignesModeleDep) {
					ligne = ligne.replaceAll("modele", nomBean.toLowerCase());
					ligne = ligne.replaceAll("Modele", nomBean);
					ligne = ligne.replaceAll("objetlie", dep.toLowerCase());
					ligne = ligne.replaceAll("Objetlie", dep);
					myBuffWriter.write(ligne+"\n");
				}
				
			}
			myBuffWriter.write("}\n");
			myBuff.close();
			myBuffWriter.close();
		}

		
	}

}
