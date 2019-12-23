package org.brightlife.api.service.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.OtpEntity;
import org.brightlife.api.service.entity.UserEntity;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.repository.OtpRepo;
import org.brightlife.api.service.repository.UserRepo;
import org.brightlife.api.service.utils.EmailSenderProperties;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spencerwi.either.Either;

@Service
@Transactional
public class VerificationService {
	@Autowired
	private OtpRepo otpRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SessionService sessionService;

	public Either<ErrorData, UserSession> validateOTP(String email, String otp) {

		OtpEntity otpEntity = new OtpEntity();

		otpEntity = otpRepo.findOtpDetails(email);

		if (otpEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		if (Integer.parseInt(otp) == otpEntity.getOtp() && LocalDateTime.now().isAfter(otpEntity.getIssuedDate())
				&& LocalDateTime.now().isBefore(otpEntity.getValidUpto())) {

			UserEntity userEntity = userRepo.findByEmail(email);
			UserSession userSession = sessionService.createSession(userEntity.getId());
			userSession.setMessage(Constants.OTP_VERIFIED);
			boolean flag = true;
			otpRepo.updateOTPVerifiedStatus(flag, LocalDateTime.now(), email);
			userRepo.updateIsVerified(flag, LocalDateTime.now(), email);
			return Either.right(userSession);

		} else if (LocalDateTime.now().isAfter(otpEntity.getValidUpto()))
			return Either.left(ResponseUtils.createError(ErrorConstants.OTP_TIMED_OUT));
		else
			return Either.left(ResponseUtils.createError(ErrorConstants.INVALID_OTP));

	}

	public boolean sendOTP(String email, String purpose) {

		UserEntity dbUser = userRepo.findByEmail(email);

		if (dbUser == null) {
			return false;
		}

		OtpEntity otpEntity = new OtpEntity();

		StringBuilder generatedToken = new StringBuilder();
		try {
			SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
			for (int i = 0; i < 4; i++) {
				generatedToken.append(number.nextInt(3));
			}

			String random = "" + Math.abs(number.nextInt());
			String OTP = random.substring(0, 4);

			boolean otpSent = sendOTPtoTransporter(OTP, email);
			if (otpSent) {
				otpEntity.setIssuedDate(LocalDateTime.now());
				otpEntity.setValidUpto(LocalDateTime.now().plusHours(1L));
				otpEntity.setOtp(Integer.parseInt(OTP));
				otpEntity.setPurpose(purpose);
				otpEntity.setUserId(dbUser.getId());
				otpEntity.setActive(true);
				otpEntity.setCreatedDate(LocalDateTime.now());
				otpEntity.setLastUpdatedDate(LocalDateTime.now());
				otpEntity.setEmail(dbUser.getEmail());

				otpRepo.save(otpEntity);
				return true;
			} else {
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean sendOTPtoTransporter(String otp, String email) {
		EmailSenderProperties senderProperties = new EmailSenderProperties();
		Session session = Session.getDefaultInstance(senderProperties.getProperties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constants.SENDER_EMAIL, Constants.SENDER_PASSWORD);
			}
		});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(Constants.SENDER_EMAIL));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Verify your Email Address");
			message.setText("Please enter the following OTP   " + otp);
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
