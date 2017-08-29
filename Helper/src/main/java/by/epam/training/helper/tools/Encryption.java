package by.epam.training.helper.tools;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import by.epam.training.helper.constant.ParameterName;

public class Encryption {
	public static String getHahsCode(String login, String password) throws NoSuchAlgorithmException {
		String text = login.concat(password);
		MessageDigest messageDigest = MessageDigest.getInstance(ParameterName.ALGORITHM);
		messageDigest.update(text.getBytes(Charset.forName(ParameterName.ECODING)));
		return Hex.encodeHexString(messageDigest.digest());
	}
}
