/**
 * 
 */
package PART1;

import java.text.ParseException;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
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

		//Step 1: Initialize the mail system
		MailStore mailStore = new MailStoreMem();
		MailSystem mSystem = new MailSystem(mailStore);
		Predicate<Message> predicate;
		Comparator<Message> comparator;
		//Step 2: Create 4 users
		MailBox albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		MailBox julenbhy = mSystem.createUser("Julenbhy", "Julen", 1899);
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
		albertoChicote.sendMail("Julenbhy", "non subject", "Hola, gracias por ser mi fan numero 1 :)");
		TimeUnit.SECONDS.sleep(1);
		julenbhy.sendMail("Albigle", "Hola", "Muy buenas");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("AlbertoChicote", "subject", "Body para chicote");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Somos tus fans", "Somos tus fans numero 1 nos sigues xfaa");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Felicidades Alberto", "Nos gusta mucho pesadilla en la cocina");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("JulenBhy", "Bienvenido Julen", "Gracias por formar parte del club");
		

		//Step 4: Update a mail box
		albigle.updateMail();	//update sorts by time by default
		System.out.println("\nStep 5: list the mailbox messages");
		System.out.println(" - albigle's mailbox");
		albigle.listMail().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
													"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" - julenbhy's mailbox");
		julenbhy.updateMail();
		julenbhy.listMail().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
													"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\nStep 6: list the messages by sender\n - julenbhy's mailbox filter by mails received from albigle");
		predicate = o -> o.getSender().equals("albigle");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\nStep 7: filter the messages");
		System.out.println(" - The message subject contains 'subjetc' word");
		predicate = o -> o.getSubject().contains("subject");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" -  The message sender is a certain user");
		System.out.println("   - julenbhy's mailbox filter by mails received from albigle");
		predicate = o -> o.getSender().equals("albigle");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("   - julenbhy's mailbox filter by mails received from AlbertoChicote");
		predicate = o -> o.getSender().equals("albertochicote");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+																				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
			
		System.out.println("\nStep 8: use the mail system object to retrieve all messages and print them");
		mSystem.getAllMessages().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+														"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\nStep 9: filter messages globally that fulfill the following conditions");
		System.out.println(" - The message subject is a single word, aka there are less than 2 words");
		predicate = o -> o.getSubject().split("\\s+").length < 2;
		mSystem.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" -  The sender was born after year 2000");
/*		predicate = o -> o.getYearOfBirth() >= 2000;
		mSystem.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
*/
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
	
		
		
		//Step 16.1: Now change the mail store to the file implementation.
		mailStore = new MailStoreFile("unitTestFile.txt");
		mSystem = new MailSystem(mailStore);
		//Step 16.2: Create 3 users
		albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		julenbhy = mSystem.createUser("Julenbhy", "Julen", 1899);
		albertoChicote = mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		albertoCFC = mSystem.createUser("AlbertoChicoteFansClub", "FansClub", 2007);
		//Step  16.3: send a few email
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
		albertoChicote.sendMail("Julenbhy", "non subject", "Hola, gracias por ser mi fan n� 1 :)");
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
		

		//Step  16.4: Update a mail box
		albigle.updateMail();	//update sorts by time by default
		System.out.println("\nStep  16.5: list the mailbox messages");
		System.out.println(" - albigle's mailbox");
		albigle.listMail().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
													"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" - julenbhy's mailbox");
		julenbhy.updateMail();
		julenbhy.listMail().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
													"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		
		System.out.println("\nStep 16.6: list the messages by sender\n - julenbhy's mailbox filter by mails received from albigle");
		predicate = o -> o.getSender().equals("Albigle");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\nStep 16.7: filter the messages");
		System.out.println(" - The message subject contains 'subjetc' word");
		predicate = o -> o.getSubject().contains("subject");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" -  The message sender is a certain user");
		System.out.println("   - julenbhy's mailbox filter by mails received from albigle");
		predicate = o -> o.getSender().equals("Albigle");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("   - julenbhy's mailbox filter by mails received from AlbertoChicote");
		predicate = o -> o.getSender().equals("AlbertoChicote");
		julenbhy.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+																				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
			
		System.out.println("\nStep 16.8: use the mail system object to retrieve all messages and print them");
		mSystem.getAllMessages().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+														"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\nStep 16.9: filter messages globally that fulfill the following conditions");
		System.out.println(" - The message subject is a single word, aka there are less than 2 words");
		predicate = o -> o.getSubject().split("\\s+").length < 2;
		mSystem.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println(" -  The sender was born after year 2000");
/*		predicate = o -> o.getYearOfBirth() >= 2000;
		mSystem.filterBy(predicate).forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
																		"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
*/
		System.out.println("\nStep  16.10: get the count of messages in the system and print it");
		System.out.println("Number of messages at the system: "+mSystem.messageAmount());
		System.out.println("Messages:");
		mSystem.getAllMessages().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));

		System.out.println("\nStep  16.11: get the average number of messages received per user and print it");
		System.out.println("Average: "+mSystem.averageMessages());
		
		System.out.println("\nStep  16.12: group the messages per subject in a Map<String, List<Message>> and print it");
		mSystem.groupBySubject().forEach((key, value) -> {System.out.println("Subject: "+key); value.forEach(s -> System.out.println(" -Body: "+s.getBody()));});	
		
		System.out.println("\nStep  16.13: count the words of all messages sent by users with a certain real name");
		System.out.println("The word count send by julen: " + mSystem.countWords("julen"));
		System.out.println("\nStep  16.14: count using the name that you used on two users");
		System.out.println("The word count send by alberto (Albigle and Chichote): " + mSystem.countWords("alberto"));
			
		System.out.println("\nStep  16.15: Print the messages received by users born before year 2000.");
		System.out.println();

	}

}
