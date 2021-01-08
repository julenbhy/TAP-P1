/**
 * 
 */
package PART1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public interface MailStore {
	
	/**
	 * Stores a mail in the MailStore
	 * @param mail the mail to store
	 * @throws IOException if the file is not found
	 */
	public void sendMail(Message mail)  throws IOException;


	/**
	 * get all the mails for a certain user
	 * @param user the certain user
	 * @return a List with the mails
	 * @throws FileNotFoundException if the file is not found
	 */
	public List<Message> getMails(String user) throws FileNotFoundException;
	
	/**
	 * gets all the mail from the system
	 * @return a List with the mails 
	 * @throws FileNotFoundException if the file is not found
	 */
	public List<Message> getAllMessages() throws FileNotFoundException;

}
