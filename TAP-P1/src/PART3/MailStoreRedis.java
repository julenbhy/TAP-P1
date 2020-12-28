package PART3;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import PART1.MailStore;
import PART1.Message;
import redis.clients.jedis.Jedis;

public class MailStoreRedis implements MailStore{

	Jedis jedis = new Jedis("localhost");
	
	@Override
	public void sendMail(Message mail){
		String string = mail.getSender()+";"+mail.getReceiver()+";"+mail.getSubject()+";"+mail.getBody()+";"+mail.getDate();
		jedis.lpush(mail.getReceiver(), string );
		System.out.println(jedis.lrange(mail.getReceiver(),0, -1));
	
	}

	@Override
	public List<Message> getMails(String user){
		System.out.println(jedis.lrange("albigle",0, -1));
		List<Message> result = new ArrayList<>();
		List<String> redisList= jedis.lrange(user, 0, -1);
		for(String msg:redisList) {
			String[] line = msg.split(";");
			System.out.println("msg");
			try {
				result.add(new Message(line[0], line[1], line[2], line[3], line[4])); //sender;receiver;subject;body;date
			} catch (ParseException e) {
				System.out.println("Error, can't convert date from file to message");
			}
		}
		jedis.close();		
		return result;
	}

	@Override
	public List<Message> getAllMessages(){
		// TODO Auto-generated method stub
		return null;
	}

}
