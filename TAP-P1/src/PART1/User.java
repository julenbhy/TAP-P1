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
		 * @param userName the user name, will be passed to lower case
		 * @param name the name
		 * @param yearOfBirth the year of birth
		 */
		public User(String userName, String name, int yearOfBirth) {
			this.userName = userName.toLowerCase();
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

		/**
		 * prints the user information
		 */
		public void printUser() {
			System.out.println("User [userName=" + userName + ", name=" + name + ", yearOfBirth=" + yearOfBirth + "]");
		}
		
		
		
		
		
		
		

}
