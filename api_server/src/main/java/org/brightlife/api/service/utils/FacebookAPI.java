package org.brightlife.api.service.utils;

import org.brightlife.api.service.model.SocialUser;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonObject;

public class FacebookAPI {

	public SocialUser getUserInfo(String token) {

		new GoolgeAPI().getUserInfo(token);

		FacebookClient facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_10);
		JsonObject user = facebookClient.fetchObject("me", JsonObject.class);
		System.out.println(user);
		SocialUser userEntity = new SocialUser();

		if (!user.contains("id")) {
			return userEntity;
		}

		String email = "";
		String facebookId = "";
		String name = "";

		System.out.println(user.get("id"));
		System.out.println(user.get("email"));

		if (user.contains("id") && user.get("id") != null) {
			facebookId = user.get("id").toString().substring(1, user.get("id").toString().length() - 1);
			userEntity.setFacebookId(facebookId);
		}
		if (user.contains("email") && user.get("email") != null) {
			email = user.get("email").toString().substring(1, user.get("email").toString().length() - 1);
			userEntity.setEmail(email);

		}

		if (user.contains("name") && user.get("name") != null) {
			name = user.get("name").toString().substring(1, user.get("name").toString().length() - 1);
			userEntity.setName(name);
		}
		return userEntity;

	}

}
