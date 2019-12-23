package org.brightlife.api.service.service;

import java.time.LocalDateTime;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.UserEntity;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.repository.UserRepo;
import org.brightlife.api.service.utils.CryptoUtils;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spencerwi.either.Either;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private VerificationService verificationService;
	@Autowired
	private SessionService sessionService;

	public Either<ErrorData, UserSession> signupWithEMail(String name, String email, String password) {
		System.out.println(name + " " + email + " " + password);

		String purpose = Constants.EMAIL_VERIFIACTION;

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(email);
		UserEntity dbUser = userRepo.findByEmail(email);

		if (dbUser != null) {
			return Either.left(ResponseUtils.createError(ErrorConstants.EMAIL_EXISTS));
		}
		try {
			String unique = StringUtils.convertToCode(name);

			CryptoUtils cryptoUtils = new CryptoUtils();
			String salt = cryptoUtils.salt();
			userEntity.setPassword(cryptoUtils.encrypt(salt, password));
			userEntity.setSalt(salt);
			userEntity.setCode(unique);
			userEntity.setName(name);
			userEntity.setIsActive(true);
			userEntity.setCreatedBy("testing");
			userEntity.setLastUpdatedBy("testing");
			userEntity.setCreatedDate(LocalDateTime.now());
			userEntity.setLastUpdatedDate(LocalDateTime.now());

			userRepo.save(userEntity);

			boolean otpSent = verificationService.sendOTP(email, purpose);
			if (otpSent) {
				UserSession userSession = new UserSession();
				userSession.setMessage(Constants.OTP_SENT);
				return Either.right(userSession);
			} else
				return Either.left(ResponseUtils.createError(ErrorConstants.SEND_OTP_FAILED));

		} catch (Exception ex) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, UserSession> loginWithEmail(String email, String password) {

		System.out.println(email + " " + password);

		UserEntity userEntity = userRepo.findByEmail(email);

		ValidationErrorData errData = new ValidationErrorData();
		if (userEntity != null) {

			if (userEntity.getIsVerified()) {
				CryptoUtils crypto = new CryptoUtils();
				String encryptedPassword = password;
				encryptedPassword = crypto.encrypt(userEntity.getSalt(), encryptedPassword);
				if (encryptedPassword.equals(userEntity.getPassword())) {

					UserSession session = sessionService.createSession(userEntity.getId());

					return Either.right(session);
				}
				errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.INVALID_USER_NAME_PASSWORD));
				return Either.left(errData);
			} else {
				errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.VERIFY_EMAIL_WITH_OTP));
				return Either.left(errData);
			}

		}
		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.INVALID_EMAIL));
		return Either.left(errData);
	}
}
