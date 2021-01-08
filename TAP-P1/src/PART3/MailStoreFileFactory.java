package PART3;

import PART1.MailStore;
import PART1.MailStoreFile;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreFileFactory extends MailStoreFactory{
	
	private String fileName;
	
	
	/**
	 * @param fileName the name of the file to use
	 */
	public MailStoreFileFactory(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public MailStore createMailStore() {
		return new MailStoreFile(fileName);
	}



}
