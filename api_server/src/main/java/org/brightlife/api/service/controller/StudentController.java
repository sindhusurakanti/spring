package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.studentapplication.EducationDetailsEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.EducationDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FileUploadRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FilterRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FundingDetailsEditRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FundingDetailsRequest;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("service/api/v1")
public class StudentController extends BaseController {

	@Autowired
	private RequestProcessor requestProcessor;

	@RequestMapping(value = "profile_pic", method = RequestMethod.POST)
	public APIResponse uploadFile(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		System.out.println("controller");
		FileUploadRequest req = new FileUploadRequest(file);
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("my_profile")
	public APIResponse myProfile(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			
			@RequestBody(required = false) StudentProfileRequest req) {

		
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("my_profile/edit")
	public APIResponse editMyProfile(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		StudentProfileEditRequest req = new StudentProfileEditRequest();
		return requestProcessor.handleRequest(sessionId, req);
	}
	
	/*
	 * @RequestMapping("get/applications") public APIResponse
	 * getApplications(@RequestHeader(value = "Session-Id", required = true)
	 * String sessionId) { GetApplicationsRequest req = new
	 * GetApplicationsRequest(); return
	 * requestProcessor.handleRequest(sessionId, req); }
	 * 
	 * @RequestMapping("get/application/id") public APIResponse
	 * getApplicationById(@RequestHeader(value = "Session-Id", required = true)
	 * String sessionId,
	 * 
	 * @RequestParam("application_id") String appId) { GetApplicationByIdRequest
	 * req = new GetApplicationByIdRequest(appId); return
	 * requestProcessor.handleRequest(sessionId, req); }
	 */

	@RequestMapping("student/parent")
	public APIResponse parentDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId, @RequestBody StudentParentRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("student/parent/edit")
	public APIResponse editParentDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId ) {

		StudentParentEditRequest req = new StudentParentEditRequest(); 
		return requestProcessor.handleRequest(sessionId, req);
	}
	@RequestMapping("studentguardian")
	public APIResponse guardianDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId, @RequestBody StudentGuardianRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}
	@RequestMapping("studentguardian/edit")
	public APIResponse editGuardianDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		 StudentGuardianEditRequest req = new StudentGuardianEditRequest(); 
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("student/orphanage")
	public APIResponse orphanageDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestBody StudentOrphanageRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("student/orphanage/edit")
	public APIResponse editOrphanageDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		StudentOrphanageEditRequest req = new StudentOrphanageEditRequest();
		return requestProcessor.handleRequest(sessionId, req);
	}
	
	@RequestMapping("documents")
	public APIResponse uploadDocuments(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestParam(value = "photoidProof", required = true) MultipartFile photoidProof,
			@RequestParam(value = "incomeProof", required = true) MultipartFile incomeProof,
			@RequestParam(value = "addressProof", required = true) MultipartFile addressProof,
			@RequestParam(value = "others1", required = false) MultipartFile others1,
			@RequestParam(value = "others2", required = false) MultipartFile others2,
			@RequestParam(value = "others3", required = false) MultipartFile others3) {

		StudentDocumentsRequest req = new StudentDocumentsRequest(photoidProof, incomeProof, addressProof, others1, others2, others3);
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping(value = "educationdetails")
	public APIResponse educationDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestParam(value = "achievementsFile", required = false) MultipartFile achievementsFile,
			@RequestParam(value = "schoolName", required = false) String schoolName,
			@RequestParam(value = "performance", required = false) String performance,
			@RequestParam(value = "studentClass", required = false) String studentClass,
			@RequestParam(value = "schoolAddress", required = false) String schoolAddress,
			@RequestParam(value = "pincode", required = false) String pincode,
			@RequestParam(value = "aspirations", required = false) String aspirations,
			@RequestParam(value = "achievements", required = false) String achievements,
			@RequestParam(value = "hobbies", required = false) String hobbies,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "indicator", required = true) String indicator) {

		Long applicantId = 0L;
		if(id != null) {
			applicantId = Long.valueOf(id);
		}
		EducationDetailsRequest req = new EducationDetailsRequest(schoolName, performance, studentClass, schoolAddress, pincode, aspirations,
				achievements, achievementsFile, hobbies, applicantId, indicator);

		return requestProcessor.handleRequest(sessionId, req);
	}
	
	@RequestMapping(value = "educationdetails/edit")
	public APIResponse editEducationDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		EducationDetailsEditRequest req = new EducationDetailsEditRequest();

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("fundingdetails")
	public APIResponse fundingDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId, @RequestBody FundingDetailsRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("fundingdetails/edit")
	public APIResponse editFundingDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		FundingDetailsEditRequest req = new FundingDetailsEditRequest();

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("filter")
	public APIResponse filterDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestParam(value = "name", required = false) String name, @RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "livesWithOrIn", required = false) String livesWithOrIn,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "birthMonth", required = false) String birthMonth, @RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "ageRange", required = false) String ageRange, @RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "income", required = false) String income, @RequestParam(value = "orphan", required = false) String orphan) {
		FilterRequest req = new FilterRequest(name, city, livesWithOrIn, country, birthMonth, gender, ageRange, state, income, orphan);
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping("filter/getstudent")
	public APIResponse getStudentSummary(@RequestParam(value = "applicationId", required = true) String applicationId,
			@RequestParam(value = "studentId", required = true) String studentId) {

		StudentSummaryRequest req = new StudentSummaryRequest(applicationId, studentId);
		return requestProcessor.handleRequest(req);
	}

	@RequestMapping(value = "bankdetails")
	public APIResponse studentBankDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestBody StudentBankDetailsRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping(value = "bankdetails/edit")
	public APIResponse editStudentBankDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		StudentBankDetailsEditRequest req = new StudentBankDetailsEditRequest();
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping(value = "incomedetails")
	public APIResponse incomeDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestBody StudentIncomeDetailsRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping(value = "incomedetails/edit")
	public APIResponse editIncomeDetails(@RequestHeader(value = "Session-Id", required = true) String sessionId) {

		StudentIncomeDetailsEditRequest req = new StudentIncomeDetailsEditRequest();
		return requestProcessor.handleRequest(sessionId, req);
	}

	@RequestMapping(value = "student/home")
	public APIResponse studentHome(@RequestHeader(value = "Session-Id", required = true) String sessionId, @RequestBody StudentHomePageRequest req) {

		return requestProcessor.handleRequest(sessionId, req);
	}
}
