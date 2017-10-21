package mybank.server.beans;

import mybank.server.rest.EventRest;
import mybank.server.rest.util.Accesseur;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Accesseur.init();
			String idEvent= "1448630e-092f-4717-aaba-6405a2500f34";
			EventRest.bilan(idEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
