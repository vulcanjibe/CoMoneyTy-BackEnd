package mybank.server.beans;



import java.util.ArrayList;

import java.util.Base64;

import java.util.Date;

import java.util.List;




import com.couchbase.client.java.query.N1qlQuery;

import com.couchbase.client.java.query.N1qlQueryResult;

import com.couchbase.client.java.query.N1qlQueryRow;




import mybank.server.beans.type.TypeOperation;

import mybank.server.rest.util.Accesseur;




public class GenerateurTest {

	

	public static void main(String[] args) throws Exception

	{

		String password = Base64.getEncoder().encodeToString(new String("CoMoneyTy").getBytes());

		User user1 = new User("Bergère","Tony", "Tony", password, "tony.bergere@gmail.com","user/avatarTony.png");
		User user2 = new User("Combey","Hervé", "Herve", password, "hcombey@gmail.com","user/avatarHerve.png");
		User user3 = new User("Bergère","Fatia", "Fatia", password, "fatia.bergere@gmail.com","user/avatarFatia.png");
		User user4 = new User("Combey","Sylvie", "Sylvie", password, "sylvie.combey@yahoo.fr","user/avatarSylvie.png");
		User user5 = new User("Combey","Clément", "Clement", password, "clement.combey@gmail.com","user/avatarClement.png");
		User user6 = new User("Boldron","François", "Francois", password, "francois.boldron@labanquepostale.fr","user/Letter-F-icon.png","0643213056");
		User user7 = new User("Fay","Stephane", "Stephane", password, "stfay@netc.fr","user/Letter-S-icon.png","0669082337");

		ArrayList<User> listeUser = new ArrayList<User>();

		listeUser.add(user1);
		listeUser.add(user2);
		listeUser.add(user3);
		listeUser.add(user4);
		listeUser.add(user5);
		listeUser.add(user6);
		listeUser.add(user7);

		for(User user : listeUser)
			user.setIban(donneIBAN());

		ArrayList<Relation> relations = new ArrayList<Relation>();

		for(User auser1 : listeUser)
		{
			for(User auser2 : listeUser)
			{
				if(!auser1.getId().equals(auser2.id))
				{
					Relation aRelation = new Relation(auser1.id, auser2.id);
					relations.add(aRelation);
				}
			}
		}

		

		Event event1 = new Event("Anniversaire Tony", new Date("2017/01/06"), "event/photoEvent1.png");
		Event event2 = new Event("Soirée Karting", new Date("2017/03/21"),  "event/photoEvent2.png");
		Event event3 = new Event("Sortie ski", new Date("2017/02/15"),  "event/photoEvent3.png");
		Event event4 = new Event("Vacances de Pâques", new Date("2017/04/07"),  "event/photoEvent4.png");
		Event event5 = new Event("AfterWork La Poste", new Date("2017/08/07"),  "event/afterwork.jpg");
		Event event6 = new Event("Soirée Séminaire", new Date("2017/10/07"),  "event/seminaireLBP.jpg");

		

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
		event1.setParticipants(participants1 );

		ArrayList<User> participants2 = new ArrayList<User>();
		participants2.add(user1);
		participants2.add(user2);
		participants2.add(user4);
		participants2.add(user5);
		event2.setParticipants(participants2);


		ArrayList<User> participants3 = new ArrayList<User>();
		participants3.add(user1);
		participants3.add(user2);
		participants3.add(user3);
		event3.setParticipants(participants3 );


		ArrayList<User> participants4 = new ArrayList<User>();
		participants4.add(user2);
		participants4.add(user4);
		participants4.add(user5);
		event4.setParticipants(participants4 );


		ArrayList<User> participants5 = new ArrayList<User>();
		participants5.add(user1);
		participants5.add(user2);
		participants5.add(user6);
		participants5.add(user7);
		event5.setParticipants(participants5 );


		ArrayList<User> participants6 = new ArrayList<User>();
		participants6.add(user1);
		participants6.add(user6);
		participants6.add(user7);
		event6.setParticipants(participants6);


		ArrayList<Operation> listeOperation = new ArrayList<Operation>();

		for(User usr : listeUser)
		{
			int nbMouvement = new Double(Math.random()*50).intValue();
			while(nbMouvement<20)
				nbMouvement = new Double(Math.random()*50).intValue();

			for(int i=0;i<nbMouvement;i++) {

				Operation ope = new Operation(usr.id, donneDate(),"Operation "+new Double(Math.random()*10000).intValue() , new Double(Math.random()*1500).doubleValue()/10);
				ope.ibanEmetteur=donneIBAN();
				ope.ibanDestinataire=donneIBAN();
				TypeOperation type = new TypeOperation(1, "Virement");
				double test =Math.random();
				if(test>0.8) {
					type = new TypeOperation(2, "Prélèvement");
					ope.setMontant(-ope.getMontant());

				} else if(test>0.7) {
					type = new TypeOperation(3, "Règlement CB");
					ope.setMontant(-ope.getMontant());

				}  else if(test>0.4) {
					type = new TypeOperation(3, "Chèque");
					ope.setMontant(-ope.getMontant());

				} else {
					// virement
					if(Math.random()>0.7) {
						User userEmetteur = (User)donneHasard(listeUser, usr.getId());
						ope.ibanEmetteur=userEmetteur.iban;
					}
				}
				ope.setTypeOperation(type);
				listeOperation.add(ope);
			}

		}

		

		ArrayList<LienEventUser> listeLien = new ArrayList<>();
		for(Event event : listeEvent)
		{
			for(User user : event.participants)
			{
				listeLien.add(new LienEventUser(user.getId(),event.getId()));
			}

		}

	//	ObjectMapper mapper = new ObjectMapper();

	//	System.out.println(mapper.writeValueAsString(event1));

		

		Accesseur.init();
		Accesseur.deleteAll();


		System.out.println("START");

		int nb = 0;

		for(User user : listeUser)
		{
			Accesseur.save(user);
			nb++;
		}

		

		for(Relation relation : relations)
		{
			Accesseur.save(relation);
			nb++;
		}




		for(Event event : listeEvent)
		{
			Accesseur.save(event);
			nb++;
		}




		for(Operation ope : listeOperation)
		{
			Accesseur.save(ope);
			nb++;
		} 




		for(LienEventUser lien : listeLien)

		{

			Accesseur.save(lien);

			nb++;

		}

		System.out.println("STOP");

		

		boolean ok = false;

		while(!ok)

		{

			Thread.sleep(1000);

			String requete = "select * from `"+Accesseur.BUCKET_NAME+"`";

			

			

			N1qlQuery query = N1qlQuery.simple(requete);

			N1qlQueryResult result = Accesseur.BUCKET.query(query);

			List<N1qlQueryRow> list = result.allRows();

			

			System.out.println(list.size()+" sur "+nb);

			if(list.size()==nb)

			{

				ok = true;

			}

		}




	}

	public static Date donneDate()

	{

		int m = new Double(Math.random()*11).intValue()+1;

		int j = new Double(Math.random()*25).intValue()+1;

		return new Date("2017/"+(m<10?"0"+m:""+m)+"/"+(j<10?"0"+j:""+j));

	}

	public static Object donneHasard(ArrayList liste)

	{

		int idx = new Double(Math.random()*liste.size()).intValue();

		return liste.get(idx);

	}

	private static String completePar0(String s,int len)

	{

		int reste = s.length()-len;

		if(reste<0)

		{

			for(int i=0;i<reste;i++)

				s="0"+s;

		} else

			s=s.substring(0, len);

		return s;

	}

	public static String donneIBAN()

	{

		String str = "";

		str+=completePar0(new Double(Math.random()*1000).intValue()+"",4)+"-";

		str+=completePar0(new Double(Math.random()*1000).intValue()+"",4)+"-";

		str+=completePar0(new Double(Math.random()*1000).intValue()+"",4)+"-";

		str+=completePar0(new Double(Math.random()*1000).intValue()+"",4);

		return str;

	}

	public static ObjetId donneHasard(ArrayList liste,String exception)

	{

		int idx = new Double(Math.random()*liste.size()).intValue();

		ObjetId res = (ObjetId) liste.get(idx);

		while(res.getId().equals(exception))

		{

			idx = new Double(Math.random()*liste.size()).intValue();

			res = (ObjetId) liste.get(idx);

		}

		return res;

	}

}
