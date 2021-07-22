import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Authorize access based on the authenticated user's role in 
 * credentials.txt
 * 
 * @author warren.outlaw@snhu.edu
 *
 */
public class Authorize {
	
	private Scanner scnr = new Scanner(System.in);
	private BufferedReader br;
	private String fileLocation;
	private int userChoice;
	
	/**
	 * Display contents of the appropriate role file.
	 * 
	 * @param userRole - user role from credentials.txt
	 */
	public void displayRole(String userRole) {
		String currentLine = null;
		
		// Set file location for admin.
		if (userRole.equals("admin")) {
			fileLocation = "admin.txt";
		}
		
		// Set file location for zookeeper.
		else if (userRole.equals("zookeeper")) {
			fileLocation = "zookeeper.txt";
		}
		
		// Set file location for veterinarian.
		else if (userRole.equals("veterinarian")) {
			fileLocation = "veterinarian.txt";
		}
		
		try {
			FileReader userInfo = new FileReader(fileLocation);
			br = new BufferedReader(userInfo);
			
			// Display each line and loop until end of file.
			while ((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
			}
            
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException ioe) {
		    System.out.println(ioe);
		}
		
		// Display user options menu, loop until valid input.
		do {
	        try {
	        	
	        	// Log out is the only selection available for now but more could be added.
	        	System.out.println("\nMake a selection:");
				System.out.println("1: Log out");
				userChoice = scnr.nextInt();
				
				if (userChoice == 1) {
					System.out.println("\nYou are now logged out.");
					return;
				}
				
				// Error message for invalid menu selection.
				else {
					System.out.println("\nInvalid input");
				}
			
			// Exception handling for non-integer input.
	        } catch (InputMismatchException e) {
	            System.out.println("\nInvalid input");
	            
	            // Refresh the scanner.
	            scnr.nextLine();
	        }
	    } while (true);
	}
}
