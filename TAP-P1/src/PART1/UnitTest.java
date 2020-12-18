/**
 * 
 */
package PART1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class UnitTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		
		MailSystem mSystem = new MailSystem();
		MailBox albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		MailBox julenbhy = mSystem.createUser("Julenbhy", "Julen", 1899);
		MailBox albertoChichote = mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		
		//albigle.sendMail(destination, subject, body);

		
		
		
		//para comprobar la ordenacion
		
				List<Message> lista = new ArrayList<Message>();
				Message a = new Message("Alberto", "Jon", "Me perdonas?", "el bodkjmhnybgtyfvtrcvgbhy");
				TimeUnit.SECONDS.sleep(2);
				Message b = new Message("Alberto", "Julen", "kuku", "treaadsfasd");
				TimeUnit.SECONDS.sleep(2);
				Message c = new Message("kdasfjf", "Jon", "Gilipollas", "edafsvgbhy");
				
				
				
				lista.add(b);
				lista.add(a);
				lista.add(c);
				lista.add(a);
				
				String fecha = "31/11/2020";
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
				System.out.println(date);
				
				System.out.println(lista);
				lista = lista.stream()
						  .sorted((o1,o2) -> o1.getTime().compareTo(o2.getTime()))
						  .collect(Collectors.toList());
				System.out.println("lista ordenada:" + lista);
				Collections.reverse(lista);
				System.out.println("lista invertida:" + lista);
	}

}
