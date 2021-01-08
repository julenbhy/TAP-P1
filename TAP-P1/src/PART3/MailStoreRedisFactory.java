package PART3;

import PART1.MailStore;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreRedisFactory extends MailStoreFactory {

	
	@Override
	public MailStore createMailStore() {
		return new MailStoreRedis();
	}

}
