/**
 * 
 */
package PART2;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import PART1.*;




/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class UnitTestDecorator {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub

		MailSystemPart2 mSystem = new MailSystemPart2("unitTestFile2.txt");
		mSystem.ReverseEncrypted();
		mSystem.CipherEncrypted();
		
		//Create 4 users
		MailBox albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		MailBox julenbhy = mSystem.createUser("Julenbhy", "Julen", 1899);
		MailBox albertoChicote = mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		MailBox albertoCFC = mSystem.createUser("AlbertoChicoteFansClub", "FansClub", 2007);
		
		//Send a few mails we add some delay between each sent message, for having different dates
		albigle.sendMail("Julenbhy", "subject", "body para julen");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("Albigle", "Hola", "Hola a mi mismo");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("Julenbhy", "Eguberri on", "Hola julen, eguberri on eta urte berri on");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("Julenbhy", "subject2", "segundo body para body");
		TimeUnit.SECONDS.sleep(1);
		albertoChicote.sendMail("Julenbhy", "subject", "otro body para julen");
		TimeUnit.SECONDS.sleep(1);
		albertoChicote.sendMail("Julenbhy", "non subject", "Hola, gracias por ser mi fan n� 1 :)");
		TimeUnit.SECONDS.sleep(1);
		julenbhy.sendMail("Albigle", "Hola", "Muy buenas");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("AlbertoChicote", "subject", "Body para chicote");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Somos tus fans", "Somos tus fans n� 1 nos sigues xfaa");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Felicidades Alberto", "Nos gusta mucho pesadilla en la cocina");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("JulenBhy", "Bienvenido Julen", "Gracias por formar parte del club");
		
		System.out.println("  - Julen Bohoyo's mailbox");
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		
		System.out.println("\n  - Julen Bohoyo's mailbox after reverse encription");
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		
	}

}