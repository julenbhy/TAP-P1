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
	

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public MailStoreFile(String fileName) throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
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
			super.messages.add(new Message(line[0], line[1], line[2], line[3]));
		}
	}
	
	
	/**
	 * 
	 */
	private void saveList() {
		
	}


}
