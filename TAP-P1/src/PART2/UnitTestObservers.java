/**
 * 
 */
package PART2;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;


import PART2.Observer.*;



/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class UnitTestObservers {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub

		MailSystemPart2 mSystem = new MailSystemPart2();
	
		ObservableMailBox albigle = (ObservableMailBox) mSystem.createUser("Albigle", "Alberto", 1999);
		ObservableMailBox julenbhy = (ObservableMailBox) mSystem.createUser("Julenbhy", "Julen", 1899);
		ObservableMailBox albertoChicote = (ObservableMailBox) mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		ObservableMailBox albertoCFC = (ObservableMailBox) mSystem.createUser("AlbertoChicoteFansClub", "FansClub", 2007);
		
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
		albertoChicote.sendMail("Julenbhy", "non subject", "Hola, gracias por ser mi fan numero 1 :) para saber mas sobre mi sigueme en mis redes sociales (Facebook, Instagram y Twitter) ");
		TimeUnit.SECONDS.sleep(1);
		julenbhy.sendMail("Albigle", "Hola", "Muy buenas");
		TimeUnit.SECONDS.sleep(1);
		albigle.sendMail("AlbertoChicote", "Problemas en la cocina", "Buenos dias Alberto, actualmente llevo un restaurante que no esta funcionando muy bien podrías ayudarme a sacarlo adelante, sino me arruinare en menos de un mes. AYUDA");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Somos tus fans", "Somos tus fans numero 1 nos sigues xfaa");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("AlbertoChicote", "Felicidades Alberto", "Nos gusta mucho pesadilla en la cocina");
		TimeUnit.SECONDS.sleep(1);
		albertoCFC.sendMail("JulenBhy", "Bienvenido Julen", "Gracias por formar parte del club");
		
		System.out.println("  - Julen Bohoyo's mailbox");
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\n  - Julen Bohoyo's spam mailbox");
		julenbhy.getSpam().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\n- Filter messages with a body with more than 20 characters\n  - Julen Bohoyo's mailbox");
		julenbhy.addObserver(new TooLongFilter());
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\n  - Julen Bohoyo's spam mailbox");
		julenbhy.getSpam().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\n- Filter messages whose sender username is albigle\n  - Julen Bohoyo's mailbox");
		julenbhy.addObserver(new SpamUserFilter("albigle"));
		julenbhy.updateMail();
		julenbhy.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\n  - Julen Bohoyo's spam mailbox");
		julenbhy.getSpam().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("  - Alberto Chicote's mailbox");
		albertoChicote.updateMail();
		albertoChicote.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\n  - Alberto Chicote's spam mailbox");
		albertoChicote.getSpam().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\n- Filter messages with a body with more than 20 characters\n  - Alberto Chicote's mailbox");
		albertoChicote.addObserver(new TooLongFilter());
		albertoChicote.updateMail();
		albertoChicote.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\n  - Alberto Chicote's spam mailbox");
		albertoChicote.getSpam().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
		System.out.println("\n- Filter messages whose sender username contains fansclub word\n  - Alberto Chicote's mailbox");
		albertoChicote.addObserver(new SpamUserFilter("fansclub"));
		albertoChicote.updateMail();
		albertoChicote.listMail().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		System.out.println("\n  - Alberto Chicote's spam mailbox");
		albertoChicote.getSpam().stream().forEach(s -> System.out.println("Message from: "+s.getSender()+", to: "+s.getReceiver()+
				"\n\tSubject: "+s.getSubject()+"\n\tBody: "+s.getBody()+"\n\tDate: "+s.getDate()));
		
	}

}
