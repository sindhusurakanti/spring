package org.brightlife.api.service.handler;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.password.ChangePasswordWithOTPRequest;
import org.brightlife.api.service.model.dto.request.password.ForgotPasswordRequest;
import org.brightlife.api.service.model.dto.request.password.ResendOTPRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class PasswordHandler extends RequestHandler {

	public PasswordHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public ValidationErrorData validate(Request req) {

		if (req instanceof ChangePasswordWithOTPRequest) {
			return validate((ChangePasswordWithOTPRequest) req);
		} else if (req instanceof ForgotPasswordRequest) {
			return validate((ForgotPasswordRequest) req);
		}

		return new ValidationErrorData();
	}

	private ValidationErrorData validate(ChangePasswordWithOTPRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getEmail()) && ValidationUtils.isValidEmail(req.getEmail())) {
			errData.addError(new ValidationError("email", "email is invalid", "invalid_email"));

		}

		if (ValidationUtils.isNullOrEmpty(req.getPassword()) && ValidationUtils.isValidPassword(req.getPassword())) {
			errData.addError(new ValidationError("Password", "Password is invalid", "invalid_password"));
		}

		if (ValidationUtils.isNullOrEmpty(req.getOtp()) && ValidationUtils.isNumeric(req.getOtp())) {
			errData.addError(new ValidationError("OTP", "OTP is invalid", "invalid_OTP"));
		}
		return errData;
	}

	private ValidationErrorData validate(ForgotPasswordRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getEmail()) && ValidationUtils.isValidEmail(req.getEmail())) {
			errData.addError(new ValidationError("email", "email is invalid", "invalid_email"));
		}
		return errData;
	}

	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (req instanceof ChangePasswordWithOTPRequest) {
			return processChangeWithOTPRequest((ChangePasswordWithOTPRequest) req);
		} else if (req instanceof ForgotPasswordRequest) {
			return processForgotPasswordRequest((ForgotPasswordRequest) req);
		} else if (req instanceof ResendOTPRequest) {
			return processResendOTPRequest((ResendOTPRequest) req);
		}

		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));
		return ResponseUtils.errorResponse(errData);
	}

	private APIResponse processResendOTPRequest(ResendOTPRequest req) {
		boolean sendFlag = host.getPasswordService().resendOTP(req.getEmail());
		if (sendFlag) {
			return ResponseUtils.simpleSuccessResponse(Constants.OTP_SENT);
		}
		return ResponseUtils.errorResponse(ErrorConstants.INVALID_EMAIL);
	}

	public APIResponse processForgotPasswordRequest(ForgotPasswordRequest req) {
		String purpose = Constants.FORGOT_PASSWORD;
		boolean sendOTP = host.getVerificationService().sendOTP(req.getEmail(), purpose);
		if (sendOTP) {
			return ResponseUtils.simpleSuccessResponse(Constants.OTP_SENT);
		} else {
			return ResponseUtils.errorResponse(ErrorConstants.SEND_OTP_FAILED);
		}
	}

	public APIResponse processChangeWithOTPRequest(ChangePasswordWithOTPRequest req) {
		Either<ErrorData, String> res = host.getPasswordService().updatePasswordWithOTP(req.getEmail(),
				req.getPassword(), req.getOtp());
		if (res.isLeft()) {
			return ResponseUtils.errorResponse(res.getLeft());
		} else {
			return ResponseUtils.simpleSuccessResponse(res.getRight());
		}
	}

}
