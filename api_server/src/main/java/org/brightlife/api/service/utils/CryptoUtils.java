package org.brightlife.api.service.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.tomcat.util.codec.binary.Base64;

public class CryptoUtils {

	MessageDigest digestProvider = getMessageDigest();
	SecureRandom secureRandomGenerator = getSecureRandom();
	char[] charset = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'K', 'L', '!', '@', '#', '$', '%', '^', '&', '*', '(',
			')', '+', '=', '/', '*', '\\', '"' };

	MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-1");
		} catch (Exception e) {
			return null;
		}
	}

	SecureRandom getSecureRandom() {
		try {
			return SecureRandom.getInstance("SHA1PRNG");
		} catch (Exception e) {
			return null;
		}
	}

	public synchronized String encrypt(String salt, String password) {
		try {
			digestProvider.update((salt + password).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Base64().encodeAsString(digestProvider.digest());
	}

	public synchronized String salt() {
		char[] salt = new char[10];
		for (int index = 0; index < 10; index++) {
			salt[index] = charset[secureRandomGenerator.nextInt(Math.abs((int) Math.random() + 1))];
		}
		return salt.toString();
	}

	public synchronized String newRandomPassword(int size) {
		char[] password = new char[size];
		for (int index = 0; index < size; index++) {
			password[index] = charset[secureRandomGenerator.nextInt(charset.length)];
		}
		return password.toString();
	}
}