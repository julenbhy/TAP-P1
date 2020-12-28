package PART3;

import PART1.MailStore;


public class MailStoreRedisFactory extends MailStoreFactory {

	
	@Override
	public MailStore createMailStore() {
		return new MailStoreRedis();
	}

}
