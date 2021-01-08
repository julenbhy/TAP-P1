package PART2.Decorator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import PART1.MailStore;
import PART1.Message;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class ReverseEncrypted extends WrapperForTheMailStore {

	/**
	 * Constructor for RevereseEncrypted 
	 * @param client is the MailStore that we want to encrypt
	 */
	public ReverseEncrypted(MailStore client) {
	super(client);
		// TODO Auto-generated constructor stub
	}

		@Override
	public List<Message> getAllMessages() throws FileNotFoundException {
		List<Message> reverse = super.client.getAllMessages();
		List<Message> result = new ArrayList<>();
		for(Message msg:reverse) {
			StringBuilder output = new StringBuilder(msg.getBody()).reverse();
			result.add(new Message(msg.getSender(), msg.getReceiver(), msg.getSubject(), output.toString()));
		}
		return result;
	}

	@Override
	public void sendMail(Message mail) throws IOException {
		StringBuilder output = new StringBuilder(mail.getBody()).reverse();
		Message result = new Message(mail.getSender(), mail.getReceiver(), mail.getSubject(), output.toString());
		super.client.sendMail(result);
	}

	@Override
	public List<Message> getMails(String user) throws FileNotFoundException {
		List<Message> reverse = super.client.getMails(user);
		List<Message> result = new ArrayList<>();
		for(Message msg:reverse) {
			StringBuilder output = new StringBuilder(msg.getBody()).reverse();
			result.add(new Message(msg.getSender(), msg.getReceiver(), msg.getSubject(), output.toString()));
		}
		return result;
	}

}
