package PART2.Observer;

import java.util.List;

import PART1.Message;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class SpamUserFilter extends SpamObserver{
	
	private String userName;
	

	/**
	 * Set the user  that we want as spam as a local variable
	 * @param userName that we want as spam
	 */
	public SpamUserFilter(String userName) {
		this.userName = userName.toLowerCase();
	}

	@Override
	public void update(List<Message> messages, List<Message> spam) {
		for(Message msg:messages) {
			if(msg.getSender().contains(userName) && !spam.contains(msg)) {
				spam.add(msg);
			}
		}
		for(Message msg:spam) {
			messages.remove(msg);
		}
	}

}
