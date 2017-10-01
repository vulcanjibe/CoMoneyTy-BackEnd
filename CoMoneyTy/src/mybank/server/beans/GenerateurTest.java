package mybank.server.beans;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

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
		ArrayList<User> listeUser = new ArrayList<User>();
		listeUser.add(user1);
		listeUser.add(user2);
		listeUser.add(user3);
		listeUser.add(user4);
		listeUser.add(user5);
		
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
		
		Event event1 = new Event("Anniversaire Tony", new Date("2017/01/06"), 100, "event/photoEvent1.png");
		Event event2 = new Event("Soirée Karting", new Date("2017/03/21"), -50, "event/photoEvent2.png");
		Event event3 = new Event("Sortie ski", new Date("2017/02/15"), 130, "event/photoEvent3.png");
		Event event4 = new Event("Vacances de Pâques", new Date("2017/04/07"), -10, "event/photoEvent4.png");
		
		ArrayList<Event> listeEvent = new ArrayList<Event>();
		listeEvent.add(event1);
		listeEvent.add(event2);
		listeEvent.add(event3);
		listeEvent.add(event4);
		
		
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
		
		ArrayList<Operation> listeOperation = new ArrayList<Operation>();
		for(User usr : listeUser)
		{
			int nbMouvement = new Double(Math.random()*50).intValue();
			while(nbMouvement<20)
				nbMouvement = new Double(Math.random()*50).intValue();
			for(int i=0;i<nbMouvement;i++) {
				Operation ope = new Operation(usr.id, donneDate(),"Operation "+new Double(Math.random()*10000).intValue() , new Double(Math.random()*1500).doubleValue()/10);
				ope.ibanEmetteur="IBAN Emet";
				ope.ibanDestinataire="IBAN Dest";
				TypeOperation type = new TypeOperation(1, "Virement");
				double test =Math.random();
				if(test>0.8) {
					type = new TypeOperation(2, "Prélèvement");
					ope.setMontant(-ope.getMontant());
				} else if(test>0.5) {
					type = new TypeOperation(3, "Règlement CB");
					ope.setMontant(-ope.getMontant());
				}  else if(test>0.4) {
					type = new TypeOperation(3, "Chèque");
					ope.setMontant(-ope.getMontant());
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
		
		for(User user : listeUser)
		{
			Accesseur.save(user);
		}
		
		for(Relation relation : relations)
		{
			Accesseur.save(relation);
		}

		for(Event event : listeEvent)
		{
			Accesseur.save(event);
		}

		for(Operation ope : listeOperation)
		{
			Accesseur.save(ope);
		} 

		for(LienEventUser lien : listeLien)
		{
			Accesseur.save(lien);
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
