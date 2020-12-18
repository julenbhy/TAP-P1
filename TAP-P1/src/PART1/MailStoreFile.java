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
	private  BufferedWriter bw;

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public MailStoreFile(String fileName) throws IOException{
		sc = new Scanner(new File(fileName));
		bw = new BufferedWriter(new FileWriter(fileName, true));
	}
	
	
	@Override
	public void sendMail(Message mail) throws IOException{
	    bw.write(mail.getSender()+";"+mail.getReceiver()+";"+mail.getSubject()+";"+mail.getBody());
	    bw.newLine();
	}
	
	
	@Override
	public List<Message> getMails(String user){
		List<Message> result = new ArrayList<>();
		while(sc.hasNext()) {
			String line[] = sc.next().split(";"); //obtain the next line and split it
			if(line[1].toLowerCase().equals(user.toLowerCase()))
						result.add(new Message(line[0], line[1], line[2], line[3]));	//sender;receiver;subject;body
		}
		return result;
	}
	
	
	@Override
	public List<Message> getAllMessages(){
		List<Message> result = new ArrayList<>();
		while(sc.hasNext()) {
			String line[] = sc.next().split(";"); //obtain the next line and split it
			result.add(new Message(line[0], line[1], line[2], line[3]));	//sender;receiver;subject;body
		}
		return result;
	}

}
