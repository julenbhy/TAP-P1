/**
 * 
 */
package PART1;

import java.io.FileNotFoundException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailBox implements Iterable<Message>{
	private User user;
	private MailStore mailStore;
	private List<Message> messages;
	
	
	/**
	 * @param user
	 * @param mailStore
	 * @param messages
	 */
	public MailBox(User user, MailStore mailStore) {
		this.user = user;
		this.mailStore = mailStore;
		this.messages = new ArrayList<Message>();
	}


	@Override
	public Iterator<Message> iterator() {
		// TODO Auto-generated method stub
		return messages.iterator();
	}
	
	
	/**
	 * Update the MailBox sorted by date
	 * @throws FileNotFoundException 
	 */
	public void updateMail() {
		try {
			this.messages = mailStore.getMails(user.getUserName()).stream()
																  .sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
																  .collect(Collectors.toList());
			Collections.reverse(this.messages);
		}
		catch(Exception e) {
			System.out.println("Can't update, the file is not found");
		}
	}


	/**
	 * @return the messages
	 */
	public List<Message> listMail() {
		return messages;
	}
	
	
	/**
	 * 
	 * @param destination userName
	 * @param subject
	 * @param body
	 */
	public void sendMail(String destination, String subject, String body) {
		try {
			this.mailStore.sendMail(new Message(this.user.getUserName(), destination, subject, body));
		}
		catch(Exception e) {
			System.out.println("Can't update, the file is not found");
		}
		
	}
	
	/**
	 * 
	 * @param condition The condition to sort (newer, older, a-z or z-a)
	 * @return
	 */
	
	public List<Message> sortBy(String condition){
		switch(condition){
		
		case "newer": 
			List<Message> result  = this.messages.stream()
													.sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
													.collect(Collectors.toList());
			Collections.reverse(result);	
			return result;
			
			
		case "older": 
			return this.messages.stream()
								.sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
								.collect(Collectors.toList());
			
			
		case "a-z":
			return this.messages.stream()
								.sorted((o1,o2) -> o1.getSender().compareTo(o2.getSender()))
								.collect(Collectors.toList());
			
			
		case "z-a":
			List<Message> resultAZ =  this.messages.stream()
											.sorted((o1,o2) -> o1.getSender().compareTo(o2.getSender()))
											.collect(Collectors.toList());
			Collections.reverse(resultAZ);	
			return resultAZ;
			
		//añadir mas opciones de ordenacion
			
		
		default: return null;
		}
	}

	
	
	/**
	 * filter the mailbox by a given parameter
	 * @param condition The condition to filter (sender, subject, body or date)
	 * date must be format: dd/mm/yyyy
	 * @param word The string it must search
	 * @return the filtered list
	 * @throws ParseException 
	 */
	public List<Message> filterBy(String condition, String word) throws ParseException{
		
		Date date;
		int number;
		
		switch(condition){
		
		case "sender": 
			return this.messages.stream().filter(o -> o.getSender().equals(word)).collect(Collectors.toList());

		case "subject":
			return this.messages.stream().filter(s -> s.getSubject().contains(word)).collect(Collectors.toList());

		case "body": 
			return this.messages.stream().filter(s -> s.getBody().contains(word)).collect(Collectors.toList());

		case "after": 
			date = new SimpleDateFormat("dd/MM/yyyy").parse(word);
			return this.messages.stream()
								.filter(s -> s.getDate().after(date))
								.collect(Collectors.toList());

		case "before": 
			date = new SimpleDateFormat("dd/MM/yyyy").parse(word);
			return this.messages.stream()
								.filter(s -> s.getDate().before(date))
								.collect(Collectors.toList());
			
		case "bodylessthan":
			number = Integer.parseInt(word);
			return this.messages.stream()
						.filter(s -> s.getBody().split("\\s+").length < number)
						.collect(Collectors.toList());
			
		case "bodymorethan":
			number = Integer.parseInt(word);
			return this.messages.stream()
						.filter(s -> s.getBody().split("\\s+").length > number)
						.collect(Collectors.toList());
			
		case "subjectlessthan":
			number = Integer.parseInt(word);
			return this.messages.stream()
						.filter(s -> s.getSubject().split("\\s+").length < number)
						.collect(Collectors.toList());
			
		case "subjectmorethan":
			number = Integer.parseInt(word);
			return this.messages.stream()
						.filter(s -> s.getSubject().split("\\s+").length > number)
						.collect(Collectors.toList());	
			
		default: return null;
		}
	}
	

	
	/**
	 * return the amount of messages in the MailBox
	 * ( = amount of messages for a user)
	 * @return
	 */
	public int messageAmount() {
		return this.messages.size();
	}

}
