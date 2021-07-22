import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The program below and associated classes were written for
 * my IT-145 Foundation in Application Development final project.
 * 
 * The LogIn class houses the main credential screen loop.
 * 
 * @author warren.outlaw@snhu.edu
 * 
 */
public class LogIn {

	private static Scanner scnr = new Scanner(System.in);
	private static int userChoice;
	
	/**
	 * Allows the user to choose between logging in to the system or exiting.
	 * Exits the program when a user chooses to exit, fails login three times,
	 * or logs out after being authenticated.
	 * 
	 * @param args
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		Authenticate authenticate = new Authenticate();
		
		// Display main menu, loop until valid selection.
		do {
			try {
				System.out.println("---Zoo Authentication System---\n");
				System.out.println("Make a selection:");
				System.out.println("1: Log In");
				System.out.println("2: Exit");
				userChoice = scnr.nextInt();
				
				// Allow user to exit from credentials screen.
				if (userChoice == 2) {
					System.out.println("\nGoodbye!");
					return;
				}
				
				if (userChoice == 1) {
					
					// Refresh the scanner and call the login loop.
					scnr.nextLine();
					authenticate.tryLogin();
					
					/* This break stops the program from returning to the credential
					 * screen after three failed log in attempts or a user log out.
					 * Remove to make the program return to the log in screen.
					 */
					break;
				} 
				
				// Error message for invalid menu selection.
				else {
					System.out.println("\nInvalid input\n");
				}
				
			// Exception handling for non-integer input.
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input\n");
				scnr.nextLine();
		    }
		} while (true);
	}
}

