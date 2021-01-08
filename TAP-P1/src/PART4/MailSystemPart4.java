/**
 * 
 */
package PART4;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import PART1.*;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */

@Config(
        store = "PART1.MailStoreMem",
      //store = "PART1.MailStoreFile",
      //store = "PART3.MailStoreRedis",
        log = false
)
public class MailSystemPart4 {
	
	private List<User> users;
	private List<MailBox> mailBoxes;
	private MailStore mailStore;

	/**
	 * constructor for MailSystem using normal MailStore
	 * @param mailStore
	 */
	public MailSystemPart4() {
		this.users = new ArrayList<User>();
		this.mailBoxes = new ArrayList<MailBox>();

		try {
			this.createMailStore();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
			System.out.println("Error, can't create new instance");
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, can't get the constructor");
		}



	}
	
	public void createMailStore() throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<MailSystemPart4> obj = MailSystemPart4.class;
		Annotation annotation = obj.getAnnotation(Config.class);
        Config copy = (Config) annotation;
		Constructor<?> constructor = Class.forName(copy.store()).getConstructors()[0];
		Object myObj = null;
		if(copy.store().equals("PART1.MailStoreMem")) myObj = constructor.newInstance();
		else if(copy.store().equals("PART1.MailStoreFile")) myObj = constructor.newInstance("part4.txt");
		else if(copy.store().equals("PART3.MailStoreRedis")) myObj = constructor.newInstance();
		this.mailStore = (MailStore) myObj;
	}
	
	/**
	 * 
	 * @param userName
	 * @param name
	 * @param yearOfBirth
	 * @return
	 */
	public MailBox createUser(String userName, String name, int yearOfBirth) {
		User user = new User(userName.toLowerCase(), name.toLowerCase(), yearOfBirth);
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
			if(elem.getUserName().toLowerCase().equals(userName.toLowerCase())) return true;
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
		catch(FileNotFoundException e) {
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
														.sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
														.collect(Collectors.toList());
			Collections.reverse(result);	
			return result;
			
			
		case "older": 
			return this.getAllMessages().stream()
										.sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
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
		int number;
		
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
										.filter(s -> s.getDate().after(date))
										.collect(Collectors.toList());

		case "before": 
			date = new SimpleDateFormat("dd/MM/yyyy").parse(word);
			return this.getAllMessages().stream()
										.filter(s -> s.getDate().before(date))
										.collect(Collectors.toList());
			
		case "senderafter":
			number = Integer.parseInt(word);
			List<Message> resultAfter = new ArrayList<>();
			this.users.stream()
						.filter(s -> s.getYearOfBirth() >= number)
						.forEach(s -> resultAfter.addAll(getSentMessages(s.getUserName())));
			return resultAfter;
			
		case "senderbefore":
			number = Integer.parseInt(word);
			List<Message> resultBefore = new ArrayList<>();
			this.users.stream()
						.filter(s -> s.getYearOfBirth() < number)
						.forEach(s -> resultBefore.addAll(getSentMessages(s.getUserName())));
			return resultBefore;
			
		case "bodylessthan":
			number = Integer.parseInt(word);
			return this.getAllMessages().stream()
						.filter(s -> s.getBody().split("\\s+").length < number)
						.collect(Collectors.toList());
			
		case "bodymorethan":
			number = Integer.parseInt(word);
			return this.getAllMessages().stream()
						.filter(s -> s.getBody().split("\\s+").length > number)
						.collect(Collectors.toList());
			
		case "subjectlessthan":
			number = Integer.parseInt(word);
			return this.getAllMessages().stream()
						.filter(s -> s.getSubject().split("\\s+").length < number)
						.collect(Collectors.toList());
			
		case "subjectmorethan":
			number = Integer.parseInt(word);
			return this.getAllMessages().stream()
						.filter(s -> s.getSubject().split("\\s+").length > number)
						.collect(Collectors.toList());	
			
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
		return (float) this.messageAmount()/(float) this.users.size();
	}
	
	
	public Map<String, List<Message>> groupBySubject(){
	return this.getAllMessages().stream()
								.collect(Collectors.groupingBy(Message::getSubject));	
	}
	
	
	public int countWords(String name) {
		return this.getAllMessages().stream()
							.filter(s -> this.getUser(s.getSender()).getName().toLowerCase().equals(name.toLowerCase()))
							.mapToInt(s -> s.getBody().split("\\s+").length).sum();
							//.forEach(s -> result =+ s.getBody().split("\\s+").length);

	}


}
