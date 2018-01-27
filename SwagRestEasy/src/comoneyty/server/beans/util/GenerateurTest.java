package comoneyty.server.beans.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import comoneyty.server.beans.Credential;
import comoneyty.server.beans.Depense;
import comoneyty.server.beans.Event;
import comoneyty.server.beans.LienEventUser;
import comoneyty.server.beans.Modele;
import comoneyty.server.beans.Operation;
import comoneyty.server.beans.Relation;
import comoneyty.server.beans.User;
import comoneyty.server.beans.type.TypeOperation;
import comoneyty.server.rest.util.Accesseur;
import comoneyty.server.rest.util.PasswordEncoder;
import comoneyty.server.rest.util.Utilitaire;

public class GenerateurTest {

	public static void main(String[] args)

	{
		new Accesseur<>().init();
		try {
			initialisation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initialisation() throws Exception {
		//String password = Base64.getEncoder().encodeToString(new String("CoMoneyTy").getBytes());
		String password = PasswordEncoder.GenerePasswordHash("CoMoneyTy");
		
		User user1 = new User("Bergère", "Tony",  "tony.bergere@gmail.com", "user/avatarTony.png",
				"0642663846");
		User user2 = new User("Combey", "Hervé",  "hcombey@gmail.com", "user/avatarHerve.png",
				"0682667921");
		User user6 = new User("Boldron", "François", "francois.boldron@labanquepostale.fr",
				"user/Letter-F-icon.png", "0643213056");
		User user7 = new User("Fay", "Stephane",  "stfay@netc.fr", "user/Letter-S-icon.png",
				"0669082337");

		User user0 = new User("Administrateur", "CoMoneyTy",  "hcombey@gmail.com", "user/Neo.png","0682667921");
		user0.setId("1111-1111-1111-1111");

		ArrayList<User> listeUser = new ArrayList<User>();
		ArrayList<Credential> listeCredential = new ArrayList<Credential>();
		listeUser.add(user0);
		listeUser.add(user1);
		listeUser.add(user2);
		listeUser.add(user6);
		listeUser.add(user7);
		
		for (User user : listeUser) {
			user.setIban(donneIBAN());
		} 
		for (User user : listeUser) {
			Credential cred = new Credential(user.getNom(), password);
			cred.setIdUser(user.getId());
			cred.setNbTentative(3);
			listeCredential.add(cred);
		}


		ArrayList<Relation> relations = new ArrayList<Relation>();

		for (User auser1 : listeUser) {
			for (User auser2 : listeUser) {
				if (!auser1.getId().equals(auser2.getId())) {
					Relation aRelation = new Relation(auser1.getId(), auser2.getId());
					relations.add(aRelation);
				}
			}
		}

		Event event1 = new Event("Anniversaire Tony", new Date("2017/01/06"), "event/photoEvent1.png");
		Event event2 = new Event("Soirée Karting", new Date("2017/03/21"), "event/photoEvent2.png");
		Event event3 = new Event("Sortie ski", new Date("2017/02/15"), "event/photoEvent3.png");
		Event event4 = new Event("Vacances de Pâques", new Date("2017/04/07"), "event/photoEvent4.png");
		Event event5 = new Event("AfterWork La Poste", new Date("2017/08/07"), "event/afterwork.jpg");
		Event event6 = new Event("Soirée Séminaire", new Date("2017/10/07"), "event/seminaireLBP.jpg");

		ArrayList<Event> listeEvent = new ArrayList<Event>();
		listeEvent.add(event1);
		listeEvent.add(event2);
		listeEvent.add(event3);
		listeEvent.add(event4);
		listeEvent.add(event5);
		listeEvent.add(event6);

		ArrayList<User> participants1 = new ArrayList<User>();
		participants1.add(user1);
		participants1.add(user2);
		event1.setParticipants(participants1);

		ArrayList<User> participants2 = new ArrayList<User>();
		participants2.add(user1);
		participants2.add(user2);
		event2.setParticipants(participants2);

		ArrayList<User> participants3 = new ArrayList<User>();
		participants3.add(user1);
		participants3.add(user2);
		event3.setParticipants(participants3);

		ArrayList<User> participants4 = new ArrayList<User>();
		participants4.add(user2);
		event4.setParticipants(participants4);

		ArrayList<User> participants5 = new ArrayList<User>();
		participants5.add(user1);
		participants5.add(user2);
		participants5.add(user6);
		participants5.add(user7);
		event5.setParticipants(participants5);

		ArrayList<User> participants6 = new ArrayList<User>();
		participants6.add(user1);
		participants6.add(user6);
		participants6.add(user7);
		event6.setParticipants(participants6);

		ArrayList<Operation> listeOperation = new ArrayList<Operation>();
		ArrayList<Operation> listeOperationReference = new ArrayList<Operation>();
		BufferedReader myBuff = new BufferedReader(
				new FileReader(new Modele().getClass().getResource("listeOperationTest.txt").getFile()));
		String str = myBuff.readLine();
		while (str != null) {
			Operation ope = new Operation();
			String[] tab = str.split(";");
			ope.setDate(Utilitaire.FORMAT_DATE_STANDARD.parse(tab[0]));
			ope.setDescription(tab[1]);
			ope.setMontant(Double.parseDouble(tab[2].replaceAll(",", ".")) );
			listeOperationReference.add(ope);
			str = myBuff.readLine();
		}

		for (User usr : listeUser) {
			int nbMouvement = new Double(Math.random() * 100).intValue();
			while (nbMouvement < 50)
				nbMouvement = new Double(Math.random() * 100).intValue();

			for (int i = 0; i < nbMouvement; i++) {
				Operation ope1 = (Operation) donneHasard(listeOperationReference);
				Operation ope = new Operation();
				ope.setDate(ope1.getDate());
				ope.setDescription(ope1.getDescription());
				ope.setMontant(ope1.getMontant()* Math.random());
				ope.setIdUser(usr.getId());
				ope.setIbanEmetteur(donneIBAN());
				ope.setIbanDestinataire(donneIBAN());
				TypeOperation type = new TypeOperation(1, "Virement");
				double test = Math.random();
				if (ope.getMontant() < 0) {
					if (test > 0.8) {
						type = new TypeOperation(2, "Prélèvement");

					} else if (test > 0.7) {
						type = new TypeOperation(3, "Règlement CB");

					} else if (test > 0.4) {
						type = new TypeOperation(3, "Chèque");

					} else {
						// virement
						if (Math.random() > 0.7) {
							User userEmetteur = (User) donneHasard(listeUser, usr.getId());
							ope.setIbanEmetteur(userEmetteur.getIban());
						}
					}
				} else {
					if (test > 0.8) {
						type = new TypeOperation(2, "Prélèvement");
					} else if (test > 0.4) {
						type = new TypeOperation(3, "Depot Chèque");
					} else {
						// virement
						if (Math.random() > 0.7) {
							User userEmetteur = (User) donneHasard(listeUser, usr.getId());
							ope.setIbanEmetteur(userEmetteur.getIban());
						}
					}

				}

				ope.setTypeOperation(type);
				listeOperation.add(ope);
			}

		}

		for (User usr : listeUser) {
			int nbMouvement = new Double(Math.random() * 15).intValue();
			while (nbMouvement < 8)
				nbMouvement = new Double(Math.random() * 15).intValue();

			for (int i = 0; i < nbMouvement; i++) {
				Operation ope = new Operation(usr.getId(), donneDate(),"Virement reçu - Ref"+completePar0(new Double(Math.random() * 1000).intValue() + "", 4),
						new Double(Math.random() * 1500).doubleValue() / 10);
				ope.setIbanEmetteur(donneIBAN());
				ope.setIbanDestinataire(donneIBAN());
				TypeOperation type = new TypeOperation(1, "Virement");
				if(Math.random()>0.7) {
					User userEmetteur = (User) donneHasard(listeUser, usr.getId());
					ope.setIbanEmetteur(userEmetteur.getIban());
				}
				ope.setTypeOperation(type);
				listeOperation.add(ope);
			}

		}

		ArrayList<LienEventUser> listeLien = new ArrayList<>();
		for (Event event : listeEvent) {
			boolean first = true;
			for (User user : event.participants) {
				LienEventUser rel = new LienEventUser(user.getId(), event.getId());
				rel.setRoles(new ArrayList<>());
				if (first) {
					first = false;
					rel.getRoles().add("Createur");
				}
				rel.getRoles().add("Participant");
				listeLien.add(rel);
			}
		}

		ArrayList<Depense> listeDepense = new ArrayList<>();
		ArrayList<String> depenses = new ArrayList();
		depenses.add("Bière");
		depenses.add("Apéritif");
		depenses.add("Entrées");
		depenses.add("Taxi");
		for (int i = 0; i < 5 + Math.random() * 5; i++) {
			Depense dep = new Depense((String) donneHasard(depenses), 50 * Math.random());
			dep.setIdEvent(event5.getId());
			dep.setDate(event5.getDate());
			dep.setIdPayeur(((User) donneHasard(participants5)).getId());
			listeDepense.add(dep);
		}
		for (int i = 0; i < 4 + Math.random() * 5; i++) {
			Depense dep = new Depense((String) donneHasard(depenses), 50 * Math.random());
			dep.setIdEvent(event6.getId());
			dep.setDate(event6.getDate());
			dep.setIdPayeur(((User) donneHasard(participants6)).getId());
			listeDepense.add(dep);
		}

		new Accesseur().deleteAll();

		System.out.println("START");

		int nb = 0;

		for (User user : listeUser) {	
			new Accesseur().save(user);
			nb++;
		}
		for (Credential user : listeCredential) {
			new Accesseur().save(user);
			nb++;
		}

		for (Depense depense : listeDepense) {
			new Accesseur().save(depense);
			nb++;
		}

		for (Relation relation : relations) {	
			new Accesseur().save(relation);
			nb++;
		}

		for (Event event : listeEvent) {
			new Accesseur().save(event);
			nb++;
		}

		for (Operation ope : listeOperation) {
			new Accesseur().save(ope);
				nb++;
		}

		for (LienEventUser lien : listeLien)
		{
			new Accesseur().save(lien);
			nb++;

		}

		System.out.println("STOP");

	
	}

	public static Date donneDate()

	{

		int m = new Double(9+Math.random() * 2).intValue() ;

		int j = new Double(Math.random() * 25).intValue() + 1;

		return new Date("2017/" + (m < 10 ? "0" + m : "" + m) + "/" + (j < 10 ? "0" + j : "" + j));

	}

	public static Object donneHasard(ArrayList liste)

	{

		int idx = new Double(Math.random() * liste.size()).intValue();

		return liste.get(idx);

	}

	private static String completePar0(String s, int len)

	{

		int reste = s.length() - len;

		if (reste < 0)

		{

			for (int i = 0; i < reste; i++)

				s = "0" + s;

		} else

			s = s.substring(0, len);

		return s;

	}

	public static String donneIBAN()

	{

		String str = "FR61";
		for (int i = 0; i < 5; i++)
			str += "-" + completePar0(new Double(Math.random() * 1000).intValue() + "", 4);

		str += "-" + completePar0(new Double(Math.random() * 1000).intValue() + "", 3);

		return str;

	}

	public static ObjetId donneHasard(ArrayList liste, String exception)

	{

		int idx = new Double(Math.random() * liste.size()).intValue();

		ObjetId res = (ObjetId) liste.get(idx);

		while (res.getId().equals(exception))

		{

			idx = new Double(Math.random() * liste.size()).intValue();

			res = (ObjetId) liste.get(idx);

		}

		return res;

	}

}
