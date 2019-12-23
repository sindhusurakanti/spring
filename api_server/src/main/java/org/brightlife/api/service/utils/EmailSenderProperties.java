package org.brightlife.api.service.utils;

import java.io.InputStream;
import java.util.Properties;

public class EmailSenderProperties {

	public Properties getProperties() {
		Properties props = new Properties();
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream("emailsender.properties");
			props.load(in);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;

	}
}
