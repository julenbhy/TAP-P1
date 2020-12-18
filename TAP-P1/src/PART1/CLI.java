/**
 * 
 */
package PART1;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class CLI {

	static Scanner sc;
	static String[] command;
	static MailSystem sys;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		sc = new Scanner(System.in);  // Create a Scanner object
		
		System.out.println("Welcome");
		System.out.println("Which type of MailStore do you want to use?"
						+ "\n\t-press enter if you want a MailStore from memory"
						+ "\n\t-enter a file name if you want a MailStore from a file (with the extension)");
		
		//loop for selecting a MailSystem from memory or from a file
		//if the file does't exist we continue asking
		fileNameLoop: do {
			String fileName = sc.nextLine();
			if(fileName == "") {
				sys = new MailSystem();
				break fileNameLoop;
			}
			else {
				try {
					sys = new MailSystem(fileName);
					break fileNameLoop;
				} catch (IOException e) {
					System.out.println("Error: file not found, try again");
				}
			}
		}while(true);
		

		System.out.println("\n\nType 'help' for showing options");
		
		//main loop for the program
		systemLoop: do {
			command = sc.nextLine().split(" ");
			
			switch (command[0]) {
				case "help":	System.out.println("Sys operations: ");
								System.out.println("createuser <user nickname> <name> <year of birth (yyyy)> : Create a new user as admin");
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
									break;
										
					
				case "filter":		if(command.length != 3) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									try {
										sys.filterBy(command[1], command[2]);
									} catch (ParseException e) {
										System.out.println("Error: Incorrect date format");
									}catch (NumberFormatException e) {
							        	System.out.println("Error: Incorrect number format");
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
									userLoop(command[1]);
									break;
								
				default:		System.out.println("Error: command not found");
			}
			
	
		} while(true);
		sc.close();
	}
	
	public static void userLoop(String userName) {
		userLoop: do {
			command = sc.nextLine().split(" ");
			
			switch (command[0]) {
				case "help":	System.out.println("User operations: ");
								System.out.println("send <to> \"subject\" \"body\" : send a new message.");
								System.out.println("update : retrieve messages from the mail store.");
								System.out.println("list : show messages sorted by sent time.");
								System.out.println("sort <> : sort messages by some predefined comparators.");
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
								System.out.println("\nlogout : Exit from user to system.");
								break;
								
								
								
				case "send":		if(command.length != 4) {	//checks if the number of arguments is correct
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
									break;
										
					
				case "filter":		if(command.length != 3) {	//checks if the number of arguments is correct
										System.out.println("Error: Incorrect args");
										break;
									}
									try {
										sys.filterBy(command[1], command[2]);
									} catch (ParseException e) {
										System.out.println("Error: Incorrect date format");
									}catch (NumberFormatException e) {
							        	System.out.println("Error: Incorrect number format");
							        }
									break;
									
						
				case "logout":		break userLoop;
								
				default:		System.out.println("Error: command not found");
			}
		}while(true);
	}
}
