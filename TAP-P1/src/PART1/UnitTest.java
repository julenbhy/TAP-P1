/**
 * 
 */
package PART1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class UnitTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		
		MailSystem mSystem = new MailSystem();
		MailBox albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		MailBox julenbhy = mSystem.createUser("Julenbhy", "Julen", 1899);
		MailBox albertoChicote = mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		MailBox albertoCFC = mSystem.createUser("AlbertoChicoteFansClub", "FansClub", 2007);
		
		albigle.sendMail("Julenbhy", "subject", "body");
		albigle.sendMail("Albigle", "Hola", "Hola a mi mismo");
		albigle.sendMail("Julenbhy", "Eguberri on", "Hola julen, eguberri on eta urte berri on");
		albigle.sendMail("Julenbhy", "subject2", "body");
		albertoChicote.sendMail("Julenbhy", "subject", "body");
		albertoChicote.sendMail("Julenbhy", "non subject", "Hola, gracias por ser mi fan nº 1 :)");
		julenbhy.sendMail("Albigle", "Hola", "Muy buenas");
		albigle.sendMail("AlbertoChicote", "subjecyt", "Body");
		albertoCFC.sendMail("AlbertoChicote", "Somos tus fans", "Somos tus fans nº 1 nos sigues xfaa");
		albertoCFC.sendMail("AlbertoChicote", "Felicidades Alberto", "Nos gusta mucho pesadilla en la cocina");
		albertoCFC.sendMail("JulenBhy", "Bienvenido Julen", "Gracias por formar parte del club");
		
		System.out.println("\nStep 5: list the mailbox messages");
		System.out.println(" - albigle's mailbox");
		albigle.updateMail();	//update sorts by time by default
		albigle.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
													"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" - julenbhy's mailbox");
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
													"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		
		System.out.println("\nStep 6: list the messages by sender\n - julenbhy's mailbox filter by mails received from albigle");
		julenbhy.filterBy("sender", "Albigle").stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		
		System.out.println("\nStep 7: filter the messages");
		System.out.println(" - The message subject contains 'subjetc' word");
		julenbhy.filterBy("subject", "subject").stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" -  The message sender is a certain user");
		System.out.println("   - julenbhy's mailbox filter by mails received from albigle");
		julenbhy.filterBy("sender", "Albigle").stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("   - julenbhy's mailbox filter by mails received from AlbertoChicote");
		julenbhy.filterBy("sender", "AlbertoChicote").stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
			
		System.out.println("\nStep 8: use the mail system object to retrieve all messages and print them");
		mSystem.getAllMessages().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
														"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\nStep 9: filter messages globally that fulfill the following conditions");
		System.out.println(" - The message subject is a single word, aka there are less than 2 words");
		mSystem.filterBy("subjectlessthan", "2").stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" -  The sender was born after year 2000");
		mSystem.filterBy("senderafter", "2000").stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));

		System.out.println("\nStep 10: get the count of messages in the system and print it");
		System.out.println("Number of messages at the system: "+mSystem.messageAmount());
		System.out.println("Messages:");
		mSystem.getAllMessages().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));

		System.out.println("\nStep 11: get the average number of messages received per user and print it");
		System.out.println("Average: "+mSystem.averageMessages());
		
		System.out.println("\nStep 12: group the messages per subject in a Map<String, List<Message>> and print it");
		mSystem.getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject)).forEach((key, value) -> {System.out.println("Subject: "+key); value.forEach(s -> System.out.println(" -Body: "+s.getBody()));});	
		
		System.out.println("\nStep 13: count the words of all messages sent by users with a certain real name");
		System.out.println(mSystem.countWords("julenbhy"));
			
		//para comprobar la ordenacion
		/*
				List<Message> lista = new ArrayList<Message>();
				Message a = new Message("Alberto", "Jon", "Me perdonas?", "el bodkjmhnybgtyfvtrcvgbhy");
				TimeUnit.SECONDS.sleep(2);
				Message b = new Message("Alberto", "Julen", "kuku", "treaadsfasd");
				TimeUnit.SECONDS.sleep(2);
				Message c = new Message("kdasfjf", "Jon", "Gilipollas", "edafsvgbhy");
				
				
				
				lista.add(b);
				lista.add(a);
				lista.add(c);
				lista.add(a);
				
				String fecha = "31/11/2020";
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
				System.out.println(date);
				
				System.out.println(lista);
				lista = lista.stream()
						  .sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
						  .collect(Collectors.toList());
				System.out.println("lista ordenada:" + lista);
				Collections.reverse(lista);
				System.out.println("lista invertida:" + lista);
				*/
	}

}
