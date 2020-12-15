/**
 * 
 */
package PART1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailSystem {
	
	private List<User> users;
	private List<MailBox> mailBoxes;
	private MailStore mailStore;

	/**
	 * constructor for MailSystem using normal MailStore
	 */
	public MailSystem() {
		users = new ArrayList<User>();
		mailBoxes = new ArrayList<MailBox>();
		mailStore = new MailStore();
	}
	
	
	/**
	 * constructor for MailSystem using MailStoreFile
	 * @param fileName
	 * @throws FileNotFoundException 
	 */
	public MailSystem(String fileName) throws FileNotFoundException {
		users = new ArrayList<User>();
		mailBoxes = new ArrayList<MailBox>();
		mailStore = new MailStoreFile(fileName);
	}
	
	
	/**
	 * 
	 * @param userName
	 * @param name
	 * @param yearOfBirth
	 * @return
	 */
	public MailBox createUser(String userName, String name, int yearOfBirth) {
		User user = new User(userName, name, yearOfBirth);
		if(!this.exists(userName)) {
			MailBox mailBox = new MailBox(user, mailStore);
			users.add(user);
			mailBoxes.add(mailBox);
			return mailBox;
		}
		return null;
	}
	
	
	/**
	 * Returns the MailBox of a certain userName
	 * @param userName
	 * @return the MailBox
	 */
	public MailBox getMailBox(String userName) {
		if(this.exists(userName)) {
			int i = 0;
			for(User elem: users) {
				if(elem.getUserName().equals(userName)) return this.mailBoxes.get(i);
				i++;
			}
		}
		return null;
	}
	
	/**
	 * Checks if the userName is already used
	 * @param userName
	 * @return true if it is used
	 */
	private boolean exists(String userName) {
		for(User elem: users) {
			if(elem.getUserName().equals(userName)) return true;
		}
		return false;
	}
	
	/**
	 * Gets all the messages in the system
	 * @return a List with all the messages
	 */
	public List<Message> getAllMessages(){
		return mailStore.getMessages();
	}


	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	
	
	//los de filtrar
	/**
	 * filters all the messages in the system by the subject
	 * @param subject
	 * @return the filtered List
	 */
	public List<Message> filterBySubjectd(String subject){
		List<Message> result = new ArrayList<Message>();
		List<Message> messages = getAllMessages(); 	//we get all the messages from the MailStore
		for(Message elem: messages) {
			if(elem.getSubject().equals(subject)) result.add(elem);
		}
		return result;
	}
	
	/**
	 * return the total amount of messages in the system
	 * @return
	 */
	public int messageAmount() {
		return this.getAllMessages().size();
	}
	
	/**
	 * return the average amount of messages per user in the system
	 * @return
	 */
	public float averageMessages() {
		return this.messageAmount()/this.users.size();
	}
	
	
	/**
	 * filters all the messages in the system by the maximum year of birth
	 * @param year
	 * @return the new List with the messages
	 */
	public List<Message> usersBeforeYear(int year){
		List<Message> result = new ArrayList<Message>();
		int i = 0;
		for(User elem: users) {
			//if the yearOfBirth if before year we append to the result the message list from the user's MailBox
			if(elem.getYearOfBirth() < year) result.addAll(mailBoxes.get(i).listMail());
			i++;
		}
		return result;
	}

}
