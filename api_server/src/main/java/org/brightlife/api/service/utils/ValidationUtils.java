package org.brightlife.api.service.utils;

import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class ValidationUtils {

	public static boolean isNullOrEmpty(String s) {
		return StringUtils.isNullOrEmpty(s);
	}

	public static boolean isValidEmail(String s) {
		Pattern emailPattern = RegExUtils.getEMailPattern();
		return emailPattern.matcher(s).matches();
	}

	public static boolean isValidPassword(String s) {
		Pattern passwordPattern = RegExUtils.getPasswordPattern();
		return passwordPattern.matcher(s).matches();
	}

	public static boolean isNumeric(String s) {
		Pattern numPattern = RegExUtils.getNumberPattern();
		return numPattern.matcher(s).matches();
	}
	public  static boolean isNullOrEmpty(Long l) {
		return StringUtils.isNullOrEmpty(l);
	}
	public  static boolean isNullOrEmpty(Integer l) {
		return StringUtils.isNullOrEmpty(l);
	}
	public  static boolean isNullOrEmpty(MultipartFile l) {
		return StringUtils.isNullOrEmpty(l);
	}
}
