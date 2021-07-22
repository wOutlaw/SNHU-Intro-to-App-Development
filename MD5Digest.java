
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Pre-made MD5 hashing code supplied in the rubric.
 * 
 * @author SNHU
 *
 */
public class MD5Digest {
	
	private String digested;
	
	/**
	 * Convert the user's password input on the credentials screen to MD5 hash.
	 * 
	 * @param userPassword - password from user input
	 * @return a string representing the MD5 digest of the input password
	 * @throws NoSuchAlgorithmException
	 */
	public String getMDHash(String userPassword) throws NoSuchAlgorithmException {
	       
			String original = userPassword;  
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte[] digest = md.digest();
	        StringBuffer sb = new StringBuffer();
	        
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			
			digested = sb.toString();
			
	      return digested;	
	}
}
