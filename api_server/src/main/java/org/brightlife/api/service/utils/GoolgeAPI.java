package org.brightlife.api.service.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.brightlife.api.service.model.SocialUser;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
public class GoolgeAPI {
	private static final List<String> SCOPES = Arrays.asList( "https://www.googleapis.com/auth/userinfo.profile",
            "https://www.googleapis.com/auth/userinfo.email");

	public SocialUser getUserInfo(String token) {

		Userinfoplus userinfo = null;
		SocialUser user = new SocialUser();
		try {
			
			
			GoogleCredential credential = new GoogleCredential().setAccessToken(token);
			credential.getClientAuthentication();
			credential.createScoped(SCOPES);
		    Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(),
		            new JacksonFactory(), credential).setApplicationName("Oauth2").build();
		    
		    
		    userinfo =  oauth2.userinfo().get().execute();
		    System.out.println(userinfo.getId());
		    System.out.println(userinfo.getEmail());
		    System.out.println(userinfo.getName());
		    
		    user.setGoogleId(userinfo.getId());
		    user.setEmail(userinfo.getEmail());
		    user.setName(userinfo.getName());
		    

		} catch ( IOException e) {
			e.printStackTrace();
		}

		return user;
	}

}
