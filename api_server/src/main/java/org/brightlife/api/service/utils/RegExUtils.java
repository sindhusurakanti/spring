package org.brightlife.api.service.utils;

import java.util.regex.Pattern;

import org.brightlife.api.service.consts.Constants;

public class RegExUtils {
	static Pattern emailPattern = Pattern.compile(Constants.EMAIL_PATTERN); 
	public static Pattern getEMailPattern(){
		return emailPattern;
	}
	
	public static Pattern getPasswordPattern(){
		return Pattern.compile(Constants.PASSWORD_PATTERN);
	}

	public static Pattern getNumberPattern(){
		return Pattern.compile(Constants.NUMBER_PATTERN);
	}
}
