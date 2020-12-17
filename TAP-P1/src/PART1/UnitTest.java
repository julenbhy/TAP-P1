/**
 * 
 */
package PART1;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
public class UnitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MailSystem mSystem = new MailSystem();
		MailBox albigle = mSystem.createUser("Albigle", "Alberto", 1999);
		MailBox julenbhy = mSystem.createUser("Julenbhy", "Julen", 1899);
		MailBox albertoChichote = mSystem.createUser("AlbertoChicote", "Alberto", 1969);
		
		//albigle.sendMail(destination, subject, body);

	}

}
