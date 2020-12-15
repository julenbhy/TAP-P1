/**
 * 
 */
package PART1;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStore implements Iterable<Message>{
	
	protected List<Message> messages;
	
	/**
	 * Constructor
	 */
	public MailStore() {
		this.messages = new ArrayList<Message>();
	}
	
	@Override
	public Iterator<Message> iterator() {
		// TODO Auto-generated method stub
		return messages.iterator();
	}
	
	

	/**
	 * 
	 * @param mail
	 */
	public void sendMail(Message mail) {
		this.messages.add(mail);
	}

	/**
	 * 
	 * @param user
	 * @return List with the messages intended for the user
	 */
	public List<Message> getMails(User user) {
		List<Message> result = new ArrayList<Message>();
		for(Message elem: messages) {
			if(elem.isIntended(user)) result.add(elem);
		}
		return result;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}
	
	
	
	
	
}
