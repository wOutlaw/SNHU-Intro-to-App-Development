import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Class to perform the authentication process.
 *  
 * @author warren.outlaw@snhu.edu
 *
 */
public class Authenticate {
	
	private static Scanner scnr = new Scanner(System.in);
	private static String userName;
	private static String userPassword;
	private static String digest;
	private static int attempts;

	/**
	 * Main authentication loop.
	 */
	public void tryLogin() {
		
		Parse parse = new Parse();
		Authorize authorize = new Authorize();
		MD5Digest md5 = new MD5Digest();
		
		// Limit user to three log in attempts.
		while (attempts < 3) {
			
			// Prompt for a user name.
			System.out.println("Username: ");
			userName = scnr.nextLine();
			
			// Prompt for a password.
			System.out.println("Password: ");
			userPassword = scnr.nextLine();
			
			// Convert the password to MD5 hash and get other credential information.
			try {
				digest = md5.getMDHash(userPassword);
			} catch (NoSuchAlgorithmException e) {
				System.out.println(e);
			}
			parse.getCredentials(userName);
			
			// Perform authorization if MD5 hash matches the credentials file.
			if (digest.equals(parse.getUserHash())) {
				authorize.displayRole(parse.getUserRole());
				return;
			}
			
			// Error message on invalid log in attempt.
			System.out.println("\nIncorrect username and/or password.\n");
			attempts++;
		}
		
		// Display message after three failed log in attempts.
		System.out.println("Maximum attempts exceeded.  Terminating."
				+ "\n");
		return;
	}
	
}
