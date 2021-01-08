/**
 * 
 */
package PART1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailBox implements Iterable<Message>{
	protected User user;
	protected MailStore mailStore;
	protected List<Message> messages;
	
	
	/**
	 * @param user the owner
	 * @param mailStore the mail store to access
	 */
	public MailBox(User user, MailStore mailStore) {
		this.user = user;
		this.mailStore = mailStore;
		this.messages = new ArrayList<Message>();
	}


	@Override
	public Iterator<Message> iterator() {
		// TODO Auto-generated method stub
		return messages.iterator();
	}
	
	
	/**
	 * Update the MailBox sorted by date
	 */
	public void updateMail() {
		try {
			this.messages = mailStore.getMails(user.getUserName()).stream()
																  .sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
																  .collect(Collectors.toList());
			Collections.reverse(this.messages);
		}
		catch(Exception e) {
			System.out.println("Can't update, the file is not found");
		}
	}


	/**
	 * @return the messages
	 */
	public List<Message> listMail() {
		return messages;
	}
	
	
	/**
	 * 
	 * @param destination userName
	 * @param subject the subject of the message
	 * @param body the body of the message
	 */
	public void sendMail(String destination, String subject, String body) {
		try {
			this.mailStore.sendMail(new Message(this.user.getUserName(), destination, subject, body));
		}
		catch(IOException e) {
			System.out.println("Can't send mail, the file is not found");
		}
		catch(JedisConnectionException e) {
			System.out.println("Can't connect to server");
		}
		
	}
	
	/**
	 * Sorts the MailBox using the given predicate
	 * @param comparator the comparator to sort
	 * @return the sorted list
	 */
	public List<Message> sortBy(Comparator<Message> comparator){
		return this.messages.stream().sorted(comparator).collect(Collectors.toList());
	}

	
	
	/**
	 * Filters the MailBox using the given predicate
	 * @param predicate the condition predicate
	 * @return the filtered list
	 */
	public List<Message> filterBy(Predicate<Message> predicate){
		
		return this.messages.stream().filter(predicate).collect(Collectors.toList());

	}
	

	
	/**
	 * return the amount of messages in the MailBox
	 * ( = amount of messages for a user)
	 * @return the ammount 
	 */
	public int messageAmount() {
		return this.messages.size();
	}

}
