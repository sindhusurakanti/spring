package org.brightlife.api.service.handler;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.SocialUser;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.auth.SignInRequest;
import org.brightlife.api.service.model.dto.request.auth.SignUpRequest;
import org.brightlife.api.service.model.dto.request.auth.SocialSignInRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.FacebookAPI;
import org.brightlife.api.service.utils.GoolgeAPI;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class AuthenticationHandler extends RequestHandler {

	public AuthenticationHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public ValidationErrorData validate(Request req) {
		if (req instanceof SignUpRequest) {
			return validate((SignUpRequest) req);
		} else if (req instanceof SignInRequest) {
			return validate((SignInRequest) req);
		} else if (req instanceof SocialSignInRequest) {
			return validate((SocialSignInRequest) req);
		}
		
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(SignUpRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getEmail()) || !ValidationUtils.isValidEmail(req.getEmail())) {
			errData.addError(new ValidationError("email", "email is invalid", "invalid_email"));
		} 
		
		if (ValidationUtils.isNullOrEmpty(req.getPassword())
				&& ValidationUtils.isValidPassword(req.getPassword())) {
			errData.addError(new ValidationError("Password", "Password is invalid", "invalid_password"));
		}
		
		if (ValidationUtils.isNullOrEmpty(req.getName())) {
			errData.addError(new ValidationError("Name", "Name is invalid", "invalid_name"));
		}
		
		return errData;
	}

	private ValidationErrorData validate(SignInRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getEmail()) || !ValidationUtils.isValidEmail(req.getEmail())) {
			errData.addError(new ValidationError("email", "email is invalid", "invalid_email"));
		} 
		
		if (ValidationUtils.isNullOrEmpty(req.getPassword())) {
			errData.addError(new ValidationError("Password", "Password is invalid", "invalid_password"));
		}
		
		return errData;
	}

	private ValidationErrorData validate(SocialSignInRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getSocialToken())) {
			errData.addError(new ValidationError("Social Token", "Social Token is invalid", "invalid_social_token"));
		} 
		
		if (ValidationUtils.isNullOrEmpty(req.getProvider())) {
			errData.addError(new ValidationError("Identifier", "Identifier is invalid", "invalid_identifier"));
		}
		
		if (!ValidationUtils.isNullOrEmpty(req.getEmail()) && !ValidationUtils.isValidEmail(req.getEmail())) {
			errData.addError(new ValidationError("email", "email is invalid", "invalid_email"));
		}
		
		return errData;
	}

	@Override
	public APIResponse process(Request req) {
		if (req instanceof SignUpRequest) {
			return processSignupRequest((SignUpRequest) req);
		} else if (req instanceof SignInRequest) {
			return processSigninRequest((SignInRequest) req);
		} else if (req instanceof SocialSignInRequest) {
			return processSocialSigninRequest((SocialSignInRequest) req);
		}
		return ResponseUtils.errorResponse(ErrorConstants.UNKNOWN_REQUEST);
	}

	private APIResponse processSocialSigninRequest(SocialSignInRequest req) {
		SocialUser socialUser = null;

		if (req.getProvider().equalsIgnoreCase(Constants.FACEBOOK)) {
			FacebookAPI facebookDetails = new FacebookAPI();
			socialUser = facebookDetails.getUserInfo(req.getSocialToken());
		} else if (req.getProvider().equalsIgnoreCase(Constants.GOOGLE)) {
			GoolgeAPI googleDetails = new GoolgeAPI();
			socialUser = googleDetails.getUserInfo(req.getSocialToken());
		}
		
		if (socialUser == null) {
			return ResponseUtils.errorResponse(ErrorConstants.INVALID_SOCIAL_MEDIA_TOKEN);
		}
		
		if (socialUser.getEmail() == null) {
			if(req.getEmail() != null) {
				socialUser.setEmail(req.getEmail());
			}
			
		}
		Either<ErrorData, UserSession> res = host.getSocialMediaService().getUserDetails(socialUser);
		return createResponse(res);
	}

	private APIResponse processSignupRequest(SignUpRequest req) {
		Either<ErrorData, UserSession> res = host.getAuthService().signupWithEMail(req.getName(),
				req.getEmail(), req.getPassword());
		return createResponse(res);
	}

	private APIResponse processSigninRequest(SignInRequest req) {
		Either<ErrorData, UserSession> res = host.getAuthService().loginWithEmail(req.getEmail(),
				req.getPassword());
		return createResponse(res);
	}
	
	private APIResponse createResponse(Either<ErrorData, UserSession> res){
		if (res.isLeft()) {
			return ResponseUtils.errorResponse(res.getLeft());
		} else {
			return ResponseUtils.successResponse(res.getRight());
		}
	}
}
