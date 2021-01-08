package PART2.Observer;

import java.util.List;

import PART1.Message;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public abstract class SpamObserver {
	
	/**
	 * Update one mailbox and separate the spam mail from the other mails 
	 * @param messages list of the whole messages that will only end with normal mails
	 * @param spam list of spam mails
	 */
	public abstract void update(List<Message> messages, List<Message> spam);
}
