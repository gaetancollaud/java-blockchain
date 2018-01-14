package net.collaud.gaetan.blockchain.utils;

import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@UtilityClass
public class CryptoUtil {

	public static final Charset CHARSET = StandardCharsets.UTF_8;

	public static String sha256(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(CHARSET));
			return new String(hash, CHARSET);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Unable to compute SHA-256", e);
		}
	}
}
