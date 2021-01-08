package PART2.Decorator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
 
import PART1.MailStore;
import PART1.Message;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public abstract class WrapperForTheMailStore implements MailStore{
	
	protected MailStore client;
	
	/**
	 * Constructor for WrapperForTheMailStore
	 * @param client is the mailstore that we want to encrypt
	 */
	public WrapperForTheMailStore(MailStore client) {
		super();
		this.client = client;
	}
	
	/**
	 * Stores a mail in the MailStore
	 * @throws IOException if the file is not found
	 */
	public abstract void sendMail(Message mail) throws IOException;
	
	/**
	 * get all the mails for a certain user
	 * @param user the certain user
	 * @return a List with the mails
	 * @throws FileNotFoundException if the file is not found
	 */
	public abstract List<Message> getMails(String user) throws FileNotFoundException;
	
	/**
	 * get all the mails
	 * @return a List with the mails
	 * @throws FileNotFoundException if the file is not found
	 */
	public abstract List<Message> getAllMessages() throws FileNotFoundException;
}