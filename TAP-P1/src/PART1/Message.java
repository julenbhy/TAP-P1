/**
 * 
 */
package PART1;

import java.sql.Date;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class Message {
	String sender, receiver, subject, body;
	Date time;
	
	
	/**
	 * 
	 * @param sender userName
	 * @param receiver userName
	 * @param subject
	 * @param body
	 */
	public Message( String sender, String receiver, String subject, String body) {
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
		this.time = new Date(System.currentTimeMillis());
	}
	
	
	/**
	 * 
	 * @param user
	 * @return True if is intended
	 */
	public boolean isIntended(User user) {
		return (this.receiver.equals(user.getUserName()));
	}
	
	
	

	
}
