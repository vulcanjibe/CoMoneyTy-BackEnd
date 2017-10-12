package mybank.server.beans;

import java.util.ArrayList;

import mybank.server.rest.EventRest;
import mybank.server.rest.util.Accesseur;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Accesseur.init();
			String idEvent= "1448630e-092f-4717-aaba-6405a2500f34";
			 ArrayList<Mouvement> liste = EventRest.bilan(idEvent);
			 for(Mouvement mvt : liste)
			 {
				 System.out.println(mvt.idEmetteur+" -> "+mvt.idDestinataire+" : "+mvt.montant);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
