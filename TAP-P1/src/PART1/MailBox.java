/**
 * 
 */
package PART1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailBox implements Iterable<Message>{
	User user;
	MailStore mailStore;
	List<Message> messages;
	
	
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
	 * 
	 */
	public void updateMail() {
		this.messages = mailStore.getMails(user);
		//sort by date
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
	public void sendMessage(String destination, String subject, String body) {
		this.mailStore.sendMail(new Message(this.user.getUserName(), destination, subject, body));
	}
	
	public List<Message> sortByXXX (){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			//ordenador como nos salga del nardo
		}
		return result;
	}
	
	public List<Message> filterUserMailByXXX(){
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.isIntended(user)) result.add(elem);
		}
		return result;
	}
	

}
