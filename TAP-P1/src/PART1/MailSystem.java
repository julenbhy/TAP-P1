/**
 * 
 */
package PART1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailSystem {
	
	private List<User> users;
	private List<MailBox> mailBoxes;
	private MailStore mailStore;

	/**
	 * constructor for MailSystem using normal MailStore
	 */
	public MailSystem() {
		users = new ArrayList<User>();
		mailBoxes = new ArrayList<MailBox>();
		mailStore = new MailStoreMem();
	}
	
	
	/**
	 * constructor for MailSystem using MailStoreFile
	 * @param fileName
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public MailSystem(String fileName) {
		mailStore = new MailStoreFile(fileName);
		users = new ArrayList<User>();
		mailBoxes = new ArrayList<MailBox>();
	}
	
	
	/**
	 * 
	 * @param userName
	 * @param name
	 * @param yearOfBirth
	 * @return
	 */
	public MailBox createUser(String userName, String name, int yearOfBirth) {
		User user = new User(userName, name, yearOfBirth);
		if(!this.userExists(userName)) {
			MailBox mailBox = new MailBox(user, mailStore);
			users.add(user);
			mailBoxes.add(mailBox);
			return mailBox;
		}
		return null;
	}
	
	
	/**
	 * Returns the MailBox of a certain userName
	 * @param userName
	 * @return the MailBox
	 */
	public MailBox getMailBox(String userName) {
		if(this.userExists(userName)) {
			int i = 0;
			for(User elem: users) {
				if(elem.getUserName().equals(userName)) return this.mailBoxes.get(i);
				i++;
			}
		}
		return null;
	}
	
	public List<Message> getSentMessages(String userName){
		List<Message> result = new ArrayList<Message>();
		if(this.userExists(userName)) {
			result = this.getAllMessages().stream()
											.filter(i -> i.getSender().equals(userName))
											.collect(Collectors.toList());
		}
		return result;
	}
	
	/**
	 * Returns the User object of a certain userName
	 * @param userName
	 * @return the User
	 */
	public User getUser(String userName) {
		if(this.userExists(userName)) {
			for(User elem: users) if(elem.getUserName().equals(userName)) return elem;
		}
		return null;
	}
	

	
	/**
	 * Checks if the userName is already used
	 * @param userName
	 * @return true if it is used
	 */
	public boolean userExists(String userName) {
		for(User elem: users) {
			if(elem.getUserName().equals(userName)) return true;
		}
		return false;
	}
	
	/**
	 * Gets all the messages in the system
	 * @return a List with all the messages
	 */
	public List<Message> getAllMessages(){
		try{
			return mailStore.getAllMessages();
		}
		catch(Exception e) {
			System.out.println("Error: can't find the file");
			return null;
		}
	}
	
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}


	/**
	 * 
	 * @param condition The condition to sort (newer, older, a-z or z-a)
	 * @return
	 */
	
	public List<Message> sortBy(String condition){
		switch(condition){
		
		case "newer": 
			List<Message> result = this.getAllMessages().stream()
														.sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
														.collect(Collectors.toList());
			Collections.reverse(result);	
			return result;
			
			
		case "older": 
			return this.getAllMessages().stream()
										.sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
										.collect(Collectors.toList());
			
			
		case "a-z":
			return this.getAllMessages().stream()
										.sorted((o1,o2) -> o1.getSender().compareTo(o2.getSender()))
										.collect(Collectors.toList());
			
			
		case "z-a":
			List<Message> resultAZ = this.getAllMessages().stream()
															.sorted((o1,o2) -> o1.getSender().compareTo(o2.getSender()))
															.collect(Collectors.toList());
			Collections.reverse(resultAZ);	
			return resultAZ;
			
		
		default: return null;
		}
	}
	
	
	
	
	/**
	 * filter all de messages on the system by a given parameter
	 * @param condition The condition to filter (sender, subject, body or date)
	 * date must be format: dd/mm/yyyy
	 * @param word The string it must search
	 * @return the filtered list
	 * @throws ParseException 
	 */
	public List<Message> filterBy(String condition, String word) throws ParseException, NumberFormatException{

		Date date;
		int year;
		
		switch(condition){
		
		case "sender": 
			return this.getAllMessages().stream()
										.filter(o -> o.getSender().equals(word)).collect(Collectors.toList());
			
		case "receiver": 
			return this.getAllMessages().stream()
										.filter(o -> o.getReceiver().equals(word)).collect(Collectors.toList());

		case "subject":
			return this.getAllMessages().stream()
										.filter(o -> o.getSubject().contains(word)).collect(Collectors.toList());

		case "body": 
			return this.getAllMessages().stream()
										.filter(o -> o.getBody().contains(word)).collect(Collectors.toList());

		case "after": 
			date = new SimpleDateFormat("dd/MM/yyyy").parse(word);
			return this.getAllMessages().stream()
										.filter(s -> s.getTime().after(date))
										.collect(Collectors.toList());

		case "before": 
			date = new SimpleDateFormat("dd/MM/yyyy").parse(word);
			return this.getAllMessages().stream()
										.filter(s -> s.getTime().before(date))
										.collect(Collectors.toList());
			
		case "senderafter":
			year = Integer.parseInt(word);
			List<Message> resultAfter = new ArrayList<>();
			this.users.stream()
						.filter(s -> s.getYearOfBirth() >= year)
						.forEach(s -> resultAfter.addAll(getSentMessages(s.getUserName())));
			return resultAfter;
			
		case "senderbefore":
			year = Integer.parseInt(word);
			List<Message> resultBefore = new ArrayList<>();
			this.users.stream()
						.filter(s -> s.getYearOfBirth() < year)
						.forEach(s -> resultBefore.addAll(getSentMessages(s.getUserName())));
			return resultBefore;
			
		case "lessthan":
			
			return null;
			
		case "morethan":
			
			return null;
			
		default: return null;
		}
	}
	
	
	/**
	 * return the total amount of messages in the system
	 * @return
	 */
	public int messageAmount() {
		return this.getAllMessages().size();
	}
	
	/**
	 * return the average amount of messages per user in the system
	 * @return
	 */
	public float averageMessages() {
		return this.messageAmount()/this.users.size();
	}
	
	
	public Map<String, List<Message>> groupBySubject(){
	//	return Map<String, List<Message>> bySubject 
										//	= this.getAllMessages().stream()
										//	.collect(Collectors.groupingBy(Message::getSubject));	
		return null;
	}
	
	
	public int contWords(String name) {
		int result = 0;
		//this.getAllMessages().stream()
				//				.filter(s -> s.getSender().equals(name))
			//					.forEach(s -> result =+ s.getBody().length());
		return result;

	}
	
	/**
	 * filters all the messages in the system by the subject
	 * @param subject
	 * @return the filtered List
	 */
	/*
	public List<Message> filterBySubjectd(String subject){
		List<Message> result = new ArrayList<Message>();
		List<Message> messages = getAllMessages(); 	//we get all the messages from the MailStore
		for(Message elem: messages) {
			if(elem.getSubject().equals(subject)) result.add(elem);
		}
		return result;
	}
	*/
	
	
	/**
	 * filters all the messages in the system by the maximum year of birth
	 * @param year
	 * @return the new List with the messages
	 */
	/*
	public List<Message> usersBeforeYear(int year){
		List<Message> result = new ArrayList<Message>();
		int i = 0;
		for(User elem: users) {
			//if the yearOfBirth if before year we append to the result the message list from the user's MailBox
			if(elem.getYearOfBirth() < year) result.addAll(mailBoxes.get(i).listMail());
			i++;
		}
		return result;
	}
	*/
	
	



}
