package org.brightlife.api.service.handler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentBankDetails;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentIncomeDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.studentapplication.EducationDetailsEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.EducationDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FileUploadRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FilterRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FundingDetailsEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FundingDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.GetApplicationByIdRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.GetApplicationsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentBankDetailsEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentBankDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentDocumentsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentGuardianEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentGuardianRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentHomePageRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentIncomeDetailsEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentIncomeDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentOrphanageEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentOrphanageRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentParentEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentParentRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentProfileEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentProfileRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentSummaryRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ApplicationResponseList;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.FileUploadResponse;
import org.brightlife.api.service.model.dto.response.FilteredResponseList;
import org.brightlife.api.service.model.dto.response.ResponseData;
import org.brightlife.api.service.model.dto.response.StudentApplicationResponse;
import org.brightlife.api.service.model.dto.response.StudentDetailsResponse;
import org.brightlife.api.service.model.dto.response.StudentFilteredResponse;
import org.brightlife.api.service.model.dto.response.StudentSummaryResponse;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.StringUtils;
import org.brightlife.api.service.utils.ValidationUtils;

import com.spencerwi.either.Either;

public class StudentApplicationHandler extends RequestHandler {

	public StudentApplicationHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public ValidationErrorData validate(Request req) {

		if (req instanceof FileUploadRequest)
			return validate((FileUploadRequest) req);
		if (req instanceof StudentProfileRequest)
			return validate((StudentProfileRequest) req);
		if (req instanceof StudentParentRequest)
			return validate((StudentParentRequest) req);
		if (req instanceof StudentGuardianRequest)
			return validate((StudentGuardianRequest) req);
		if (req instanceof StudentOrphanageRequest)
			return validate((StudentOrphanageRequest) req);
		if (req instanceof FundingDetailsRequest)
			return validate((FundingDetailsRequest) req);
		if (req instanceof EducationDetailsRequest)
			return validate((EducationDetailsRequest) req);
		if (req instanceof StudentDocumentsRequest)
			return validate((StudentDocumentsRequest) req);
		if (req instanceof StudentBankDetailsRequest)
			return validate((StudentBankDetailsRequest) req);
		if (req instanceof StudentIncomeDetailsRequest)
			return validate((StudentIncomeDetailsRequest) req);
		if (req instanceof StudentIncomeDetailsEditRequest)
			return validate((StudentIncomeDetailsEditRequest) req);
		if (req instanceof StudentHomePageRequest)
			return validate((StudentHomePageRequest) req);

		return new ValidationErrorData();
	}

	private ValidationErrorData validate(FileUploadRequest req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (req.getFile() == null) {
			errData.addError(new ValidationError("File", "File is Empty", "invalid_file"));
			errData.setMessage("File is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentProfileRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getFullName())) {
			errData.addError(new ValidationError("Name", "Name is Empty", "invalid_name"));
			errData.setMessage("Name is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getCity())) {
			errData.addError(new ValidationError("City", "City is Empty", "invalid_city"));
			errData.setMessage("City is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getDoorNo())) {
			errData.addError(new ValidationError("Door no", "Door no is Empty", "invalid_door_no"));
			errData.setMessage("Door no is Empty");
		}

		if (req.getDateOfBirth() == null) {
			errData.addError(new ValidationError("Birth Date", "Birth Date is Empty", "invalid_birth_date"));
			errData.setMessage("Birth Date is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSession().getSessionId())) {
			errData.addError(new ValidationError("SessionId", "Session Id is Empty", "invalid_session"));
			errData.setMessage("Session Id is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentParentRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getLivesWithOrIn())) {
			errData.addError(new ValidationError("Lives with or in", "Lives with or in is Empty", "invalid_name"));
			errData.setMessage("Lives with or in is Empty");
		}

		if (ValidationUtils.isNullOrEmpty(req.getAnnualIncome())) {
			errData.addError(new ValidationError("Annual income", "Annual income is Empty", "invalid_door_no"));
			errData.setMessage("Annual income is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getFamilyMembers())) {
			errData.addError(new ValidationError("Family members", "Family members is Empty", "invalid_postal_code"));
			errData.setMessage("Family members is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSession().getSessionId())) {
			errData.addError(new ValidationError("SessionId", "Session Id is Empty", "invalid_session"));
			errData.setMessage("Session Id is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentGuardianRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getLivesWithOrIn())) {
			errData.addError(new ValidationError("Lives with or in", "Lives with or in is Empty", "invalid_name"));
			errData.setMessage("Lives with or in is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getName())) {
			errData.addError(new ValidationError("Name", "Name is Empty", "invalid_city"));
			errData.setMessage("Name is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getRelationWithStudent())) {
			errData.addError(new ValidationError("Relation with Student", "Relation with Student is Empty", "invalid_door_no"));
			errData.setMessage("Relation with Student is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getAnnualIncome())) {
			errData.addError(new ValidationError("Annual income", "Annual income is Empty", "invalid_postal_code"));
			errData.setMessage("Annual income is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSession().getSessionId())) {
			errData.addError(new ValidationError("SessionId", "Session Id is Empty", "invalid_session"));
			errData.setMessage("Session Id is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentOrphanageRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getLivesWithOrIn())) {
			errData.addError(new ValidationError("Lives with or in", "Lives with or in is Empty", "invalid_name"));
			errData.setMessage("Lives with or in is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getName())) {
			errData.addError(new ValidationError("Name", "Name is Empty", "invalid_city"));
			errData.setMessage("Name is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getContact())) {
			errData.addError(new ValidationError("Contact", "Contact is Empty", "invalid_door_no"));
			errData.setMessage("Contact is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getAddress())) {
			errData.addError(new ValidationError("Address", "Address is Empty", "invalid_postal_code"));
			errData.setMessage("Address is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getPostalCode())) {
			errData.addError(new ValidationError("Postal Code", "Postal Code is Empty", "invalid_postal_code"));
			errData.setMessage("Postal Code is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSession().getSessionId())) {
			errData.addError(new ValidationError("SessionId", "Session Id is Empty", "invalid_session"));
			errData.setMessage("Session Id is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(FundingDetailsRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getLivingExpenses())) {
			errData.addError(new ValidationError("LivingExpenses", "LivingExpenses is Empty", "invalid_LivingExpenses"));
			errData.setMessage("LivingExpenses is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getPhysicalHealth())) {
			errData.addError(new ValidationError("PhysicalHealth", "PhysicalHealth is Empty", "invalid_PhysicalHealth"));
			errData.setMessage("PhysicalHealth is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSchoolNeeds())) {
			errData.addError(new ValidationError("SchoolNeeds", "SchoolNeeds is Empty", "invalid_SchoolNeeds"));
			errData.setMessage("SchoolNeeds is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getTotalFunding())) {
			errData.addError(new ValidationError("TotalFunding", "TotalFunding is Empty", "invalid_TotalFunding"));
			errData.setMessage("TotalFunding is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(EducationDetailsRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getSchoolName())) {
			errData.addError(new ValidationError("schoolName", "schoolName is Empty", "invalid_schoolName"));
			errData.setMessage("schoolName is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getStudentClass())) {
			errData.addError(new ValidationError("studentClass", "studentClass is Empty", "invalid_studentClass"));
			errData.setMessage("studentClass is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSchoolAddress())) {
			errData.addError(new ValidationError("schoolAddress", "schoolAddress is Empty", "invalid_schoolAddress"));
			errData.setMessage("schoolAddress is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getPincode())) {
			errData.addError(new ValidationError("Pincode", "Pincode is Empty", "invalid_Pincode"));
			errData.setMessage("Pincode is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentDocumentsRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getAddressProof())) {
			errData.addError(new ValidationError("AddressProof", "AddressProof is Empty", "invalid_AddressProof"));
			errData.setMessage("AddressProof is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIncomeProof())) {
			errData.addError(new ValidationError("IncomeProof", "IncomeProof is Empty", "invalid_IncomeProof"));
			errData.setMessage("IncomeProof is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getPhotoidProof())) {
			errData.addError(new ValidationError("PhotoidProof", "PhotoidProof is Empty", "invalid_PhotoidProof"));
			errData.setMessage("PhotoidProof is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentBankDetailsRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getAccountNo())) {
			errData.addError(new ValidationError("AccountNo", "AccountNo is Empty", "invalid_account no"));
			errData.setMessage("AccountNo is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getBankCode())) {
			errData.addError(new ValidationError("BankCode", "BankCode is Empty", "invalid_bank code"));
			errData.setMessage("BankCode is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getBranch())) {
			errData.addError(new ValidationError("Branch name", "Branch name is Empty", "invalid_Branch names"));
			errData.setMessage("Branch name is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getBranchAddress())) {
			errData.addError(new ValidationError("BranchAddress", "BranchAddress is Empty", "invalid BranchAddress"));
			errData.setMessage("BranchAddress is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getCountry())) {
			errData.addError(new ValidationError("Country", "Country is Empty", "invalid_country_name"));
			errData.setMessage("Country is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getHolderName())) {
			errData.addError(new ValidationError("HolderName", "HolderName is Empty", "invalid_holder_name"));
			errData.setMessage("HolderName is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIfscCode())) {
			errData.addError(new ValidationError("IfscCode", "IfscCode is Empty", "invalid_ifsc_code"));
			errData.setMessage("IfscCode is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getPincode())) {
			errData.addError(new ValidationError("IfscCode", "IfscCode is Empty", "invalid_ifsc_code"));
			errData.setMessage("IfscCode is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getState())) {
			errData.addError(new ValidationError("State", "State is Empty", "invalid_state"));
			errData.setMessage("State is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getSession().getSessionId())) {
			errData.addError(new ValidationError("SessionId", "Session Id is Empty", "invalid_session"));
			errData.setMessage("Session Id is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentIncomeDetailsRequest req) {

		ValidationErrorData errData = new ValidationErrorData();
		if (ValidationUtils.isNullOrEmpty(req.getEarningPerson())) {
			errData.addError(new ValidationError("EarningPerson", "EarningPerson is Empty", "invalid_name"));
			errData.setMessage("EarningPerson is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getRelationWithStudent())) {
			errData.addError(new ValidationError("Relation", "Relation is Empty", "invalid_relation"));
			errData.setMessage("Relation is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getAnnualIncome())) {
			errData.addError(new ValidationError("AnnualIncome", "AnnualIncome is Empty", "invalid_income"));
			errData.setMessage("AnnualIncome is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getCurrency())) {
			errData.addError(new ValidationError("Currency", "Currency is Empty", "invalid_currency"));
			errData.setMessage("Currency is Empty");
		}
		if (ValidationUtils.isNullOrEmpty(req.getIndicator())) {
			errData.addError(new ValidationError("Indicator", "Indicator is Empty", "Indicator invalid"));
			errData.setMessage("Indicator is Empty");
		}
		return errData;
	}

	private ValidationErrorData validate(StudentHomePageRequest req) {
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(StudentIncomeDetailsEditRequest req) {
		return new ValidationErrorData();
	}

	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (req instanceof FileUploadRequest)
			return process((FileUploadRequest) req);
		if (req instanceof StudentProfileRequest)
			return process((StudentProfileRequest) req);
		if (req instanceof StudentProfileEditRequest) {
			return process((StudentProfileEditRequest) req);
		}
		if (req instanceof StudentDocumentsRequest)
			return process((StudentDocumentsRequest) req);
		if (req instanceof StudentGuardianRequest)
			return process((StudentGuardianRequest) req);
		if (req instanceof StudentGuardianEditRequest) {
			return process((StudentGuardianEditRequest) req);
		}
		if (req instanceof StudentOrphanageRequest)
			return process((StudentOrphanageRequest) req);
		if (req instanceof StudentOrphanageEditRequest) {
			return process((StudentOrphanageEditRequest) req);
		}
		if (req instanceof StudentParentRequest)
			return process((StudentParentRequest) req);
		if (req instanceof StudentParentEditRequest) {
			return process((StudentParentEditRequest) req);
		}
		if (req instanceof GetApplicationsRequest)
			return process((GetApplicationsRequest) req);
		if (req instanceof FundingDetailsRequest)
			return process((FundingDetailsRequest) req);
		if (req instanceof FundingDetailsEditRequest) {
			return process((FundingDetailsEditRequest) req);
		}
		if (req instanceof EducationDetailsRequest)
			return process((EducationDetailsRequest) req);
		if (req instanceof EducationDetailsEditRequest) {
			return process((EducationDetailsEditRequest) req);
		}
		if (req instanceof GetApplicationByIdRequest)
			return process((GetApplicationByIdRequest) req);
		if (req instanceof FilterRequest) {
			return process((FilterRequest) req);
		}
		if (req instanceof StudentSummaryRequest) {
			return process((StudentSummaryRequest) req);
		}
		if (req instanceof StudentBankDetailsRequest) {
			return process((StudentBankDetailsRequest) req);
		}
		if (req instanceof StudentBankDetailsEditRequest) {
			return process((StudentBankDetailsEditRequest) req);
		}
		if (req instanceof StudentIncomeDetailsRequest) {
			return process((StudentIncomeDetailsRequest) req);
		}
		if (req instanceof StudentIncomeDetailsEditRequest) {
			return process((StudentIncomeDetailsEditRequest) req);
		}
		if (req instanceof StudentHomePageRequest) {
			return process((StudentHomePageRequest) req);
		}

		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));
		return ResponseUtils.errorResponse(errData);
	}

	private APIResponse process(FilterRequest req) {

		Either<ErrorData, List<StudentFilteredResponse>> res = host.getStudentService().getFilteredData(req.getName(), req.getCity(),
				req.getLivesWithOrIn(), req.getCountry(), req.getAgeRange(), req.getBirthMonth(), req.getGender(), req.getIncome(), req.getState(),
				req.getOrphan());
		if (res.isLeft())
			return ResponseUtils.errorResponse(res.getLeft());

		FilteredResponseList response = new FilteredResponseList();
		response.setResponseList(res.getRight());

		return ResponseUtils.successResponse(response);
	}

	private APIResponse process(GetApplicationByIdRequest req) {
		Either<ErrorData, StudentApplicationResponse> res = host.getStudentService().getApplicationById(req.getId());
		if (res.isLeft())
			return ResponseUtils.errorResponse(res.getLeft());
		return ResponseUtils.successResponse(res.getRight());

	}

	private APIResponse process(FileUploadRequest req) {
		Either<ErrorData, String> res = host.getS3Service().uploadFile(req.getFile());
		if (res.isRight()) {
			ResponseData data = new FileUploadResponse(Constants.FILE_UPLOAD_SUCCESS, res.getRight());
			return ResponseUtils.successResponse(data);
		} else {
			return ResponseUtils.errorResponse(res.getLeft());
		}
	}

	private APIResponse process(StudentParentRequest req) {

		Either<ErrorData, String> response = host.getStudentService().insertStudentParentDetails(req);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(StudentParentEditRequest req) {

		Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
		if (value.isLeft())
			return ResponseUtils.errorResponse(value.getLeft());

		Either<ErrorData, StudentParentDetails> response = host.getStudentService().getStudentParent(Long.valueOf(value.getRight()));
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}

	private APIResponse process(StudentGuardianRequest req) {

		Either<ErrorData, String> response = host.getStudentService().insertStudentGuardianDetails(req);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(StudentGuardianEditRequest req) {

		Long applicationId;
		if (req.getStudentApplicationId() != null) {
			applicationId = req.getStudentApplicationId();
		} else {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());
			applicationId = Long.valueOf(value.getRight());
		}
		Either<ErrorData, StudentGuardianDetails> response = host.getStudentService().getStudentGuardian(applicationId);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}

	private APIResponse process(StudentProfileRequest req) {

		Either<ErrorData, String> response = host.getStudentService().insertMyProfile(req);

		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(StudentProfileEditRequest req) {

		Long studentProfileId;

		if (req.getStudentProfileId() != null) {
			studentProfileId = req.getStudentProfileId();
		} else {
			studentProfileId = host.getStudentService().getStudentProfileId(Long.valueOf(req.getSession().getUserId()));
		}
		if (studentProfileId != 0L) {
			Either<ErrorData, StudentProfile> studentProfile = host.getStudentService().getStudentProfile(studentProfileId);
			if (studentProfile.isRight())
				return ResponseUtils.successResponse(studentProfile.getRight());
		}

		return ResponseUtils.errorResponse(ErrorConstants.NO_ELEMENTS_FOUND);
	}

	private APIResponse process(StudentOrphanageRequest req) {

		Either<ErrorData, String> response = host.getStudentService().insertStudentOrphanageDetails(req);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(StudentOrphanageEditRequest req) {

		Long applicationId;
		if (req.getStudentApplicationId() != null) {
			applicationId = req.getStudentApplicationId();
		} else {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());

			applicationId = Long.valueOf(value.getRight());
		}
		Either<ErrorData, StudentOrphanageDetails> response = host.getStudentService().getStudentOrphanage(applicationId);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());

	}

	private APIResponse process(StudentDocumentsRequest req) {

		try {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());

			Map<String, File> filesList = StringUtils.getConvertedFiles(req, value.getRight());
			Either<ErrorData, String> response = host.getStudentService().uploadDocuments(filesList, value.getRight());

			if (response.isLeft())
				return ResponseUtils.errorResponse(response.getLeft());
			return ResponseUtils.simpleSuccessResponse(response.getRight());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseUtils.errorResponse(ErrorConstants.UNKNOWN_ERROR);
		}
	}

	private APIResponse process(GetApplicationsRequest req) {

		Either<ErrorData, List<StudentApplication>> response = host.getStudentService().getApplications(req.getSession().getUserId());
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		ApplicationResponseList res = new ApplicationResponseList();
		res.setDataList(response.getRight());
		return ResponseUtils.successResponse(res);
	}

	private APIResponse process(EducationDetailsRequest req) {

		try {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());

			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());

			Either<ErrorData, String> response = host.getStudentService().insertEducationDetails(req, value.getRight());

			if (response.isLeft())
				return ResponseUtils.errorResponse(response.getLeft());

			return ResponseUtils.simpleSuccessResponse(response.getRight());
		} catch (Exception e) {
			return ResponseUtils.errorResponse(ErrorConstants.UNKNOWN_ERROR);
		}

	}

	private APIResponse process(EducationDetailsEditRequest req) {

		Long applicationId;

		if (req.getStudentApplicationId() != null) {
			applicationId = req.getStudentApplicationId();
		} else {

			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());

			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());
			applicationId = Long.valueOf(value.getRight());
		}
		Either<ErrorData, ApplicantDetails> response = host.getStudentService().getApplicantDetails(applicationId);

		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}

	private APIResponse process(FundingDetailsRequest req) {

		Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
		if (value.isLeft())
			return ResponseUtils.errorResponse(value.getLeft());

		Either<ErrorData, String> response = host.getStudentService().insertFundingDetails(req, value.getRight());
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(FundingDetailsEditRequest req) {

		Long applicationId;

		if (req.getStudentApplicationId() != null) {
			applicationId = req.getStudentApplicationId();
		} else {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());

			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());

			applicationId = req.getStudentApplicationId();
		}
		Either<ErrorData, ApplicantFundingDetails> response = host.getStudentService().getApplicantFundingDetails(applicationId);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}

	private APIResponse process(StudentSummaryRequest req) {

		Either<ErrorData, List<StudentSummaryResponse>> result = host.getStudentService().getStudentSummary(Long.valueOf(req.getApplicationId()),
				Long.valueOf(req.getStudentId()));
		if (result.isLeft())
			return ResponseUtils.errorResponse(ErrorConstants.UNABLE_TO_GET_STUDENT_DETAILS);

		else {
			StudentDetailsResponse response = new StudentDetailsResponse();
			response.setResponseList(result.getRight());
			return ResponseUtils.successResponse(response);
		}
	}

	private APIResponse process(StudentBankDetailsRequest req) {

		Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
		if (value.isLeft())
			return ResponseUtils.errorResponse(value.getLeft());

		Either<ErrorData, String> response = host.getStudentService().insertBankDetails(req, value.getRight());
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(StudentBankDetailsEditRequest req) {

		Long applicationId;
		if (req.getStudentApplicationId() != null) {
			applicationId = req.getStudentApplicationId();
		} else {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());

			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());

			applicationId = req.getStudentApplicationId();
		}
		Either<ErrorData, StudentBankDetails> response = host.getStudentService().getBankDetails(applicationId.toString());
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}

	private APIResponse process(StudentIncomeDetailsRequest req) {

		Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());
		if (value.isLeft())
			return ResponseUtils.errorResponse(value.getLeft());

		Either<ErrorData, String> response = host.getStudentService().insertIncomeDetails(req, value.getRight());

		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	private APIResponse process(StudentIncomeDetailsEditRequest req) {

		Long applicationId;

		if (req.getStudentApplicationId() != null) {
			applicationId = req.getStudentApplicationId();

		} else {
			Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());

			if (value.isLeft())
				return ResponseUtils.errorResponse(value.getLeft());
			applicationId = req.getStudentApplicationId();
		}
		Either<ErrorData, StudentIncomeDetails> response = host.getStudentService().getApplicantIncomeDetails(applicationId);
		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}

	private APIResponse process(StudentHomePageRequest req) {

		long studentProfileId = host.getStudentService().getStudentProfileId(req.getSession().getUserId());
		if (studentProfileId == 0L)
			return ResponseUtils.errorResponse(ErrorConstants.NO_ELEMENTS_FOUND);

		Either<ErrorData, String> value = host.getStudentService().getApplicationDetails(req.getSession().getUserId());

		long applicationId = Long.valueOf(value.getRight());
		if (applicationId == 0L)
			return ResponseUtils.errorResponse(ErrorConstants.NO_APPLICATION_FOUND);

		Either<ErrorData, List<StudentSummaryResponse>> result = host.getStudentService().getStudentSummary(applicationId, studentProfileId);
		if (result.isLeft())
			return ResponseUtils.errorResponse(ErrorConstants.UNABLE_TO_GET_STUDENT_DETAILS);
		else {
			StudentDetailsResponse response = new StudentDetailsResponse();
			response.setResponseList(result.getRight());
			return ResponseUtils.successResponse(response);
		}
	}
}
