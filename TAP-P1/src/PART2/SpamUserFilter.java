package PART2;

import java.util.List;

import PART1.Message;

public class SpamUserFilter extends SpamObserver{
	
	private String userName;
	

	/**
	 * @param userName
	 */
	public SpamUserFilter(String userName) {
		this.userName = userName.toLowerCase();
	}



	@Override
	public void update(List<Message> messages, List<Message> spam) {
		for(Message msg:messages) {
			if(msg.getSender().equals(userName)) {
				spam.add(msg);
			}
		}
		for(Message msg:spam) {
			messages.remove(msg);
		}
	}

}
