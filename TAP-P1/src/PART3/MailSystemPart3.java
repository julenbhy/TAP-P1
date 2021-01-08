/**
 * 
 */
package PART3;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.crypto.NoSuchPaddingException;
import PART1.*;
import PART2.Decorator.*;
import PART2.Observer.ObservableMailBox;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailSystemPart3{
	
	private List<User> users;
	private List<MailBox> mailBoxes;
	private MailStore mailStore;


	/**
	 * constructor for MailSystem using normal MailStore
	 * @param mailStoreFactory the factory to create mailStores
	 */
	public MailSystemPart3(MailStoreFactory mailStoreFactory) {
		this.users = new ArrayList<User>();
		this.mailBoxes = new ArrayList<MailBox>();
		this.mailStore = mailStoreFactory.createMailStore();
	}
	
	
	/**
	 * reverse-encryps the mailStore
	 */
	public void ReverseEncrypted() {
		mailStore = new ReverseEncrypted(mailStore);
	}
	
	/**
	 * cypher-encrypts the mail store 
	 */
	public void CipherEncrypted() {
		try {
			mailStore = new CipherEncrypted(mailStore);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.out.println("Error: can't encrypt the mail store");
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a new user
	 * @param userName the username, will be passed to lower case
	 * @param name the name of the user
	 * @param yearOfBirth the year of birth
	 * @return the MailBox of the user
	 */
	public MailBox createUser(String userName, String name, int yearOfBirth) {
		User user = new User(userName.toLowerCase(), name.toLowerCase(), yearOfBirth);
		if(!this.userExists(userName)) {
			MailBox mailBox = new ObservableMailBox(user, mailStore);
			users.add(user);
			mailBoxes.add(mailBox);
			return mailBox;
		}
		return null;
	}
	
	
	/**
	 * Returns the MailBox of a certain userName
	 * @param userName the userName, will be passed to lower case
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
	
	/**
	 * Gets all the messages sent by the user
	 * @param userName the user name, will be passed to lower case
	 * @return the list of messages
	 */
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
	 * @param userName the user name, will be passed to lower case
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
	 * @param userName the user name, will be passed to lower case
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
	 * Sorts the MailSystem using the given predicate
	 * @param comparator the comparator to sort
	 * @return the sorted list
	 */
	public List<Message> sortBy(Comparator<Message> comparator){
		return this.getAllMessages().stream().sorted(comparator).collect(Collectors.toList());
	}
	
	
	
	
	/**
	 * Filters the MailSystem using the given predicate
	 * @param predicate the condition predicate
	 * @return the filtered list
	 */
	public List<Message> filterBy(Predicate<Message> predicate){
		return this.getAllMessages().stream().filter(predicate).collect(Collectors.toList());
	}
	
	
	/**
	 * gets the total amount of messages in the system
	 * @return the total amount of messages in the system
	 */
	public int messageAmount() {
		return this.getAllMessages().size();
	}
	
	/**
	 * gets the average amount of messages per user in the system
	 * @return the average amount of messages per user in the system
	 */
	public float averageMessages() {
		return (float) this.messageAmount()/(float) this.users.size();
	}
	
	
	/**
	 * groups the messages by subject
	 * @return a map with the grouped messages
	 */
	public Map<String, List<Message>> groupBySubject(){
	return this.getAllMessages().stream()
								.collect(Collectors.groupingBy(Message::getSubject));	
	}
	
	/**
	 * counts the words of all the messages sent by a certain user
	 * @param name the name, will be passed to lower case (not username)
	 * @return the number of messages
	 */
	public int countWords(String name) {
		return this.getAllMessages().stream()
							.filter(s -> this.getUser(s.getSender()).getName().toLowerCase().equals(name.toLowerCase()))
							.mapToInt(s -> s.getBody().split("\\s+").length).sum();
							//.forEach(s -> result =+ s.getBody().split("\\s+").length);
	
	}

}
