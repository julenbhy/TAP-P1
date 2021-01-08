package PART3;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import PART1.MailStore;
import PART1.Message;
import redis.clients.jedis.Jedis;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class MailStoreRedis implements MailStore{

	Jedis jedis = new Jedis("localhost");
	
	@Override
	public void sendMail(Message mail){
		jedis.lpush(mail.getReceiver(), mail.getSender()+";"+mail.getReceiver()+";"+mail.getSubject()+";"+mail.getBody()+";"+mail.getDate());
	}

	@Override
	public List<Message> getMails(String user){
		List<Message> result = new ArrayList<>();
		List<String> redisList= jedis.lrange(user, 0, -1);
		for(String msg:redisList) {
			String[] line = msg.split(";");
			try {
				result.add(new Message(line[0], line[1], line[2], line[3], line[4])); //sender;receiver;subject;body;date
			} catch (ParseException e) {
				System.out.println("Error, can't convert date from file to message");
			}
		}	
		return result;
	}

	@Override
	public List<Message> getAllMessages(){
		List<Message> result = new ArrayList<>();
		Set<String> keys = jedis.keys("*"); //we get all the keys since '*' is a metacharacter
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {				//iterate the key list
			String key = it.next();
			result.addAll(this.getMails(key));	//get all messages for each key and add to the result
		}
		return result;
	}

}
