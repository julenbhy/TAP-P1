package PART2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
 
import PART1.MailStore;
import PART1.Message;

//en el enunciado dice tenemos que usar Decorator y Strategy, osea que queremos hacer un decorator que a su vez dentro tenga
//dos implementaciones gracias a strategi, strategi se suele hacer con interface, pero como ahora lo harermos dentro de un 
//decorator pues tenemos que usar una abstract class.
//en este enlace explican justo esto https://stackoverflow.com/questions/25098726/using-an-abstract-class-instead-of-the-interface-in-the-strategy-design-pattern
public abstract class WrapperForTheMailStore implements MailStore{
	
	private MailStore client;
	
	public WrapperForTheMailStore(MailStore client) {
		super();
		this.client = client;
	}

	public void sendMail(Message mail) throws IOException
	{
		StringBuilder output = new StringBuilder(mail.getBody()).reverse();
		Message result = new Message(mail.getSender(), mail.getReceiver(), mail.getSubject(), output.toString());
		client.sendMail(result);
	}

	public abstract List<Message> getMails(String user) throws FileNotFoundException;
}