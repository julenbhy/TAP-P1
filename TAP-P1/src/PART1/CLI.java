/**
 * 
 */
package PART1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class CLI {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
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
		
		/*
		System.out.println(lista);
		lista.sort((o1,o2) -> o1.getTime().compareTo(o2.getTime()));
		System.out.println("lista ordenada:" + lista);
		Collections.reverse(lista);
		System.out.println("lista invertida:" + lista);
		*/

	}

}
