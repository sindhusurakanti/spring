package org.brightlife.api.service.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.UserEntity;
import org.brightlife.api.service.model.SocialUser;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.repository.UserRepo;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.spencerwi.either.Either;

@Service
@Transactional
public class SocialMediaService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private VerificationService verificationService;

	public Either<ErrorData, UserSession> getUserDetails(SocialUser socialUser) {
		try {
			UserEntity userEntity = null;
			if (socialUser.getFacebookId() != null) {
				userEntity = userRepo.findFacebookId(socialUser.getFacebookId());
			} else if (socialUser.getGoogleId() != null) {
				userEntity = userRepo.findGoogleId(socialUser.getGoogleId());
			}

			if (userEntity != null) {
				if(userEntity.getEmail().isEmpty()) {
					return Either.left(ResponseUtils.createError(ErrorConstants.REQUIRE_EMAIL_WITH_SIGNUP));
				}
				if (userEntity.getIsVerified() == false) {
					String purpose = Constants.LOGIN_WITH_SOCIAL_MEDIA;
					verificationService.sendOTP(userEntity.getEmail(), purpose);
					UserSession userSession = new UserSession();
					userSession.setMessage(Constants.OTP_SENT);
					return Either.right(userSession);

				}
				return Either.right(sessionService.createSession(userEntity.getId()));

			}

			if (StringUtils.isNullOrEmpty(socialUser.getEmail())) {
				return Either.left(ResponseUtils.createError(ErrorConstants.REQUIRE_EMAIL_WITH_SIGNUP));
			}

			userEntity = userRepo.findByEmail(socialUser.getEmail());
			if (userEntity != null) {
				if (socialUser.getFacebookId() != null) {
					userRepo.updateFacebookId(socialUser.getFacebookId(), LocalDateTime.now(), userEntity.getEmail());
				} else if (socialUser.getGoogleId() != null) {
					userRepo.updateGoogleId(socialUser.getGoogleId(), LocalDateTime.now(), userEntity.getEmail());
				}
				return Either.right(sessionService.createSession(userEntity.getId()));
			} else {
				userEntity = new UserEntity();
				userEntity.setEmail(socialUser.getEmail());
				userEntity.setName(socialUser.getName());

				if (socialUser.getFacebookId() != null) {
					userEntity.setFacebookId(socialUser.getFacebookId());
				} else if (socialUser.getGoogleId() != null) {
					userEntity.setGoogleId(socialUser.getGoogleId());
				}
				
				userEntity.setCreatedBy("testing");
				userEntity.setCreatedDate(LocalDateTime.now());
				userEntity.setLastUpdatedBy("testing");
				userEntity.setLastUpdatedDate(LocalDateTime.now());
				userEntity.setIsActive(true);
				System.out.println(socialUser.getEmail() + " " + socialUser.getName());
				userRepo.save(userEntity);

				String purpose = Constants.LOGIN_WITH_SOCIAL_MEDIA;
				verificationService.sendOTP(userEntity.getEmail(), purpose);

				// userEntity = userRepo.findByEmail(userEntity.getEmail());

				//sessionService.createSession(userEntity.getId())
										 
				UserSession userSession = new UserSession();
				userSession.setMessage(Constants.OTP_SENT);
				System.out.println(userSession.getMessage());
				return Either.right(userSession);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.REQUIRE_EMAIL_WITH_SIGNUP));
	}

	/*
	 * public Either<ValidationErrorData, UserSession>
	 * updateEmailDetails(SocialUser userDetails) { try { UserEntity userEntity
	 * = new UserEntity();
	 * 
	 * 
	 * userEntity.setEmail(userDetails.getEmail());
	 * userEntity.setName(userDetails.getName());
	 * userEntity.setCreatedDate(LocalDateTime.now());
	 * 
	 * if (userDetails.getFacebookId() != null)
	 * userEntity.setFacebookId(userDetails.getFacebookId()); else if
	 * (userDetails.getGoogleId() != null)
	 * userEntity.setGoogleId(userDetails.getGoogleId());
	 * 
	 * userRepo.save(userEntity);
	 * verificationService.getOTPDetails(userDetails.getEmail(), purpose);
	 * 
	 * UserSession userSession = new UserSession();
	 * userSession.setMessage(Constants.OTP_SENT); return
	 * Either.right(userSession);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * ValidationErrorData errData = new ValidationErrorData();
	 * errData.setMessage
	 * (ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_ERROR)); return
	 * Either.left(errData); }
	 */

}
