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
	
	private Scanner sc;
	private BufferedWriter bw;
	private String fileName;

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public MailStoreFile(String fileName){
		this.fileName = fileName;
	}
	
	
	@Override
	public void sendMail(Message mail) throws IOException{
		bw = new BufferedWriter(new FileWriter(fileName, true));
	    bw.write(mail.getSender()+";"+mail.getReceiver()+";"+mail.getSubject()+";"+mail.getBody());
	    bw.newLine();
	    bw.close();
	}
	
	
	
	@Override
	public List<Message> getMails(String user) throws FileNotFoundException{
		sc = new Scanner(new File(fileName));
		List<Message> result = new ArrayList<>();
		while(sc.hasNext()) {
			String line[] = sc.next().split(";"); //obtain the next line and split it
			if(line[1].toLowerCase().equals(user.toLowerCase()))
						result.add(new Message(line[0], line[1], line[2], line[3]));	//sender;receiver;subject;body
		}
		sc.close();
		return result;
	}
	
	
	@Override
	public List<Message> getAllMessages() throws FileNotFoundException{
		sc = new Scanner(new File(fileName));
		List<Message> result = new ArrayList<>();
		while(sc.hasNext()) {
			String line[] = sc.next().split(";"); //obtain the next line and split it
			result.add(new Message(line[0], line[1], line[2], line[3]));	//sender;receiver;subject;body
		}
		sc.close();
		return result;
	}

}
