package org.brightlife.api.service.handler;

import java.util.List;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.request.master.SchoolDetailsRequest;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class MasterDataHandler extends RequestHandler {

	public MasterDataHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public APIResponse process(Request req) {
		if (req instanceof SchoolDetailsRequest) {
			return getSchoolsList((SchoolDetailsRequest) req);
		}
		return ResponseUtils.errorResponse(ErrorConstants.UNKNOWN_ERROR);
	}

	private APIResponse getSchoolsList(SchoolDetailsRequest req) {
		Either<ErrorData, List<String>> response = null;
		response = host.getMasterService().getSchoolsList(req.getName());
		if (response.isLeft()) {
			return ResponseUtils.errorResponse(ErrorConstants.UNABLE_TO_FETCH_SCHOOLS);
		}
		return ResponseUtils.successResponse(response.getRight());
	}

	@Override
	public ValidationErrorData validate(Request req) {
		if (req instanceof SchoolDetailsRequest) {
			return validate((SchoolDetailsRequest) req);
		}
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(SchoolDetailsRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getName())) {
			errData.addError(new ValidationError("name", "name is invalid", "invalid_name"));
		}
		return errData;
	}
}
