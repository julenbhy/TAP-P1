package PART3;

import PART1.MailStore;
import PART1.MailStoreMem;

public class MailStoreMemFactory extends MailStoreFactory{

	@Override
	public MailStore createMailStore() {
		return new MailStoreMem();
	}

}
