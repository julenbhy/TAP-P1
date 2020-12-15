/**
 * 
 */
package PART1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreFile extends MailStore{
	
	private String fileName;

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public MailStoreFile(String fileName) throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		fileToList(fileName);
	}
	
	
	
	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	private void fileToList(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));

		while(s.hasNext()) {
			String line[] = s.next().split(";"); //obtain the next line and split it
			User sender = new User(line[0], line[1], Integer.parseInt(line[2]));
			User receiver = new User(line[3], line[4], Integer.parseInt(line[5]));
			super.messages.add(new Message(sender, receiver, line[6], line[7]));
		}
	}
	
	
	/**
	 * 
	 */
	private void saveList() {
		
	}


}
