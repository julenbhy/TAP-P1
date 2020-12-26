package PART3;

import PART1.MailStore;
import PART1.MailStoreFile;

public class MailStoreFileFactory extends MailStoreFactory{

	@Override
	public MailStore createMailStore() {
		return new MailStoreFile(fileName);
	}



}
