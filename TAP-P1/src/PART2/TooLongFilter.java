package PART2;

import java.util.List;

import PART1.Message;

public class TooLongFilter extends SpamObserver{

	private static final int MAXLENGTH = 20;
	
	@Override
	public void update(List<Message> messages, List<Message> spam) {
		for(Message msg:messages) {
			if(msg.getBody().split("\\s+").length > MAXLENGTH) {
				spam.add(msg);
			}
		}
		for(Message msg:spam) {
			messages.remove(msg);
		}
	}

}
