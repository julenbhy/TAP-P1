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
	 * 
	 * @param mail
	 */
	public void sendMail(Message mail)  throws IOException;

	/**
	 * 
	 * @param user
	 * @return List with the messages intended for the user
	 */
	public List<Message> getMails(String user) throws FileNotFoundException;
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Message> getAllMessages() throws FileNotFoundException;

}
