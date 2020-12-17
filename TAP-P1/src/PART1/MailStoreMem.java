/**
 * 
 */
package PART1;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreMem implements MailStore{
	
	protected List<Message> messages;
	
	/**
	 * Constructor
	 */
	public MailStoreMem() {
		this.messages = new ArrayList<Message>();
	}
	

	@Override
	public void sendMail(Message mail) {
		this.messages.add(mail);
	}

	@Override
	public List<Message> getMails(String user) {
		List<Message> result = new ArrayList<>();
		for(Message elem: messages) {
			if(elem.getReceiver().toLowerCase().equals(user.toLowerCase())) 
						result.add(elem);
		}
		return result;
	}

	
	@Override
	public List<Message> getAllMessages() {
		return messages;
	}
	
	
	
	
	
}
