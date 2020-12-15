/**
 * 
 */
package PART1;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 */
public class User {
		private String userName, name;
		private int yearOfBirth;
		
		
		/**
		 * @param userName
		 * @param name
		 * @param yearOfBirth
		 */
		public User(String userName, String name, int yearOfBirth) {
			this.userName = userName;
			this.name = name;
			this.yearOfBirth = yearOfBirth;
		}

		/**
		 * @return the userName
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the yearOfBirth
		 */
		public int getYearOfBirth() {
			return yearOfBirth;
		}

		
		public void printUser() {
			System.out.println("User [userName=" + userName + ", name=" + name + ", yearOfBirth=" + yearOfBirth + "]");
		}
		
		
		
		
		
		
		

}
