package org.brightlife.api.service.handler;

import java.util.List;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.GuardianProfileModel;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.guardian.GuardianAppliedStudentsRequest;
import org.brightlife.api.service.model.dto.request.guardian.GuardianProfileEditRequest;
import org.brightlife.api.service.model.dto.request.guardian.GuardianProfileRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.AppliedStudents;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.GuardianAppliedStudentsResponse;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class GuardianHandler extends RequestHandler {

	public GuardianHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();

		if (req instanceof GuardianProfileRequest)
			return process((GuardianProfileRequest) req);
		if (req instanceof GuardianAppliedStudentsRequest)
			return process((GuardianAppliedStudentsRequest) req);
		if (req instanceof GuardianProfileEditRequest)
			return process((GuardianProfileEditRequest) req);
		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));

		return ResponseUtils.errorResponse(errData);
	}

	@Override
	public ValidationErrorData validate(Request req) {

		if (req instanceof GuardianProfileRequest)
			return validate((GuardianProfileRequest) req);
		if (req instanceof GuardianAppliedStudentsRequest)
			return validate((GuardianAppliedStudentsRequest) req);
		if (req instanceof GuardianProfileEditRequest)
			return validate((GuardianProfileEditRequest) req);
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(GuardianProfileRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getAddress())) {
			errData.addError(new ValidationError("Address", "Address is Empty", "invalid_Address"));
			errData.setMessage("Address is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getCity())) {
			errData.addError(new ValidationError("City", "City is Empty", "invalid_City"));
			errData.setMessage("City is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getCountry())) {
			errData.addError(new ValidationError("Country", "Country is Empty", "invalid_Country"));
			errData.setMessage("Country is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getEmail())) {
			errData.addError(new ValidationError("Email", "Email is Empty", "invalid_Email"));
			errData.setMessage("Address is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getFirstname())) {
			errData.addError(new ValidationError("Firstname", "Firstname is Empty", "invalid_Firstname"));
			errData.setMessage("Firstname is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getInfoAbtBrightLife())) {
			errData.addError(new ValidationError("InfoAbtBrightLife", "InfoAbtBrightLife is Empty", "invalid_InfoAbtBrightLife"));
			errData.setMessage("InfoAbtBrightLife is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getMobileNo())) {
			errData.addError(new ValidationError("MobileNo", "MobileNo is Empty", "invalid_MobileNo"));
			errData.setMessage("MobileNo is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getLastname())) {
			errData.addError(new ValidationError("Lastname", "Lastname is empty", "invalid_Lastname"));
			errData.setMessage("Lastname is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getMobileNo())) {
			errData.addError(new ValidationError("MobileNo", "MobileNo is Empty", "invalid_MobileNo"));
			errData.setMessage("MobileNo is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getOrganisation())) {
			errData.addError(new ValidationError("Organisation", "Organisation is Empty", "invalid_Organisation"));
			errData.setMessage("Organisation is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getPincode())) {
			errData.addError(new ValidationError("Pincode", "Pincode is Empty", "invalid_Pincode"));
			errData.setMessage("Pincode is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getState())) {
			errData.addError(new ValidationError("State", "State is Empty", "invalid_State"));
			errData.setMessage("State is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(GuardianAppliedStudentsRequest req) {
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(GuardianProfileEditRequest req) {
		return new ValidationErrorData();
	}

	private APIResponse process(GuardianProfileRequest req) {

		Either<ErrorData, String> response = host.getGuardianService().createGuardianProfile(req);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());
		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(GuardianAppliedStudentsRequest req) {

		Either<ErrorData, List<AppliedStudents>> value = host.getGuardianService().getAppliedStudents(req);
		if (value.isLeft())
			return ResponseUtils.errorResponse(value.getLeft());

		GuardianAppliedStudentsResponse response = new GuardianAppliedStudentsResponse();
		response.setResponseList(value.getRight());
		return ResponseUtils.successResponse(response);
	}

	private APIResponse process(GuardianProfileEditRequest req) {

		Either<ErrorData, GuardianProfileModel> guardianProfile = host.getGuardianService().getGuardianProfile(req.getSession().getUserId());

		if (guardianProfile.isLeft())
			return ResponseUtils.errorResponse(guardianProfile.getLeft());

		return ResponseUtils.successResponse(guardianProfile.getRight());
	}
}
