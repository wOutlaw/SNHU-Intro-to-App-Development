import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to parse credentials.txt and return file information.
 *  
 * @author warren.outlaw@snhu.edu
 *
 */
public class Parse {
	
	private int index;
	private int lastIndex;
	private String userHash;
	private String userPassword;
	private String userRole;
	private String newLine;
	private Scanner inSS;
	private BufferedReader br;
	
	/**
	 * Reads the appropriate MD5 hash, password, and user role from the
	 * credentials file if the user inputs a valid user name.
	 * 
	 * @param userName - user's user name input from credential's screen
	 */	
	public void getCredentials(String userName) {
		String currentLine = null;
		
		try {
			
			// Open credentials.txt for parsing.
			FileReader userInfo = new FileReader("credentials.txt");
			br = new BufferedReader(userInfo);
			
			while ((currentLine = br.readLine()) != null) {
                
				// Parse the line if it contains the user name.
				if (currentLine.contains(userName)) {
					
					// Find the password based on indexes of the quotes.
					index = currentLine.indexOf("\"");
					lastIndex = currentLine.lastIndexOf("\"");
					userPassword = currentLine.substring(index + 1, lastIndex);
					
					// Remove the password and leftover quotes for easy parsing.
					newLine = currentLine.replace(userPassword, "").replace("\"", "");
					inSS = new Scanner(newLine);
					
					inSS.next();
					this.userHash = inSS.next();
					this.userRole = inSS.next();
					
					break;
				}
            }
		} catch (FileNotFoundException e) {
			System.out.println(e);
			// Return if file not found so that the user isn't stuck in the login loop.
			return;
		} catch (IOException ioe) {
		    System.out.println(ioe);
		    return;
		}
	}
	
	/**
	 * Returns the MD5 password hash.
	 * @return a string representing the credentials.txt MD5 hash
	 */
	String getUserHash() {
		return this.userHash;
	}
	
	/**
	 * Returns the user role.
	 * @return a string representing the credentials.txt user role
	 */
	String getUserRole() {
		return this.userRole;
	}
}
