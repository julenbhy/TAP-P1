package PART2.Observer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import PART1.MailBox;
import PART1.MailStore;
import PART1.Message;
import PART1.User;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class ObservableMailBox extends MailBox{
	
	private List<SpamObserver> observerList = new ArrayList<>();
	private List<Message> spam = new ArrayList<>();
	
	/**
	 * Constructor for ObservableMailBox 
	 * @param user class with the whole info
	 * @param mailStore to store the data
	 */
	public ObservableMailBox(User user, MailStore mailStore) {
		super(user, mailStore);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Add one observer to the observerList
	 * @param subscriptor of one type of spam
	 */
	public void addObserver(SpamObserver subscriptor){ observerList.add(subscriptor);}
	
	@Override
	public void updateMail() {
		try {
			this.messages = mailStore.getMails(user.getUserName()).stream()
					  .sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
					  .collect(Collectors.toList());
			Collections.reverse(this.messages);
			
			for (SpamObserver subscriptor:observerList){
				subscriptor.update(messages, spam);
				//per lligadura dinàmica cada subscriptor reaccionarà com tingui programat
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't update, the file is not found");
		}
	}

	/**
	 * Get a list with all the messages added to spam
	 * @return spam messages in one list
	 */
	public List<Message> getSpam() {
		return spam;
	}
	
	
}
