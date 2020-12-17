/**
 * 
 */
package PART1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailBox implements Iterable<Message>{
	private User user;
	private MailStore mailStore;
	private List<Message> messages;
	
	
	/**
	 * @param user
	 * @param mailStore
	 * @param messages
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
	 * @throws FileNotFoundException 
	 */
	public void updateMail() {
		try {
			this.messages = mailStore.getMails(user.getUserName()).stream()
																  .sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
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
	 * @param subject
	 * @param body
	 */
	public void sendMail(String destination, String subject, String body) {
		try {
			this.mailStore.sendMail(new Message(this.user.getUserName(), destination, subject, body));
		}
		catch(Exception e) {
			System.out.println("Can't update, the file is not found");
		}
		
	}
	
	/**
	 * Sort the MailBox by date
	 */
	
	public List<Message> sortBy(String condition){
		switch(condition){
		
		case "newer": 
			this.messages = this.messages.stream()
											.sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
											.collect(Collectors.toList());
			Collections.reverse(this.messages);	
			return this.messages;
			
			
		case "older": 
			return this.messages.stream()
								.sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
								.collect(Collectors.toList());
			
			
		case "a-z":
			return this.messages.stream()
								.sorted((o1,o2) -> o1.getSender().compareTo(o2.getSender()))
								.collect(Collectors.toList());
			
			
		case "z-a":
			this.messages =  this.messages.stream()
											.sorted((o1,o2) -> o1.getSender().compareTo(o2.getSender()))
											.collect(Collectors.toList());
			Collections.reverse(this.messages);	
			return this.messages;
			
		//añadir mas opciones de ordenacion
			
		
		default: return null;
		}
	}

	
	
	/**
	 * filter if the subject contains a given word
	 * @param condition The condition to filter (sender, subject, body or date)
	 * @param word The string it must search
	 * @return the filtered list
	 */
	public List<Message> filterBy(String condition, String word){
		switch(condition){
		
		case "sender": 
			return this.messages.stream().filter(o -> o.getSender().equals(word)).collect(Collectors.toList());

		case "subject":
			return this.messages.stream().filter(s -> s.getSubject().contains(word)).collect(Collectors.toList());

		case "body": 
			return this.messages.stream().filter(s -> s.getBody().contains(word)).collect(Collectors.toList());

		case "date": 
			//return this.messages.stream().filter(s -> s.getTime().);
			
		//añadir mas opciones de filtrado
		
		default: return null;
		}
	}
	
	/**
	 * filter by sender
	 * @param word
	 * @return the filtered list
	 */
	/*
	public List<Message> filterBySender(String word){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.getSender().equals(word)) result.add(elem);
		}
		return result;
	}
	*/
	

	/**
	 * filter if the subject is a single word
	 * the subject can't contain an space
	 * @return the filtered list
	 */
	/*
	public List<Message> filterBySingleWord(){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(!elem.containsWord(" ")) result.add(elem);
		}
		return result;
	}
	*/
	
	
	/**
	 * filter if the sender was born after a certain year (not included)
	 * @return the filtered list
	 */
	/**
	public List<Message> filterBySendersYear(int year){
		// we have decided to receive year instead of '2000' in order to be reusable
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.checkSendersYear(year)) result.add(elem);
		}
		return result;
	}
	*/
	
	
	/**
	 * return the amount of messages in the MailBox
	 * ( = amount of messages for a user)
	 * @return
	 */
	public int messageAmount() {
		return this.messages.size();
	}

}
