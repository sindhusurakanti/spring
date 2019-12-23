package org.brightlife.api.service.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.UserEntity;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.repository.OtpRepo;
import org.brightlife.api.service.repository.SessionRepo;
import org.brightlife.api.service.repository.UserRepo;
import org.brightlife.api.service.utils.CryptoUtils;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencerwi.either.Either;

@Service
@Transactional
public class PasswordService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	SessionRepo sessionRepo;

	@Autowired
	VerificationService verificationService;

	@Autowired
	OtpRepo otpRepo;

	public Either<ErrorData, String> changePassword(long userId, String password, String newPassword) {

		System.out.println(userId + " " + password + " " + newPassword);
		UserEntity userEntity = new UserEntity();
		CryptoUtils cryptoUtils = new CryptoUtils();
		userEntity = userRepo.getOldPasswordAndSalt(userId);
		String oldPassword = cryptoUtils.encrypt(userEntity.getSalt(), password);

		if (oldPassword.equalsIgnoreCase(userEntity.getPassword())) {
			userEntity.setPassword(cryptoUtils.encrypt(userEntity.getSalt(), newPassword));
			userRepo.save(userEntity);
			return Either.right(Constants.PASSWORD_UPDATED);
		} else 
			return Either.left(ResponseUtils.createError(ErrorConstants.INVALID_PASSWORD));
		
	}

	public Either<ErrorData, String> updatePasswordWithOTP(String email, String newPassword, String otp) {
		Either<ErrorData, UserSession> res = verificationService.validateOTP(email, otp);

		if (res.isLeft()) {
			return Either.left(ResponseUtils.createError(ErrorConstants.INVALID_OTP));
		}
		return setNewPassword(email, newPassword);

	}

	private Either<ErrorData, String> setNewPassword(String email, String password) {

		CryptoUtils cryptoUtils = new CryptoUtils();
		String salt = cryptoUtils.salt();
		String encryptedPassword = cryptoUtils.encrypt(salt, password);

		if (userRepo.updatePassword(encryptedPassword, salt, LocalDateTime.now(), true, email) == 1) {
			return Either.right(Constants.PASSWORD_UPDATED);
		} else 
			return Either.left(ResponseUtils.createError(ErrorConstants.UPDATE_PASSWORD_FAILED));
		

	}

	public boolean resendOTP(String email) {
		UserEntity user = userRepo.findByEmail(email);
		if(user != null) {
			int otp = otpRepo.findOtpDetails(email).getOtp();
			boolean sendFlag = verificationService.sendOTPtoTransporter(String.valueOf(otp), email);
			if (sendFlag) {
				return true;
			}
		}
		return false;
	}

}
