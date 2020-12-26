/**
 * 
 */
package PART2;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import PART1.*;



/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class UnitTest2 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub

		//Step 1: Initialize the mail system
		MailSystemObservers mSystem = new MailSystemObservers();
		//Step 2: Create 3 users
		MailBox albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		ObservableMailBox julenbhy = (ObservableMailBox) mSystem.createUser("Julenbhy", "Julen", 1899);
		MailBox albertoChicote = mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		MailBox albertoCFC = mSystem.createUser("AlbertoChicoteFansClub", "FansClub", 2007);
		//Step 3: send a few email
		albigle.sendMail("Julenbhy", "subject", "body para julen");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("Albigle", "Hola", "Hola a mi mismo");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("Julenbhy", "Eguberri on", "Hola julen, eguberri on eta urte berri on");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("Julenbhy", "subject2", "segundo body para body");
		TimeUnit.SECONDS.sleep(1);
		albertoChicote.sendMail("Julenbhy", "subject", "otro body para julen");
		TimeUnit.SECONDS.sleep(1);
		albertoChicote.sendMail("Julenbhy", "non subject", "Hola, gracias por ser mi fan n� 1 :) a a a a a a a a a a  a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a ");
		TimeUnit.SECONDS.sleep(1);
		julenbhy.sendMail("Albigle", "Hola", "Muy buenas");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("AlbertoChicote", "subject", "Body para chicote");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Somos tus fans", "Somos tus fans n� 1 nos sigues xfaa");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Felicidades Alberto", "Nos gusta mucho pesadilla en la cocina");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("JulenBhy", "Bienvenido Julen", "Gracias por formar parte del club");
		

		//Step 4: Update a mail box
		albigle.updateMail();	//update sorts by time by default
		System.out.println("\nStep 5: list the mailbox messages");
		System.out.println(" - albigle's mailbox");
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
		System.out.println("The word count send by julen: " + mSystem.countWords("julen"));
		System.out.println("\nStep 14: count using the name that you used on two users");
		System.out.println("The word count send by alberto (Albigle and Chichote): " + mSystem.countWords("alberto"));
			
		System.out.println("\nStep 15: Print the messages received by users born before year 2000.");
		System.out.println();
		
		julenbhy.addObserver(new TooLongFilter());
		julenbhy.addObserver(new SpamUserFilter("albigle"));
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		

	}

}
