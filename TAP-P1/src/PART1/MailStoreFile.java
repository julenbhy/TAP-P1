/**
 * 
 */
package PART1;

import java.io.*;
import java.text.ParseException;
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
	 * Constructor for the MailStoreFile class
	 * @param fileName the name of the file to use
	 */
	public MailStoreFile(String fileName){
		this.fileName = fileName;
	}
	
	
	@Override
	public void sendMail(Message mail) throws IOException{
		bw = new BufferedWriter(new FileWriter(fileName, true));
	    bw.write(mail.getSender()+";"+mail.getReceiver()+";"+mail.getSubject()+";"+mail.getBody()+";"+mail.getDate());
	    bw.newLine();
	    bw.close();
	}
	
	
	
	@Override
	public List<Message> getMails(String user) throws FileNotFoundException{
		sc = new Scanner(new File(fileName));
		List<Message> result = new ArrayList<>();
		while(sc.hasNextLine()) {
			String[] line = sc.nextLine().split(";"); //obtain the next line and split it
			if(line[1].toLowerCase().equals(user.toLowerCase()))
				try {
					result.add(new Message(line[0], line[1], line[2], line[3], line[4])); //sender;receiver;subject;body;date
				} catch (ParseException e) {
					System.out.println("Error, can't convert date from file to message");
				}	
		}
		sc.close();
		return result;
	}
	
	
	@Override
	public List<Message> getAllMessages() throws FileNotFoundException{
		sc = new Scanner(new File(fileName));
		List<Message> result = new ArrayList<>();
		while(sc.hasNextLine()) {
			String[] line = sc.nextLine().split(";"); //obtain the next line and split it
			try {
				result.add(new Message(line[0], line[1], line[2], line[3], line[4])); //sender;receiver;subject;body;date
			} catch (ParseException e) {
				System.out.println("Error, can't convert date from file to message");
			}
		}
		sc.close();
		return result;
	}

}
