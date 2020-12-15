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
	private User sender, receiver;
	private String subject, body;
	private Date time;
	
	
	/**
	 * 
	 * @param sender userName
	 * @param receiver userName
	 * @param subject
	 * @param body
	 */
	public Message( User sender, User receiver, String subject, String body) {
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
	
	/**
	 * Checks if a word (no matter if upper or lower case) is contained at the subject
	 * @param word
	 * @return true if contains
	 */
	public boolean containsWord(String word) {
		return this.subject.toLowerCase().contains(word.toLowerCase());
	}



	/**
	 * checks if the sender war born after a certain year (not included)
	 * @param year
	 * @return true if was born later
	 */
	public boolean checkSendersYear(int year) {
		return (this.sender.getYearOfBirth() > year); 
	}
	
	
	
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}


	/**
	 * @return the sender
	 */
	public User getSender() {
		return sender;
	}
	
	
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	public void printMessage() {
		System.out.println("Message [sender=" + sender + ", receiver=" + receiver + ", subject=" + subject + ", body=" + body
				+ ", time=" + time + "]");
	}


	

	
	
	
	
	
	

	
}
