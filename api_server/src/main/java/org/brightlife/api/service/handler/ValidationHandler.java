package org.brightlife.api.service.handler;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.validation.VerifyEmailWithOTPRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class ValidationHandler extends RequestHandler {
	public ValidationHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public ValidationErrorData validate(Request req) {
		if (req instanceof VerifyEmailWithOTPRequest) {
			return validate((VerifyEmailWithOTPRequest) req);
		}
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(VerifyEmailWithOTPRequest req){
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getEmail()) || !ValidationUtils.isValidEmail(req.getEmail())) {
			errData.addError(new ValidationError("Email", "Email is invalid", "invalid_email"));
		}
		return errData;
	}
	
	
	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();

		if (req instanceof VerifyEmailWithOTPRequest) {
			return processVerifyEmail((VerifyEmailWithOTPRequest) req);

		}
		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));
		return ResponseUtils.errorResponse(errData);
	}

	private APIResponse processVerifyEmail(VerifyEmailWithOTPRequest req) {
		Either<ErrorData, UserSession> res = host.getVerificationService().validateOTP(req.getEmail(),
				req.getOtp());
		if (res.isLeft()) {
			return ResponseUtils.errorResponse(res.getLeft());
		} else {
			return ResponseUtils.successResponse(res.getRight());
		}
	}

}
