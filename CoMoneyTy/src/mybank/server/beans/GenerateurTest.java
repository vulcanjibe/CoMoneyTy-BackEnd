package mybank.server.beans;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


import com.fasterxml.jackson.databind.ObjectMapper;

import mybank.server.rest.util.Accesseur;

public class GenerateurTest {
	public static void main(String[] args) throws Exception
	{
		String password = Base64.getEncoder().encodeToString(new String("CoMoneyTy").getBytes());
		User user1 = new User("Bergère","Tony", "Tony", password, "tony.bergere@gmail.com","avatarTony.png");
		User user2 = new User("Combey","Hervé", "Herve", password, "hcombey@gmail.com","avatarHerve.png");
		User user3 = new User("Bergère","Fatia", "Fatia", password, "fatia.bergere@gmail.com","avatarFatia.png");
		User user4 = new User("Combey","Sylvie", "Sylvie", password, "sylvie.combey@yahoo.fr","avatarSylvie.png");
		User user5 = new User("Combey","Clément", "Clement", password, "clement.combey@gmail.com","avatarClement.png");
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
		
		Event event1 = new Event("Anniversaire Tony", new Date("2017/01/06"), 100, "photoEvent1.png");
		Event event2 = new Event("Soirée Karting", new Date("2017/03/21"), -50, "photoEvent2.png");
		Event event3 = new Event("Sortie ski", new Date("2017/02/15"), 130, "photoEvent3.png");
		Event event4 = new Event("Vacances de Pâques", new Date("2017/04/07"), -10, "photoEvent4.png");
		
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
		
		ArrayList<Mouvement> listeMvt = new ArrayList<Mouvement>();
		for(Event event : listeEvent)
		{
			int nbMouvement = new Double(Math.random()*4).intValue()+1;
			for(int i=0;i<nbMouvement;i++)
			{
				User emetteur = (User) donneHasard(listeUser);
				User destinataire = (User) donneHasard(listeUser,emetteur.getId());
				double montant = Math.random()*150;
		//		if(Math.random()>0.5)
		//			montant=-montant;
		//		Mouvement mvt = new Mouvement(emetteur.getId(), destinataire.getId(), montant, emetteur.getPrenom()+" -> "+destinataire.getPrenom()+" : "+montant+" �", donneDate(), event);
		//		listeMvt.add(mvt);
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

/*		for(Mouvement mvt : listeMvt)
		{
			Accesseur.save(mvt);
		} */

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
