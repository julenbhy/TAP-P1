package PART3;

import PART1.MailStore;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public abstract class MailStoreFactory {
	/**
	 * creates a MailStore
	 * @return the MailStore
	 */
	public abstract MailStore createMailStore();

}
