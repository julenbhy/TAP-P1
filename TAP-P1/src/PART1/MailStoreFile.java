/**
 * 
 */
package PART1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreFile implements MailStore{
	
	private String fileName;

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public MailStoreFile(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
	}
	
	
	@Override
	public void sendMail(Message mail) throws IOException {
		FileWriter fw = new FileWriter(fileName, true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(mail.getSender()+";"+mail.getReceiver()+";"+mail.getSubject()+";"+mail.getBody());
	    bw.newLine();
	    bw.close();
	}
	
	
	@Override
	public List<Message> getMails(String user) throws FileNotFoundException {
		List<Message> result = new ArrayList<>();
		Scanner s = new Scanner(new File(fileName));
		while(s.hasNext()) {
			String line[] = s.next().split(";"); //obtain the next line and split it
			if(line[1].toLowerCase().equals(user.toLowerCase()))
						result.add(new Message(line[0], line[1], line[2], line[3]));	//sender;receiver;subject;body
		}
		return result;
	}
	
	
	@Override
	public List<Message> getAllMessages() throws FileNotFoundException {
		List<Message> result = new ArrayList<>();
		Scanner s = new Scanner(new File(fileName));
		while(s.hasNext()) {
			String line[] = s.next().split(";"); //obtain the next line and split it
			result.add(new Message(line[0], line[1], line[2], line[3]));	//sender;receiver;subject;body
		}
		return result;
	}

}
