package PART2.Observer;

import java.util.List;

import PART1.Message;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class TooLongFilter extends SpamObserver{

	private static final int MAXLENGTH = 20;
	
	@Override
	public void update(List<Message> messages, List<Message> spam) {
		for(Message msg:messages) {
			if((msg.getBody().split("\\s+").length > MAXLENGTH) && !spam.contains(msg)) {
				spam.add(msg);
			}
		}
		for(Message msg:spam) {
			messages.remove(msg);
		}
	}

}
