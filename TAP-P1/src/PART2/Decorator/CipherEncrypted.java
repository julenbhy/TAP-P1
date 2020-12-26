package PART2.Decorator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import java.util.ArrayList;
import java.util.Base64;

import PART1.MailStore;
import PART1.Message;

public class CipherEncrypted extends WrapperForTheMailStore{
	
	
	Cipher cipher;
	java.security.Key aesKey;
	
	public CipherEncrypted(MailStore client) throws NoSuchAlgorithmException, NoSuchPaddingException {
		super(client);
		String key = "IWantToPassTAP12"; // 128 bit key
		aesKey =
		new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
		cipher = Cipher.getInstance("AES");
	}


	@Override
	public List<Message> getAllMessages() throws FileNotFoundException {
		List<Message> reverse = super.client.getAllMessages();
		List<Message> result = new ArrayList<>();
		for(Message msg:reverse) {
			result.add(new Message(msg.getSender(), msg.getReceiver(), msg.getSubject(), decrypt(msg.getBody())));
		}
		return result;
	}

	@Override
	public void sendMail(Message mail) throws IOException {
		Message result = new Message(mail.getSender(), mail.getReceiver(), mail.getSubject(), encrypt(mail.getBody()));
		super.client.sendMail(result);		
	}

	@Override
	public List<Message> getMails(String user) throws FileNotFoundException {
		List<Message> reverse = super.client.getMails(user);
		List<Message> result = new ArrayList<>();
		for(Message msg:reverse) {
			result.add(new Message(msg.getSender(), msg.getReceiver(), msg.getSubject(), decrypt(msg.getBody())));
		}
		return result;
	}
	
	private String encrypt(String body) {
		byte[] encrypted = new byte[0];
		try {
		 cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		 encrypted = cipher.doFinal(body.getBytes());
		} catch (Exception e) {
		 e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(encrypted);
		
	}
	
	private String decrypt(String body) {
		byte[] encrypted = Base64.getDecoder().decode(body.getBytes());
		String decrypted = null;
		try {
		 cipher.init(Cipher.DECRYPT_MODE, aesKey);
		 decrypted = new String(cipher.doFinal(encrypted));
		} catch (Exception e) {
		 e.printStackTrace();
		}
		return decrypted;

		
	}

}
