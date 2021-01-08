package PART3;

import PART1.MailStore;
import PART1.MailStoreMem;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreMemFactory extends MailStoreFactory{

	@Override
	public MailStore createMailStore() {
		return new MailStoreMem();
	}

}
