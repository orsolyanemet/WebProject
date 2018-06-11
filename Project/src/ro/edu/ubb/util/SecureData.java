package ro.edu.ubb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class to secure data.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class SecureData {

	public static byte[] hashPassword(String password) {
		MessageDigest md;
		byte byteData[] = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			byteData = md.digest();
		} catch (NoSuchAlgorithmException e) {
		} catch (NullPointerException e) {
			throw new UtilException("An error occured while hashing.");
		}
		return byteData;

	}

	public static String convertHexToString(byte[] byteData) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
