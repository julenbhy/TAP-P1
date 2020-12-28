/**
 * 
 */
package PART1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class Message {
	private String sender, receiver;
	private String subject, body;
	private Date date;
	
	
	/**
	 * 
	 * @param sender userName
	 * @param receiver userName
	 * @param subject
	 * @param body
	 */
	public Message( String sender, String receiver, String subject, String body) {
		this.sender = sender.toLowerCase();
		this.receiver = receiver.toLowerCase();
		this.subject = subject;
		this.body = body;
		this.date = new Date(System.currentTimeMillis());
	}
	
	/**
	 * 
	 * @param sender userName
	 * @param receiver userName
	 * @param subject
	 * @param body
	 * @param date
	 * @throws ParseException 
	 */
	public Message( String sender, String receiver, String subject, String body, String dateString) throws ParseException {
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
		DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
		this.date = format.parse(dateString);
	}
	
	
	/**
	 * 
	 * @param user
	 * @return True if is intended
	 */
	public boolean isIntended(String user) {
		return this.receiver.equals(user);
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
	/*
	public boolean checkSendersYear(int year) {
		return (this.sender.getYearOfBirth() > year); 
	}
	*/
	
	


	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	
	
	
	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}


	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * @return the time
	 */
	public Date getDate() {
		return date;
	}


	public void printMessage() {
		System.out.println("Message [sender=" + sender + ", receiver=" + receiver + ", subject=" + subject + ", body=" + body
				+ ", time=" + date + "]");
	}

	
}
