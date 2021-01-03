/**
 * 
 */
package PART1;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class CLI {

	static Scanner sc;
	static String[] command;
	static MailSystem sys;
	static MailStore mailStore;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		sc = new Scanner(System.in);  // Create a Scanner object
		
		System.out.println("Welcome");
		
		fileMenu();

		System.out.println("\n\nType 'help' for showing options");
		
		//main loop for the program
		systemLoop: do {
			command = sc.nextLine().split(" ");
			
			switch (command[0]) {
				case "help":	printSysOps();
								break;
								
								
				case "exit":	System.out.println("Good bye, see you soon :)");
								break systemLoop;
								
								
				case "createuser":	if(command.length != 4) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									if(sys.userExists(command[1])) {	//checks if the user nickname is used
										System.out.println("Error: The user nickname is allready used :(");
										break;
									}
									try {	//checks if the introduced year parameter is a number
										sys.createUser(command[1], command[2], Integer.parseInt(command[3]));
							        } catch (NumberFormatException e) {
							        	System.out.println("Error: Incorrect year format");
							        }
									System.out.println("The user: "+command[1]+", named: "+command[2]+" has been created successfully");
									break;
									
				case "sort":		System.out.println("Select a sort option: newer, older, a-z, z-a");
									Comparator<Message> comparator;
									switch(sc.nextLine()){
										case "newer":
											comparator = (o1, o2) -> o2.getDate().compareTo(o1.getDate());
											sys.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "older":
											comparator = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
											sys.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "a-z":
											comparator = (o1, o2) -> o1.getSender().compareTo(o2.getSender());
											sys.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "z-a":
											comparator = ( o1, o2) -> o2.getSender().compareTo(o1.getSender());
											sys.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										default: System.out.println("Not a valid option");
												 break;
									}
									break;
										
					
				case "filter":		System.out.println("Select a filter option: sender, subject, body, after, before,"
													+ " bodylessthan, bodymorethan, subjectlessthan, subjectmorethan");
									Date date;
									int number;
									Predicate<Message> predicate;
									switch(sc.nextLine()){
										case "sender": 
											System.out.println("Introduce the target username: ");
											predicate = o -> o.getSender().equals(sc.nextLine().toLowerCase());
											sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "receiver": 
											System.out.println("Introduce the target username: ");
											predicate = o -> o.getReceiver().equals(sc.nextLine().toLowerCase());
											sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "subject":
											System.out.println("Introduce the target word: ");
											predicate = o -> o.getSubject().contains(sc.nextLine());
											sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "body": 
											System.out.println("Introduce the target word: ");
											predicate = o -> o.getBody().contains(sc.nextLine());
											sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "after":
											System.out.println("Introduce the target date (dd/mm/yyyy): ");
											try {
												date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
												predicate = o -> o.getDate().after(date);
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (ParseException e) {
												System.out.println("Error: Incorrect date format");
											}
											break;
								
										case "before": 
											System.out.println("Introduce the target date (dd/mm/yyyy): ");
											try {
												date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
												predicate = o -> o.getDate().before(date);
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (ParseException e) {
												System.out.println("Error: Incorrect date format");
											}
											break;
											
										case "bodylessthan":
											System.out.println("Introduce the maximum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getBody().split("\\s+").length < number;
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "bodymorethan":
											System.out.println("Introduce the minimum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getBody().split("\\s+").length > number;
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "subjectlessthan":
											System.out.println("Introduce the maximum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getSubject().split("\\s+").length < number;
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "subjectmorethan":
											System.out.println("Introduce the minimum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getSubject().split("\\s+").length > number;
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "senderafter":
											System.out.println("Introduce the minimum year: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												List<String> senders = new ArrayList<>();
												sys.getUsers().stream().filter(s -> s.getYearOfBirth() >= number).forEach(s -> senders.add(s.getUserName())); 
												predicate = o -> senders.contains(o.getSender());
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "senderbefore":
											System.out.println("Introduce the maximum year: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												List<String> senders = new ArrayList<>();
												sys.getUsers().stream().filter(s -> s.getYearOfBirth() < number).forEach(s -> senders.add(s.getUserName())); 
												predicate = o -> senders.contains(o.getSender());
												sys.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										default: System.out.println("Not a valid option");
												 break;
									}
									break;
									
									
						
				case "logas":		if(command.length != 2) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									if(!sys.userExists(command[1])) {
										System.out.println("Error: The user does't exist");
										break;
									}
									System.out.println("You has logged as: "+command[1]+" type 'help' for showing options");
									userLoop(command[1]);
									break;
									
				case "list":		if(command.length != 1) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									System.out.println("List of all messages:");
									List<Message> list = sys.getAllMessages();
									list.stream().forEach(s -> {
										System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody());
									});
									
				break;
								
				default:		System.out.println("Error: command not found");
			}
			
	
		} while(true);
		sc.close();
	}
	
	public static void userLoop(String userName) {
		MailBox mBox = sys.getMailBox(userName);
		userLoop: do {
			
			command = sc.nextLine().split(" ");
					
			switch (command[0]) {
				case "help":	printUserOps();
								break;
								
								
								
				case "send":		if(command.length != 2) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									if(!sys.userExists(command[1])) {	//checks if the user nickname is used
										System.out.println("Error: The user nickname doesn't exist :(");
										break;
									}
									System.out.println("Insert a subject for the mail");
									String subject = sc.nextLine();
									System.out.println("Insert a body for the mail");
									String body = sc.nextLine();
									mBox.sendMail(command[1], subject, body);
			
									break;
										
					
				case "update":		if(command.length != 1) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									mBox.updateMail();
									break;
									
				case "list":		if(command.length != 1) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									System.out.println("List of messages:");
									List<Message> list = mBox.listMail();
									list.stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
																s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
									break;
					
				case "sort":		System.out.println("Select a sort option: newer, older, a-z, z-a");
									Comparator<Message> comparator;
									switch(sc.nextLine()){
										case "newer":
											comparator = (o1, o2) -> o2.getDate().compareTo(o1.getDate());
											mBox.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "older":
											comparator = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
											mBox.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "a-z":
											comparator = (o1, o2) -> o1.getSender().compareTo(o2.getSender());
											mBox.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "z-a":
											comparator = ( o1, o2) -> o2.getSender().compareTo(o1.getSender());
											mBox.sortBy(comparator).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										default: System.out.println("Not a valid option");
												 break;
									}
									break;

					
				case "filter":		System.out.println("Select a filter option: sender, subject, body, after, before,"
														+ " bodylessthan, bodymorethan, subjectlessthan, subjectmorethan");
									Date date;
									int number;
									Predicate<Message> predicate;
									switch(sc.nextLine()){
										case "sender": 
											System.out.println("Introduce the target username: ");
											predicate = o -> o.getSender().equals(sc.nextLine().toLowerCase());
											mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "subject":
											System.out.println("Introduce the target word: ");
											predicate = o -> o.getSubject().contains(sc.nextLine());
											mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "body": 
											System.out.println("Introduce the target word: ");
											predicate = o -> o.getBody().contains(sc.nextLine());
											mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
													s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											break;
											
										case "after":
											System.out.println("Introduce the target date (dd/mm/yyyy): ");
											try {
												date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
												predicate = o -> o.getDate().after(date);
												mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (ParseException e) {
												System.out.println("Error: Incorrect date format");
											}
											break;
	
										case "before": 
											System.out.println("Introduce the target date (dd/mm/yyyy): ");
											try {
												date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
												predicate = o -> o.getDate().before(date);
												mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (ParseException e) {
												System.out.println("Error: Incorrect date format");
											}
											break;
											
										case "bodylessthan":
											System.out.println("Introduce the maximum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getBody().split("\\s+").length < number;
												mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "bodymorethan":
											System.out.println("Introduce the minimum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getBody().split("\\s+").length > number;
												mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "subjectlessthan":
											System.out.println("Introduce the maximum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getSubject().split("\\s+").length < number;
												mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										case "subjectmorethan":
											System.out.println("Introduce the minimum length: ");
											try{
												number = Integer.parseInt(sc.nextLine());
												predicate = o -> o.getSubject().split("\\s+").length > number;
												mBox.filterBy(predicate).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
														s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
											} catch (NumberFormatException e) {
									        	System.out.println("Error: Incorrect number format");
									        }
											break;
											
										default: System.out.println("Not a valid option");
												 break;
									}
					
									/*
									if(command.length != 3) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									try {
										mBox.filterBy(command[1], command[2]).stream().forEach(s ->  System.out.println("Message from: "+s.getSender()+", to: "+
																									s.getReceiver()+"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()));
									} catch (ParseException e) {
										System.out.println("Error: Incorrect date format");
									}catch (NumberFormatException e) {
							        	System.out.println("Error: Incorrect number format");
							        }
							        */
									break;
						
				case "logout":		System.out.println("Good bye "+userName+", see you soon :)");
									break userLoop;
								
				default:		System.out.println("Error: command not found");
			}
		}while(true);
	}
	
	
	
	public static void printSysOps() {
		System.out.println("\n\nSys operations: ");
		System.out.println("\ncreateuser <user nickname> <name> <year of birth (yyyy)> : Create a new user as admin");
		System.out.println("\nfilter <filter type> <condition>: Filter at a system level. The program has implemented several conditions for "
						+ "\nfiltering messages and can be referenced from the command. For instance: "
						+ "\n\t- sender <word> : filters all messages sent by a sender"
						+ "\n\t- receiver <word> : filters all messages sent to a receiver"
						+ "\n\t- subject <word> : filters all messages that contain the word in the subject."
						+ "\n\t- body <word> : filters all messages that contain the word in the body. "
						+ "\n\t- after <n> : filters messages that were sent after a date (dd/MM/yyyy)."
						+ "\n\t- before <n> : filters messages that were sent before a date (dd/MM/yyyy)."
						+ "\n\t- senderafter <n> : filters messages that were sent by a user borned after a year (yyyy)."
						+ "\n\t- senderbefore <n> : filters messages that were sent by a user borned before a year (yyyy)."
						+ "\n\t- lessthan <n> : filters messages that contain less than n words in the body."
						+ "\n\t- morethan <n> : filters messages that contain more than n words in the body.");
		System.out.println("\nlogas <username> : Log in as a user. No passwords");
	}
	
	
	
	public static void printUserOps() {
		System.out.println("\n\nUser operations: ");
		System.out.println("\nsend <to> : send a new message.");
		System.out.println("\nupdate : retrieve messages from the mail store.");
		System.out.println("\nlist : show messages sorted by sent time.");
		System.out.println("\nsort <> : sort messages by some predefined comparators.");
		System.out.println("\nfilter <filter type> <condition>: Filter at user level. The program has implemented several conditions for "
						+ "\nfiltering messages and can be referenced from the command. For instance: "
						+ "\n\t- sender <word> : filters all messages sent by a sender"
						+ "\n\t- receiver <word> : filters all messages sent to a receiver"
						+ "\n\t- subject <word> : filters all messages that contain the word in the subject."
						+ "\n\t- body <word> : filters all messages that contain the word in the body. "
						+ "\n\t- after <n> : filters messages that were sent after a date (dd/MM/yyyy)."
						+ "\n\t- before <n> : filters messages that were sent before a date (dd/MM/yyyy)."
						+ "\n\t- senderafter <n> : filters messages that were sent by a user borned after a year (yyyy)."
						+ "\n\t- senderbefore <n> : filters messages that were sent by a user borned before a year (yyyy)."
						+ "\n\t- lessthan <n> : filters messages that contain less than n words in the body."
						+ "\n\t- morethan <n> : filters messages that contain more than n words in the body.");
		System.out.println("\nlogout : Exit from user to system.");
	}
	
	public static void fileMenu() {
		//loop for selecting a MailSystem from memory or from a file
		//if the file does't exist we continue asking
		String line;
		File f;
		fileNameLoop: do {
			System.out.println("\n\nWhich type of MailStore do you want to use?"
				+ "\n\t-'memory' if you want a MailStore from memory"
				+ "\n\t-'file' if you want to load from a file (with the extension)"
				+ "\n\t-'new file' if you want to create a new file (with the extension)");
			line = sc.nextLine();
			switch (line) {
			
				case "memory":		mailStore = new MailStoreMem();
									sys = new MailSystem(mailStore);
									break fileNameLoop;
								
				case "file":		System.out.println("Introduce file name: ");
									line = sc.nextLine();
									f = new File(line);
									if(f.exists()) {
										mailStore = new MailStoreFile(line);
										sys = new MailSystem(mailStore);
										break fileNameLoop;
									} 
									else{
										System.out.println("Error, file not found");
										break;
									}
								
				case "new file":	System.out.println("Introduce file name: ");
									line = sc.nextLine();
									f = new File(line);
									try {
										f.createNewFile();
										mailStore = new MailStoreFile(line);
										sys = new MailSystem(mailStore);
										break fileNameLoop;
									} catch (IOException e) {
										System.out.println("Error, can't create the new file");
										break;
									}
			}
			
		}while(true);
	}
}
