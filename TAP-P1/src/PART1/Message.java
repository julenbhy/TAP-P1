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
	 * constructor for creating new messages
	 * @param sender userName
	 * @param receiver userName
	 * @param subject the subject of the message
	 * @param body the body
	 */
	public Message( String sender, String receiver, String subject, String body) {
		this.sender = sender.toLowerCase();
		this.receiver = receiver.toLowerCase();
		this.subject = subject;
		this.body = body;
		this.date = new Date(System.currentTimeMillis());
	}
	
	/**
	 * constructor for creating messages ridden from a file
	 * @param sender userName
	 * @param receiver userName
	 * @param subject the subject of the message
	 * @param body the body
	 * @param dateString the original date of the message
	 * @throws ParseException if can't parse the date
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
	 * to know if the message is intended for a user
	 * @param userName the user name, will be passed to lower case
	 * @return True if is intended
	 */
	public boolean isIntended(String userName) {
		return this.receiver.equals(userName.toLowerCase());
	}
	
	/**
	 * Checks if a word (no matter if upper or lower case) is contained at the subject
	 * @param word the word, will be passed to lower case
	 * @return true if contains
	 */
	public boolean containsWord(String word) {
		return this.subject.toLowerCase().contains(word.toLowerCase());
	}


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

	/** 
	 * Prints the message
	 */
	public void printMessage() {
		System.out.println("Message [sender=" + sender + ", receiver=" + receiver + ", subject=" + subject + ", body=" + body
				+ ", time=" + date + "]");
	}

	
}
