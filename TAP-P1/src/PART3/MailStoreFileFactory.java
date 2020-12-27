package PART3;

import PART1.MailStore;
import PART1.MailStoreFile;

public class MailStoreFileFactory extends MailStoreFactory{
	
	private String fileName;
	
	
	/**
	 * @param fileName
	 */
	public MailStoreFileFactory(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public MailStore createMailStore() {
		return new MailStoreFile(fileName);
	}



}
