package org.brightlife.api.service.handler;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.settings.AssignRoleRequest;
import org.brightlife.api.service.model.dto.request.settings.ChangePasswordRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class SettingsHandler extends RequestHandler {

	public SettingsHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public ValidationErrorData validate(Request req) {
		if (req instanceof AssignRoleRequest) {
			return validate((AssignRoleRequest) req);
		}
		if (req instanceof ChangePasswordRequest) {
			return validate((ChangePasswordRequest) req);
		}

		return new ValidationErrorData();
	}

	public ValidationErrorData validate(AssignRoleRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getRole())) {
			errData.addError(new ValidationError("role", "role is empty", "invalid_input"));
		}

		if (ValidationUtils.isNullOrEmpty(req.getSession().getSessionId())) {
			errData.addError(new ValidationError("SessionId", "Session Id is Empty", "invalid_session"));
		}

		return errData;
	}

	public ValidationErrorData validate(ChangePasswordRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getPassword())) {
			errData.addError(new ValidationError(Constants.PASSWORD,
					ResponseUtils.getErrorMessage(ErrorConstants.INVALID_PASSWORD), Constants.INVALID_PASSWORD));
		}

		if (ValidationUtils.isNullOrEmpty(req.getNewPassword())
				|| !ValidationUtils.isValidPassword(req.getNewPassword())) {
			errData.addError(new ValidationError(Constants.PASSWORD,
					ResponseUtils.getErrorMessage(ErrorConstants.INVALID_PASSWORD), Constants.INVALID_PASSWORD));
		}
		return errData;
	}

	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (req instanceof AssignRoleRequest) {
			return assignRole((AssignRoleRequest) req);
		} else if (req instanceof ChangePasswordRequest) {
			return processChangePasswordRequest((ChangePasswordRequest) req);
		}
		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));
		return ResponseUtils.errorResponse(errData);
	}

	public APIResponse assignRole(AssignRoleRequest req) {
		Either<ErrorData, String> res = host.getUserService().assignRole(req.getSession(), req.getRole());
		if (res.isLeft()) {
			return ResponseUtils.errorResponse(res.getLeft());
		} else {
			return ResponseUtils.simpleSuccessResponse(res.getRight());
		}
	}

	private APIResponse processChangePasswordRequest(ChangePasswordRequest req) {
		Either<ErrorData, String> res = host.getPasswordService().changePassword(req.getSession().getUserId(),
				req.getPassword(), req.getNewPassword());
		if (res.isLeft()) {
			return ResponseUtils.errorResponse(res.getLeft());
		} else {
			return ResponseUtils.simpleSuccessResponse(res.getRight());
		}
	}

}
