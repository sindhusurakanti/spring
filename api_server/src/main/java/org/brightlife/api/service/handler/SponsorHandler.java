package org.brightlife.api.service.handler;

import java.util.List;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.SponsorProfileModel;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.sponsor.SponsorProfileEditRequest;
import org.brightlife.api.service.model.dto.request.sponsor.SponsorProfileRequest;
import org.brightlife.api.service.model.dto.request.sponsor.SponsoredStudentRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.SponsoredStudentsList;
import org.brightlife.api.service.model.dto.response.SponsoredStudentsResponse;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class SponsorHandler extends RequestHandler {

	public SponsorHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (req instanceof SponsorProfileRequest)
			return process((SponsorProfileRequest) req);
		/*if (req instanceof SponsorStudentRequest)
			return process((SponsorStudentRequest) req);*/
		if (req instanceof SponsoredStudentRequest)
			return process((SponsoredStudentRequest) req);
		if (req instanceof SponsorProfileEditRequest)
			return process((SponsorProfileEditRequest) req);
		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));
		return ResponseUtils.errorResponse(errData);
	}

	@Override
	public ValidationErrorData validate(Request req) {
		if (req instanceof SponsorProfileRequest)
			return validate((SponsorProfileRequest) req);
		/*if (req instanceof SponsorStudentRequest)
			return validate((SponsorStudentRequest) req);*/
		if (req instanceof SponsoredStudentRequest)
			return validate((SponsoredStudentRequest) req);
		if (req instanceof SponsorProfileEditRequest)
			return validate((SponsorProfileEditRequest) req);

		return new ValidationErrorData();
	}

	private ValidationErrorData validate(SponsorProfileRequest req) {
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

	/*private ValidationErrorData validate(SponsorStudentRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getStudentId())) {

			errData.addError(new ValidationError("StudentId", "StudentId is Empty", "invalid_StudentId"));
			errData.setMessage("StudentId is Empty");
			return errData;
		}
		return new ValidationErrorData();
	}*/

	private ValidationErrorData validate(SponsoredStudentRequest req) {
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(SponsorProfileEditRequest req) {
		return new ValidationErrorData();
	}

	private APIResponse process(SponsorProfileRequest req) {

		Either<ErrorData, String> response = host.getSponsorService().createSponsorProfile(req);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	/*private APIResponse process(SponsorStudentRequest req) {

		Either<ErrorData, String> response = host.getSponsorService().updateSponsorwithStudentId(req.getSession().getUserId(), req.getStudentId());
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}*/

	private APIResponse process(SponsoredStudentRequest req) {

		Either<ErrorData, List<SponsoredStudentsList>> value = host.getSponsorService().getSponsoredStudents(req.getSession().getUserId());
		if (value.isLeft())
			return ResponseUtils.errorResponse(value.getLeft());

		SponsoredStudentsResponse response = new SponsoredStudentsResponse();
		response.setResponseList(value.getRight());

		return ResponseUtils.successResponse(response);
	}

	private APIResponse process(SponsorProfileEditRequest req) {
		Either<ErrorData, SponsorProfileModel> sponsorProfile = host.getSponsorService().getSponsorProfile(req.getSession().getUserId());
		if (sponsorProfile.isLeft())
			return ResponseUtils.errorResponse(sponsorProfile.getLeft());
		return ResponseUtils.successResponse(sponsorProfile.getRight());
	}
}
