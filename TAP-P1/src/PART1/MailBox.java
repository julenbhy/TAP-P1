/**
 * 
 */
package PART1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	 */
	public void updateMail() {
		this.messages = mailStore.getMails(user);
		this.sortByDate();
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
	public void sendMail(User destination, String subject, String body) {
		this.mailStore.sendMail(new Message(this.user, destination, subject, body));
	}
	
	/**
	 * Sort the MailBox by date
	 */
	public void sortByDate (){
		this.messages.sort((o1,o2) -> o1.getTime().compareTo(o2.getTime()));
		Collections.reverse(this.messages);
	}
	
	
	/**
	 * filter if the subject contains a given word
	 * @param word
	 * @return the filtered list
	 */
	public List<Message> filterByContainsWord(String word){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.containsWord(word)) result.add(elem);
		}
		return result;
	}
	
	/**
	 * filter by sender
	 * @param word
	 * @return the filtered list
	 */
	public List<Message> filterBySender(String word){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.getSender().equals(word)) result.add(elem);
		}
		return result;
	}
	

	/**
	 * filter if the subject is a single word
	 * the subject can't contain an space
	 * @return the filtered list
	 */
	public List<Message> filterBySingleWord(){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(!elem.containsWord(" ")) result.add(elem);
		}
		return result;
	}
	
	
	/**
	 * filter if the sender was born after a certain year (not included)
	 * @return the filtered list
	 */
	public List<Message> filterBySendersYear(int year){
		// we have decided to receive year instead of '2000' in order to be reusable
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.checkSendersYear(year)) result.add(elem);
		}
		return result;
	}
	
	
	/**
	 * return the amount of messages in the MailBox
	 * ( = amount of messages for a user)
	 * @return
	 */
	public int messageAmount() {
		return this.messages.size();
	}

}
