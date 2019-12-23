package org.brightlife.api.service.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.ApplicantDetailsEntity;
import org.brightlife.api.service.entity.ApplicantDocumentsEntity;
import org.brightlife.api.service.entity.ApplicantFundingEntity;
import org.brightlife.api.service.entity.SponsorEntity;
import org.brightlife.api.service.entity.StudentApplicationEntity;
import org.brightlife.api.service.entity.StudentBankDetailsEntity;
import org.brightlife.api.service.entity.StudentGuardianEntity;
import org.brightlife.api.service.entity.StudentIncomeDetailsEntity;
import org.brightlife.api.service.entity.StudentOrphanageEntity;
import org.brightlife.api.service.entity.StudentParentEntity;
import org.brightlife.api.service.entity.StudentProfileEntity;
import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantDocuments;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.SponsorProfileModel;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentBankDetails;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentIncomeDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;
import org.brightlife.api.service.model.dto.request.studentapplication.EducationDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.FundingDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentBankDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentGuardianRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentIncomeDetailsRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentOrphanageRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentParentRequest;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentProfileRequest;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.StudentApplicationResponse;
import org.brightlife.api.service.model.dto.response.StudentFilteredResponse;
import org.brightlife.api.service.model.dto.response.StudentSummaryResponse;
import org.brightlife.api.service.repository.ApplicantFundingRepo;
import org.brightlife.api.service.repository.ApplicationDetailsRepo;
import org.brightlife.api.service.repository.SponsorRepo;
import org.brightlife.api.service.repository.StudentApplicationRepo;
import org.brightlife.api.service.repository.StudentBankDetailsRepo;
import org.brightlife.api.service.repository.StudentDocumentRepo;
import org.brightlife.api.service.repository.StudentGaurdianRepo;
import org.brightlife.api.service.repository.StudentIncomeDetailsRepo;
import org.brightlife.api.service.repository.StudentOrphanageRepo;
import org.brightlife.api.service.repository.StudentParentRepo;
import org.brightlife.api.service.repository.StudentProfileRepo;
import org.brightlife.api.service.utils.DateUtil;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.spencerwi.either.Either;

@EnableTransactionManagement
@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentProfileRepo studentRepo;

	@Autowired
	private StudentDocumentRepo studentDocRepo;

	@Autowired
	private StudentApplicationRepo appRepo;

	@Autowired
	private StudentParentRepo parentRepo;

	@Autowired
	private StudentGaurdianRepo studentGuardianRepo;

	@Autowired
	private StudentOrphanageRepo orphanRepo;

	@Autowired
	private ApplicationDetailsRepo appDetailsRepo;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ApplicantFundingRepo applicantFundingRepo;

	@Autowired
	private StudentBankDetailsRepo bankDetailsRepo;

	@Autowired
	private StudentIncomeDetailsRepo incomeDetailsRepo;

	@Autowired
	private SponsorRepo sponsorRepo;

	public Either<ErrorData, String> insertMyProfile(StudentProfileRequest req) {

		StudentProfileEntity stuEntity = new StudentProfileEntity();

		try {
			LocalDateTime createdOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

			stuEntity.setUserId(req.getSession().getUserId());
			stuEntity.setFullName(req.getFullName());
			stuEntity.setDateOfBirth(req.getDateOfBirth());
			stuEntity.setGender(req.getGender());
			stuEntity.setLanguageSpoken(req.getLanguageSpoken());
			stuEntity.setDoorNo(req.getDoorNo());
			stuEntity.setStreet(req.getStreet());
			stuEntity.setCity(req.getCity());
			stuEntity.setDistrict(req.getDistrict());
			stuEntity.setState(req.getState());
			stuEntity.setCountry(req.getCountry());
			stuEntity.setIncome(req.getIncome());
			stuEntity.setPostalCode(Integer.parseInt(req.getPostalCode()));
			stuEntity.setActive(true);
			stuEntity.setCreatedBy("testing");
			stuEntity.setCreatedDate(createdOn);
			stuEntity.setLastUpdatedBy("testing");
			stuEntity.setLastUpdatedDate(createdOn);

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				studentRepo.save(stuEntity);

				stuEntity = studentRepo.findByDetails(req.getSession().getUserId(), req.getFullName(), createdOn);

				System.out.println(stuEntity.getFullName());

				if (!StringUtils.isNullOrEmpty(req.getFileName())) {
					Either<ErrorData, String> res = s3Service.move(req.getFileName(), stuEntity.getId(),
							"profile_pic" + StringUtils.getExtension(req.getFileName()));
					if (res.isRight()) {
						System.out.println(Constants.CLOUDFRONT_URL + Constants.MAIN_BUCKET + "/" + res.getRight());
						stuEntity.setProfilePicUrl(Constants.CLOUDFRONT_URL + Constants.MAIN_BUCKET + "/" + res.getRight());
					} else {
						System.out.println(res.getLeft().getMessage());
					}
				}
				studentRepo.save(stuEntity);

				StudentApplicationResponse appResponse = new StudentApplicationResponse();
				StudentProfile student = new StudentProfile();
				student.setId(stuEntity.getId());
				student.setFullName(stuEntity.getFullName());
				student.setProfilePicUrl(stuEntity.getProfilePicUrl());
				student.setUserId(stuEntity.getUserId());

				appResponse.setStudent(student);

				StudentApplicationEntity appEntity = new StudentApplicationEntity();
				System.out.println(stuEntity.getId());
				appEntity.setStudentId(stuEntity.getId());
				appEntity.setStatus(Constants.APPLICATION_PROGRESS);
				appEntity.setAppliedBy(req.getSession().getUserId());
				// TODO set approved by and date
				appEntity.setApprovedBy("testing");
				appEntity.setApprovedDate(LocalDateTime.now());
				appEntity.setAppliedDate(createdOn);
				appEntity.setActive(true);
				appEntity.setCreatedDate(createdOn);
				appEntity.setCreatedBy("testing");
				appEntity.setLastUpdatedBy("testing");
				appEntity.setLastUpdatedDate(createdOn);

				appRepo.save(appEntity);
				StudentApplication application = new StudentApplication();
				application.setStudentId(stuEntity.getId());
				application.setStatus(Constants.APPLICATION_PROGRESS);
				application.setAppliedBy(req.getSession().getUserId());
				application.setAppliedDate(createdOn);

				return Either.right(Constants.STUDENT_PROFILE);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {

				if (studentRepo.updateStudentRecord(stuEntity.getCity(), stuEntity.getContact(), stuEntity.getCountry(), stuEntity.getDateOfBirth(),
						stuEntity.getDistrict(), stuEntity.getDoorNo(), stuEntity.getEmail(), stuEntity.getFullName(), stuEntity.getGender(),
						stuEntity.getIncome(), stuEntity.getLanguageSpoken(), stuEntity.getLastUpdatedBy(), stuEntity.getLastUpdatedDate(),
						stuEntity.getLivesWithOrIn(), stuEntity.getPostalCode(), stuEntity.getState(), stuEntity.getStreet(), req.getId()) == 1)
					return Either.right(Constants.UPDATE_SUCCESSFULL);
				else
					return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_UPDATE));
			}
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, String> getApplicationDetails(long userID) {
		System.out.println(userID);
		long studentId = getStudentProfileId(userID);

		if (studentId == 0L)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentApplicationEntity applicationDetails = appRepo.findApplicationID(studentId);

		if (applicationDetails != null)
			return Either.right(String.valueOf(applicationDetails.getId()));
		return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
	}

	public long getStudentProfileId(long userID) {

		StudentProfileEntity student = studentRepo.findStudentId(userID);
		if (student != null) {
			return student.getId();
		}
		return 0L;
	}

	public Either<ErrorData, String> insertStudentGuardianDetails(StudentGuardianRequest req) {

		Either<ErrorData, String> applicationIdVal = getApplicationDetails(req.getSession().getUserId());

		if (applicationIdVal.isLeft())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
		long id = Long.valueOf(applicationIdVal.getRight());

		StudentApplicationEntity appEntity = appRepo.findById(id);

		if (appEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentProfileEntity stuEntity = studentRepo.findByStudentId(appEntity.getStudentId());

		if (stuEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentGuardianEntity guardianEntity = new StudentGuardianEntity();
		stuEntity.setLivesWithOrIn(req.getLivesWithOrIn());
		guardianEntity.setApplicationId(appEntity.getId());
		guardianEntity.setName(req.getName());
		guardianEntity.setRelationWithStudent(req.getRelationWithStudent());
		guardianEntity.setProfession(req.getProfession());
		guardianEntity.setAnnualIncome(req.getAnnualIncome());
		guardianEntity.setParentDetailsAvailable(req.isParentDetailsAvailable());
		guardianEntity.setFathersName(req.getFathersName());
		guardianEntity.setMothersName(req.getMothersName());
		guardianEntity.setActive(true);
		guardianEntity.setCreatedDate(LocalDateTime.now());
		guardianEntity.setCreatedBy("testing");
		guardianEntity.setLastUpdatedBy("testing");
		guardianEntity.setLastUpdatedDate(LocalDateTime.now());

		try {

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				studentRepo.save(stuEntity);
				studentGuardianRepo.save(guardianEntity);
				return Either.right(Constants.GUARDIAN_DETAILS);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (studentGuardianRepo.updateGaurdianDetails(guardianEntity.getAnnualIncome(), guardianEntity.getFathersName(),
						guardianEntity.getMothersName(), guardianEntity.getName(), guardianEntity.getProfession(),
						guardianEntity.getRelationWithStudent(), guardianEntity.getLastUpdatedBy(), guardianEntity.getLastUpdatedDate(), req.getId()) == 1)
					return Either.right(Constants.UPDATE_SUCCESSFULL);
			}
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
	}

	public Either<ErrorData, String> insertStudentOrphanageDetails(StudentOrphanageRequest req) {

		Either<ErrorData, String> applicationIdVal = getApplicationDetails(req.getSession().getUserId());

		if (applicationIdVal.isLeft())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
		long id = Long.valueOf(applicationIdVal.getRight());

		StudentApplicationEntity appEntity = appRepo.findById(id);

		if (appEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentProfileEntity stuEntity = studentRepo.findByStudentId(appEntity.getStudentId());

		if (stuEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentOrphanageEntity orphanageEntity = new StudentOrphanageEntity();
		stuEntity.setLivesWithOrIn(req.getLivesWithOrIn());
		orphanageEntity.setApplicationId(appEntity.getId());
		orphanageEntity.setName(req.getName());
		orphanageEntity.setContact(req.getContact());
		orphanageEntity.setAddress(req.getAddress());
		orphanageEntity.setPostalCode(req.getPostalCode());
		orphanageEntity.setParentDetailsAvailable(req.isParentDetailsAvailable());
		orphanageEntity.setFathersName(req.getFathersName());
		orphanageEntity.setMothersName(req.getMothersName());
		orphanageEntity.setActive(true);
		orphanageEntity.setCreatedDate(LocalDateTime.now());
		orphanageEntity.setCreatedBy("testing");
		orphanageEntity.setLastUpdatedBy("testing");
		orphanageEntity.setLastUpdatedDate(LocalDateTime.now());

		try {

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				studentRepo.save(stuEntity);
				orphanRepo.save(orphanageEntity);
				return Either.right(Constants.ORPHAN_DETAILS);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (orphanRepo.updateOrphanDetails(orphanageEntity.getAddress(), orphanageEntity.getContact(), orphanageEntity.getFathersName(),
						orphanageEntity.getMothersName(), orphanageEntity.getName(), orphanageEntity.getPostalCode(),
						orphanageEntity.getLastUpdatedBy(), orphanageEntity.getLastUpdatedDate(), req.getId()) == 1)
					return Either.right(Constants.UPDATE_SUCCESSFULL);
				else
					return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_UPDATE));
			}
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
	}

	public Either<ErrorData, String> insertStudentParentDetails(StudentParentRequest request) {

		System.out.println(request.getSession().getUserId());
		Either<ErrorData, String> applicationIdVal = getApplicationDetails(request.getSession().getUserId());

		if (applicationIdVal.isLeft())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
		long id = Long.valueOf(applicationIdVal.getRight());

		StudentApplicationEntity appEntity = appRepo.findById(id);

		if (appEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentProfileEntity stuEntity = studentRepo.findByStudentId(appEntity.getStudentId());

		if (stuEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentParentEntity parentEntity = new StudentParentEntity();
		stuEntity.setLivesWithOrIn(request.getLivesWithOrIn());
		parentEntity.setApplicationId(appEntity.getId());
		parentEntity.setStaysWith(request.getLivesWithOrIn());
		parentEntity.setFatherName(request.getFatherName());
		parentEntity.setFatherProfession(request.getFatherProfession());
		parentEntity.setMotherName(request.getMotherName());
		parentEntity.setMotherProfession(request.getMotherProfession());
		parentEntity.setAnnualIncome(request.getAnnualIncome());
		parentEntity.setExtraAllowance(request.getExtraAllowance());
		parentEntity.setFamilyMembers(request.getFamilyMembers());
		parentEntity.setMember1Age(request.getMember1Age());
		parentEntity.setMember1Name(request.getMember1Name());
		parentEntity.setMember2Age(request.getMember2Age());
		parentEntity.setMember2Name(request.getMember2Name());
		parentEntity.setMember3Age(request.getMember3Age());
		parentEntity.setMember3Name(request.getMember3Name());
		parentEntity.setActive(true);
		parentEntity.setCreatedDate(LocalDateTime.now());
		parentEntity.setCreatedBy("testing");
		parentEntity.setLastUpdatedBy("testing");
		parentEntity.setLastUpdatedDate(LocalDateTime.now());

		try {
			if (request.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				studentRepo.save(stuEntity);
				parentRepo.save(parentEntity);
			} else if (request.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (parentRepo.updateParentsDetails(parentEntity.getAnnualIncome(), parentEntity.getExtraAllowance(),
						parentEntity.getFamilyMembers(), parentEntity.getFatherName(), parentEntity.getFatherProfession(),
						parentEntity.getMember1Age(), parentEntity.getMember1Name(), parentEntity.getMember2Age(), parentEntity.getMember2Name(),
						parentEntity.getMember3Age(), parentEntity.getMember3Name(), parentEntity.getMotherName(),
						parentEntity.getMotherProfession(), parentEntity.getStaysWith(), parentEntity.getLastUpdatedBy(),
						parentEntity.getLastUpdatedDate(), request.getId()) == 1)
					return Either.right(Constants.UPDATE_SUCCESSFULL);
			}
			return Either.right(Constants.PARENTS_DETAILS);
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, String> insertEducationDetails(EducationDetailsRequest req, String applicationId) throws IOException {

		try {
			ApplicantDetailsEntity educationDetails = new ApplicantDetailsEntity();
			if (req.getAchievementsFile() != null) {

				File convertedFile = StringUtils.convertToFile(req.getAchievementsFile(), applicationId,
						StringUtils.getExtension(req.getAchievementsFile().getOriginalFilename()));

				Map<String, File> achievementsfile = new HashMap<>();
				achievementsfile.put(Constants.ACHIEVEMENTS_FILE, convertedFile);

				Either<ErrorData, String> upload = s3Service.uploadDocuments(achievementsfile, applicationId);
				if (upload.isRight()) {
					educationDetails.setAchievementsURL(StringUtils.appendString(convertedFile));
					convertedFile.delete();
				}
			}
			educationDetails.setApplicationId(Long.valueOf(applicationId));
			educationDetails.setAspirations(req.getAspirations());
			educationDetails.setAchievements(req.getAchievements());
			educationDetails.setHobbies(req.getHobbies());
			educationDetails.setSchool(req.getSchoolName());
			educationDetails.setStandard(Integer.parseInt(req.getStudentClass()));
			educationDetails.setPerformance(req.getPerformance());
			educationDetails.setCreatedBy("testing");
			educationDetails.setCreatedDate(LocalDateTime.now());
			educationDetails.setLastUpdatedBy("testing");
			educationDetails.setLastUpdatedDate(LocalDateTime.now());
			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {

				appDetailsRepo.save(educationDetails);

				return Either.right(Constants.EDUCATION_DETAILS_SAVED);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (appDetailsRepo.updateEducationDetails(educationDetails.getAchievements(), educationDetails.getAspirations(),
						educationDetails.getEmail(), educationDetails.getHobbies(), educationDetails.getSchool(), educationDetails.getMobileNumber(),
						educationDetails.getStandard(), educationDetails.getPerformance(), educationDetails.getLastUpdatedBy(),
						educationDetails.getLastUpdatedDate(), req.getId()) == 1)
					return Either.right(Constants.UPDATE_SUCCESSFULL);
				else
					return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_UPDATE));

			}

			return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_INSERT));
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, String> uploadDocuments(Map<String, File> filesList, String applicationId) {

		Either<ErrorData, String> upload = s3Service.uploadDocuments(filesList, applicationId);
		if (upload.isRight()) {
			Either<ErrorData, String> response = insertApplicationDocuments(filesList, applicationId);
			if (response.isRight()) {

				deleteCreatedFile(filesList);
				return Either.right(response.getRight());
			}
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		} else {
			return Either.left(upload.getLeft());
		}

	}

	private void deleteCreatedFile(Map<String, File> filesList) {

		filesList.entrySet().stream().forEach(e -> e.getValue().delete());

	}

	private Either<ErrorData, String> insertApplicationDocuments(Map<String, File> convertedFiles, String applicationId) {
		try {
			for (Map.Entry<String, File> entrySet : convertedFiles.entrySet()) {
				ApplicantDocumentsEntity studentDocuments = new ApplicantDocumentsEntity();
				studentDocuments.setApplicationId(Long.parseLong(applicationId));
				studentDocuments.setDocumentCode(entrySet.getKey());
				studentDocuments.setDocumentImageUrl(StringUtils.appendString(entrySet.getValue()));
				studentDocuments.setCreatedDate(LocalDateTime.now());
				// TODO set create by verified by and date
				studentDocuments.setCreatedBy("testing");
				studentDocuments.setVerifiedBy("testing");
				studentDocuments.setVerificationDate(LocalDateTime.now().toString());
				studentDocuments.setIsVerified(false);
				studentDocuments.setActive(true);
				studentDocuments.setCreatedDate(LocalDateTime.now());
				studentDocuments.setCreatedBy("testing");
				studentDocuments.setLastUpdatedBy("testing");
				studentDocuments.setLastUpdatedDate(LocalDateTime.now());
				studentDocRepo.save(studentDocuments);

			}
			return Either.right(Constants.UPLOAD_DOCUMENTS_AND_INSERT);
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, String> insertFundingDetails(FundingDetailsRequest req, String applicationId) {
		try {
			ApplicantFundingEntity fundingDetails = new ApplicantFundingEntity();
			fundingDetails.setApplicationId(Long.parseLong(applicationId));
			fundingDetails.setAmount(req.getTotalFunding());
			fundingDetails.setCurrencyCode(Constants.INR);
			fundingDetails.setExpensesAmount(req.getLivingExpenses());
			fundingDetails.setSchoolNeedsAmount(req.getSchoolNeeds());
			fundingDetails.setPhysicalHealthAmount(req.getPhysicalHealth());
			fundingDetails.setDuration(String.valueOf(req.getDuration()));
			// TODO set create and lastupdated by
			fundingDetails.setPurpose("testing");
			fundingDetails.setPurposeDescription("testing");
			fundingDetails.setCreatedBy("testing");
			fundingDetails.setCreatedDate(LocalDateTime.now());
			fundingDetails.setLastUpdatedBy("testing");
			fundingDetails.setLastUpdatedDate(LocalDateTime.now());

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				applicantFundingRepo.save(fundingDetails);
				return Either.right(Constants.FUNDING_DETAILS_INSERT);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (applicantFundingRepo.updateFundingDetails(fundingDetails.getAmount(), fundingDetails.getCurrencyCode(),
						fundingDetails.getDuration(), fundingDetails.getExpensesAmount(), fundingDetails.getPhysicalHealthAmount(),
						fundingDetails.getPurpose(), fundingDetails.getPurposeDescription(), fundingDetails.getSchoolNeedsAmount(),
						fundingDetails.getLastUpdatedBy(), fundingDetails.getLastUpdatedDate(), req.getId()) == 1)
					return Either.right(Constants.UPDATE_SUCCESSFULL);
			}
			return Either.left(ResponseUtils.createError(ErrorConstants.FUNDING_DETAILS_INSERT_FAILED));
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.FUNDING_DETAILS_INSERT_FAILED));
		}
	}

	public Either<ErrorData, List<StudentApplication>> getApplications(long studentId) {

		List<StudentApplicationEntity> appEntityList = appRepo.findAllById(studentId);

		if (appEntityList.isEmpty())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		List<StudentApplication> appList = new ArrayList<StudentApplication>();
		for (StudentApplicationEntity appEntity : appEntityList) {
			StudentApplication app = new StudentApplication();
			app.setId(appEntity.getId());
			app.setStudentId(appEntity.getStudentId());
			app.setStatus(appEntity.getStatus());
			app.setAppliedBy(appEntity.getAppliedBy());
			app.setAppliedDate(appEntity.getAppliedDate());
			app.setApprovedBy(appEntity.getApprovedBy());
			app.setApprovedDate(appEntity.getAppliedDate());
			appList.add(app);
		}
		if (appList.isEmpty())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
		return Either.right(appList);
	}

	public Either<ErrorData, StudentProfile> getStudentProfile(long studentId) {
		StudentProfileEntity profileEntity = studentRepo.findByStudentId(studentId);

		if (profileEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentProfile profile = new StudentProfile();
		profile.setId(profileEntity.getId());
		profile.setUserId(profileEntity.getUserId());
		profile.setProfilePicUrl(profileEntity.getProfilePicUrl());
		profile.setFullName(profileEntity.getFullName());
		profile.setDateOfBirth(profileEntity.getDateOfBirth());
		profile.setGender(profileEntity.getGender());
		profile.setLanguageSpoken(profileEntity.getLanguageSpoken());
		profile.setEmail(profileEntity.getEmail());
		profile.setContact(profileEntity.getContact());
		profile.setLivesWithOrIn(profileEntity.getLivesWithOrIn());
		profile.setDoorNo(profileEntity.getDoorNo());
		profile.setStreet(profileEntity.getStreet());
		profile.setCity(profileEntity.getCity());
		profile.setDistrict(profileEntity.getDistrict());
		profile.setState(profileEntity.getState());
		profile.setCountry(profileEntity.getCountry());
		profile.setPostalCode(profileEntity.getPostalCode());
		profile.setIsOrphan(profileEntity.isOrphan());
		return Either.right(profile);
	}

	public Either<ErrorData, StudentGuardianDetails> getStudentGuardian(long appId) {
		StudentGuardianEntity guardianEntity = studentGuardianRepo.findByAppId(appId);

		if (guardianEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentGuardianDetails guardian = new StudentGuardianDetails();
		guardian.setId(guardianEntity.getId());
		guardian.setApplicationId(guardianEntity.getApplicationId());
		guardian.setName(guardianEntity.getName());
		guardian.setRelationWithStudent(guardianEntity.getRelationWithStudent());
		guardian.setProfession(guardianEntity.getProfession());
		guardian.setAnnualIncome(guardianEntity.getAnnualIncome());
		guardian.setParentDetailsAvailable(guardianEntity.isParentDetailsAvailable());
		guardian.setFathersName(guardianEntity.getFathersName());
		guardian.setMothersName(guardianEntity.getMothersName());
		return Either.right(guardian);
	}

	public Either<ErrorData, StudentOrphanageDetails> getStudentOrphanage(long appId) {
		StudentOrphanageEntity orphanageEntity = orphanRepo.findByAppId(appId);

		if (orphanageEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentOrphanageDetails orphanage = new StudentOrphanageDetails();
		orphanage.setId(orphanageEntity.getId());
		orphanage.setApplicationId(orphanageEntity.getApplicationId());
		orphanage.setName(orphanageEntity.getName());
		orphanage.setContact(orphanageEntity.getContact());
		orphanage.setAddress(orphanageEntity.getAddress());
		orphanage.setPostalCode(orphanageEntity.getPostalCode());
		orphanage.setParentDetailsAvailable(orphanageEntity.isParentDetailsAvailable());
		orphanage.setFathersName(orphanageEntity.getFathersName());
		orphanage.setMothersName(orphanageEntity.getMothersName());
		return Either.right(orphanage);
	}

	public Either<ErrorData, StudentParentDetails> getStudentParent(long appId) {
		StudentParentEntity parentEntity = parentRepo.findByAppId(appId);

		if (parentEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentParentDetails parent = new StudentParentDetails();
		parent.setId(parentEntity.getId());
		parent.setApplicationId(parentEntity.getApplicationId());
		parent.setStaysWith(parentEntity.getStaysWith());
		parent.setFatherName(parentEntity.getFatherName());
		parent.setFatherProfession(parentEntity.getFatherProfession());
		parent.setMotherName(parentEntity.getMotherName());
		parent.setMotherProfession(parentEntity.getMotherProfession());
		parent.setAnnualIncome(parentEntity.getAnnualIncome());
		parent.setExtraAllowance(parentEntity.getExtraAllowance());
		parent.setFamilyMembers(parentEntity.getFamilyMembers());
		parent.setMember1Age(parentEntity.getMember1Age());
		parent.setMember1Name(parentEntity.getMember1Name());
		parent.setMember2Age(parentEntity.getMember2Age());
		parent.setMember2Name(parentEntity.getMember2Name());
		parent.setMember3Age(parentEntity.getMember3Age());
		parent.setMember3Name(parentEntity.getMember3Name());
		return Either.right(parent);
	}

	public Either<ErrorData, ApplicantDetails> getApplicantDetails(long appId) {
		ApplicantDetailsEntity applicationEntity = appDetailsRepo.findByAppId(appId);

		if (applicationEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		ApplicantDetails application = new ApplicantDetails();
		application.setId(applicationEntity.getId());
		application.setApplicationId(applicationEntity.getApplicationId());
		application.setAspirations(applicationEntity.getAspirations());
		application.setAchievements(applicationEntity.getAchievements());
		application.setAchievementsURL(applicationEntity.getAchievementsURL());
		application.setHobbies(applicationEntity.getHobbies());
		application.setSchool(applicationEntity.getSchool());
		application.setStandard(applicationEntity.getStandard());
		return Either.right(application);
	}

	public Either<ErrorData, ApplicantFundingDetails> getApplicantFundingDetails(long appId) {
		ApplicantFundingEntity fundingEntity = applicantFundingRepo.findByAppId(appId);

		if (fundingEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		ApplicantFundingDetails funding = new ApplicantFundingDetails();
		funding.setId(fundingEntity.getId());
		funding.setApplicationId(fundingEntity.getApplicationId());
		funding.setAmount(fundingEntity.getAmount());
		funding.setCurrencyCode(fundingEntity.getCurrencyCode());
		funding.setExpensesAmount(fundingEntity.getExpensesAmount());
		funding.setSchoolNeedsAmount(fundingEntity.getSchoolNeedsAmount());
		funding.setPhysicalHealthAmount(fundingEntity.getPhysicalHealthAmount());
		// TODO set create and lastupdated by
		funding.setPurpose(fundingEntity.getPurpose());
		funding.setPurposeDescription(fundingEntity.getPurposeDescription());
		return Either.right(funding);
	}

	public Either<ErrorData, StudentIncomeDetails> getApplicantIncomeDetails(long appId) {

		StudentIncomeDetailsEntity incomeEntity = incomeDetailsRepo.getIncomeDetails(appId);

		if (incomeEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		StudentIncomeDetails incomeDetails = new StudentIncomeDetails();

		incomeDetails.setAnnualIncome(incomeEntity.getAnnualIncome());
		incomeDetails.setCurrency(incomeEntity.getCurrrency());
		incomeDetails.setEarningPerson(incomeEntity.getEarningPerson());
		incomeDetails.setRelationWithStudent(incomeEntity.getRelationWithStudent());
		incomeDetails.setId(incomeEntity.getId());

		return Either.right(incomeDetails);
	}

	private List<ApplicantDocuments> getApplicantDocuments(long applicationID) {
		List<ApplicantDocumentsEntity> documentsEntity = studentDocRepo.findByAppId(applicationID);

		List<ApplicantDocuments> documentsList = null;
		if (documentsEntity.size() == 0) {
			return documentsList;
		} else {
			for (Object obj : documentsEntity) {
				ApplicantDocumentsEntity appDocumentsEntity = (ApplicantDocumentsEntity) obj;
				ApplicantDocuments documents = new ApplicantDocuments();
				documents.setDocumnetCode(appDocumentsEntity.getDocumentCode());
				documents.setDocumnetImageURL(appDocumentsEntity.getDocumentImageUrl());
				documents.setVerifiedBy(appDocumentsEntity.getVerifiedBy());
			}
		}
		return documentsList;
	}

	public Either<ErrorData, StudentApplicationResponse> getApplicationById(String appId) {
		StudentApplicationResponse res = new StudentApplicationResponse();
		long id = StringUtils.toLong(appId, 0);
		StudentApplicationEntity appEntity = appRepo.findById(id);

		if (appEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		res.setApplicationId(id);
		StudentApplication app = new StudentApplication();
		app.setId(appEntity.getId());
		app.setStudentId(appEntity.getStudentId());
		app.setStatus(appEntity.getStatus());
		app.setAppliedBy(appEntity.getAppliedBy());
		app.setAppliedDate(appEntity.getAppliedDate());
		app.setApprovedBy(appEntity.getApprovedBy());
		app.setApprovedDate(appEntity.getAppliedDate());
		res.setStudentApplication(app);

		Either<ErrorData, StudentProfile> profile = getStudentProfile(appEntity.getStudentId());
		if (profile.isLeft())
			return Either.right(res);

		res.setStudent(profile.getRight());
		String livesWith = profile.getRight().getLivesWithOrIn();
		if (livesWith == null) {
			return Either.right(res);
		} else if (livesWith.toUpperCase().equals(Constants.PARENTS)) {
			Either<ErrorData, StudentParentDetails> response = getStudentParent(id);
			res.setParentDetails(response.getRight());
		} else if (livesWith.toUpperCase().equals(Constants.ORPHANAGE)) {
			Either<ErrorData, StudentOrphanageDetails> response = getStudentOrphanage(id);
			res.setOrphanageDetails(response.getRight());
		} else if (livesWith.toUpperCase().equals(Constants.GUARDIAN)) {
			Either<ErrorData, StudentGuardianDetails> response = getStudentGuardian(id);
			res.setGuardianDetails(response.getRight());
		}

		Either<ErrorData, ApplicantDetails> education = getApplicantDetails(id);
		if (education.isLeft())
			return Either.right(res);

		res.setApplicantDetails(education.getRight());

		Either<ErrorData, ApplicantFundingDetails> funding = getApplicantFundingDetails(id);
		if (funding.isLeft())
			return Either.right(res);

		res.setFundingDetails(funding.getRight());

		List<ApplicantDocuments> documents = getApplicantDocuments(id);
		if (documents == null || documents.size() == 0)
			return Either.right(res);
		res.setDocuments(documents);

		return Either.right(res);
	}

	public Either<ErrorData, List<StudentFilteredResponse>> getFilteredData(String name, String city, String livesWithOrIn, String country,
			String ageRange, String birthMonth, String gender, String income, String state, String orphan) {

		List<StudentApplicationEntity> appList = (List<StudentApplicationEntity>) appRepo.findRecords(Constants.APPROVED);
		List<StudentFilteredResponse> responseList = new ArrayList<StudentFilteredResponse>();

		if (appList.size() != 0) {

			List<StudentProfileEntity> profileList = new ArrayList<>();

			profileList = getProfileList(ageRange, birthMonth, name, city, livesWithOrIn, country, gender, income, state, orphan);

			for (StudentProfileEntity profile : profileList) {

				StudentFilteredResponse response = new StudentFilteredResponse();

				response.setFullName(profile.getFullName());
				response.setDateOfBirth(profile.getDateOfBirth());
				response.setProfilePicUrl(profile.getProfilePicUrl());
				response.setCity(profile.getCity());
				response.setLivesWithOrIn(profile.getLivesWithOrIn());
				response.setStudentId(profile.getId());
				response.setUserId(profile.getUserId());

				for (StudentApplicationEntity app : appList) {
					if (app.getStudentId() == response.getStudentId()) {
						response.setAppId(app.getId());

						long days = DateUtil.getDatesDifference(app.getApprovedDate(), LocalDateTime.now());
						response.setDaysWaiting(days);

						if (orphan != null) {
							Either<ErrorData, StudentOrphanageDetails> resp = getStudentOrphanage(app.getId());
							if (resp.isRight()) {
								StudentOrphanageDetails orphanage = resp.getRight();
								response.setOrphanageName(orphanage.getName());
								response.setOrphanageContact(orphanage.getContact());
								response.setOrphanageAddress(orphanage.getAddress());
								response.setOrphanagePinCode(orphanage.getPostalCode());
								response.setOrphanFather(orphanage.getFathersName());
								response.setOrphanMother(orphanage.getMothersName());
								break;
							}
						}
					}
				}

				Either<ErrorData, ApplicantDetails> applicantDetails = getApplicantDetails(response.getAppId());
				if (applicantDetails.isRight()) {
					response.setAspirations(applicantDetails.getRight().getAspirations());
					response.setHobbies(applicantDetails.getRight().getHobbies());
					response.setSchoolName(applicantDetails.getRight().getSchool());
				}

				Either<ErrorData, ApplicantFundingDetails> fundingDetails = getApplicantFundingDetails(response.getAppId());
				if (fundingDetails.isRight())
					response.setAmount(fundingDetails.getRight().getAmount());

				responseList.add(response);
			}

			if (responseList.isEmpty())
				return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
			return Either.right(responseList);
		}
		return Either.right(responseList);
	}

	private List<StudentProfileEntity> getProfileList(String ageRange, String month, String name, String city, String livesWithOrIn, String country,
			String gender, String income, String state, String orphan) {
		List<StudentProfileEntity> profileList = new ArrayList<>();
		int birthMonth = 0;
		if (month != null) {
			birthMonth = Integer.parseInt(month);

		}
		if (orphan != null) {
			if (ageRange != null) {
				String[] range = ageRange.split("-");
				if (birthMonth != 0) {
					profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income,
							state, DateUtil.getYearRange(Integer.parseInt(range[0])), DateUtil.getYearRange(Integer.parseInt(range[1])), birthMonth,
							orphan);
				} else {
					profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income,
							state, DateUtil.getYearRange(Integer.parseInt(range[0])), DateUtil.getYearRange(Integer.parseInt(range[1])), orphan);
				}

			} else if (birthMonth != 0) {
				profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income, state,
						birthMonth, orphan);
			} else {
				profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income, state,
						orphan);
			}
		} else {
			if (ageRange != null) {
				String[] range = ageRange.split("-");
				if (birthMonth != 0) {
					profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income,
							state, DateUtil.getYearRange(Integer.parseInt(range[0])), DateUtil.getYearRange(Integer.parseInt(range[1])), birthMonth);
				} else {
					profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income,
							state, DateUtil.getYearRange(Integer.parseInt(range[0])), DateUtil.getYearRange(Integer.parseInt(range[1])));
				}

			} else if (birthMonth != 0) {
				profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income, state,
						birthMonth);
			} else {
				profileList = (List<StudentProfileEntity>) studentRepo.findStudentDetails(name, city, livesWithOrIn, country, gender, income, state);
			}
		}
		return profileList;
	}

	public Either<ErrorData, List<StudentSummaryResponse>> getStudentSummary(long applicationID, long studentId) {

		Either<ErrorData, ApplicantFundingDetails> fundingDetails = getApplicantFundingDetails(applicationID);
		List<StudentSummaryResponse> responseList = new ArrayList<StudentSummaryResponse>();

		StudentSummaryResponse response = new StudentSummaryResponse();
		if (fundingDetails.isRight()) {
			response.setFundingDetails(fundingDetails.getRight());
		}
		Either<ErrorData, ApplicantDetails> applicantDetails = getApplicantDetails(applicationID);
		if (applicantDetails.isRight()) {
			response.setApplicantDetails(applicantDetails.getRight());
		}
		Either<ErrorData, StudentOrphanageDetails> orphanageDetails = getStudentOrphanage(applicationID);
		if (orphanageDetails.isRight()) {
			response.setOrphanageDetails(orphanageDetails.getRight());
		}
		Either<ErrorData, StudentParentDetails> studentParentDetails = getStudentParent(applicationID);
		if (studentParentDetails.isRight()) {
			response.setStudentParentDetails(studentParentDetails.getRight());
		}
		Either<ErrorData, StudentGuardianDetails> studentGuardianDetails = getStudentGuardian(applicationID);
		if (studentGuardianDetails.isRight()) {
			response.setStudentParentDetails(studentParentDetails.getRight());
		}
		Either<ErrorData, StudentProfile> studentProfile = getStudentProfile(studentId);
		if (studentProfile.isRight()) {
			response.setStudentProfile(studentProfile.getRight());
		}

		List<SponsorEntity> sponsorDetailsList = sponsorRepo.findStudent(String.valueOf(studentId));
		List<SponsorProfileModel> sponsorProfileList = getSponsorProfileList(sponsorDetailsList);
		if (!sponsorProfileList.isEmpty())
			response.setSponsorProfileList(sponsorProfileList);

		Either<ErrorData, List<StudentApplication>> studentApplications = getApplications(studentId);
		if (studentApplications.isRight()) {
			response.setStudentApplications(studentApplications.getRight());
		}
		Either<ErrorData, StudentIncomeDetails> incomeDetails = getApplicantIncomeDetails(applicationID);
		if (incomeDetails.isRight()) {
			response.setStudentIncomeDetails(incomeDetails.getRight());
		}
		responseList.add(response);

		if (responseList.isEmpty())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
		return Either.right(responseList);
	}

	private List<SponsorProfileModel> getSponsorProfileList(List<SponsorEntity> sponsorDetailsList) {

		List<SponsorProfileModel> sponsorProfileList = new ArrayList<>();
		for (SponsorEntity sponsor : sponsorDetailsList) {

			SponsorProfileModel sponsorProfile = new SponsorProfileModel();
			sponsorProfile.setAddress(sponsor.getAddress());
			sponsorProfile.setCity(sponsor.getCity());
			sponsorProfile.setCountry(sponsor.getCountry());
			sponsorProfile.setEmail(sponsor.getEmail());
			sponsorProfile.setFirstname(sponsor.getFirstname());
			sponsorProfile.setLastname(sponsor.getLastname());
			sponsorProfile.setOrganisation(sponsor.getOrganisation());
			sponsorProfile.setState(sponsor.getState());

			sponsorProfileList.add(sponsorProfile);
		}
		return sponsorProfileList;
	}

	public Either<ErrorData, String> insertBankDetails(StudentBankDetailsRequest req, String applicationID) {
		try {
			StudentBankDetailsEntity bankDetails = new StudentBankDetailsEntity();
			bankDetails.setAccountNo(req.getAccountNo());
			bankDetails.setApplicationId(new Long(applicationID));
			bankDetails.setBankCode(req.getBankCode());
			bankDetails.setBranch(req.getBranch());
			bankDetails.setBranchAddress(req.getBranchAddress());
			bankDetails.setCountry(req.getCountry());
			bankDetails.setHolderName(req.getHolderName());
			bankDetails.setIfscCode(req.getIfscCode());
			bankDetails.setPinCode(String.valueOf(req.getPincode()));
			bankDetails.setRelationWithApplicant(req.getRelationWithApplicant());
			bankDetails.setState(req.getState());
			bankDetails.setActive(true);
			bankDetails.setCreatedBy("testing");
			bankDetails.setCreatedDate(LocalDateTime.now());
			bankDetails.setLastUpdatedBy("testing");
			bankDetails.setLastUpdatedDate(LocalDateTime.now());

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				bankDetailsRepo.save(bankDetails);
				return Either.right(Constants.BANK_DETAILS_INSERTED);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (bankDetailsRepo.updateBankDetails(bankDetails.getAccountNo(), bankDetails.getBankCode(), bankDetails.getBranch(),
						bankDetails.getBranchAddress(), bankDetails.getCountry(), bankDetails.getHolderName(), bankDetails.getIfscCode(),
						bankDetails.getPinCode(), bankDetails.getRelationWithApplicant(), bankDetails.getState(), bankDetails.getLastUpdatedBy(),
						bankDetails.getLastUpdatedDate(), req.getId()) == 1)
					return Either.right(Constants.BANK_DETAILS_UPDATED);
				else
					return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_UPDATE));
			}
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_INSERT));
	}

	public Either<ErrorData, StudentBankDetails> getBankDetails(String applicationID) {
		try {
			StudentBankDetailsEntity bankDetailsEntity = bankDetailsRepo.getBankDetails(Long.valueOf(applicationID));
			if (bankDetailsEntity == null)
				return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
			StudentBankDetails bankDetails = new StudentBankDetails();
			bankDetails.setAccountNo(bankDetailsEntity.getAccountNo());
			bankDetails.setBankCode(bankDetailsEntity.getBankCode());
			bankDetails.setBranch(bankDetailsEntity.getBranch());
			bankDetails.setBranchAddress(bankDetailsEntity.getBranchAddress());
			bankDetails.setCountry(bankDetailsEntity.getCountry());
			bankDetails.setHolderName(bankDetailsEntity.getHolderName());
			bankDetails.setId(bankDetailsEntity.getId());
			bankDetails.setPinCode(bankDetailsEntity.getPinCode());
			bankDetails.setRelationWithApplicant(bankDetailsEntity.getRelationWithApplicant());
			bankDetails.setState(bankDetailsEntity.getState());

			return Either.right(bankDetails);
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, String> insertIncomeDetails(StudentIncomeDetailsRequest req, String applicationID) {
		try {

			StudentIncomeDetailsEntity incomeDetails = new StudentIncomeDetailsEntity();
			incomeDetails.setAnnualIncome(req.getAnnualIncome());
			incomeDetails.setApplicationId(Long.valueOf(applicationID));
			incomeDetails.setCurrrency(req.getCurrency());
			incomeDetails.setEarningPerson(req.getEarningPerson());
			incomeDetails.setRelationWithStudent(req.getRelationWithStudent());
			incomeDetails.setCreatedBy("testing");
			incomeDetails.setCreatedDate(LocalDateTime.now());
			incomeDetails.setLastUpdatedBy("testing");
			incomeDetails.setLastUpdatedDate(LocalDateTime.now());

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE)) {
				incomeDetailsRepo.save(incomeDetails);
				return Either.right(Constants.INCOME_DETAILS_INSERTED);
			} else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {
				if (incomeDetailsRepo.updateIncomeDetails(incomeDetails.getAnnualIncome(), incomeDetails.getCurrrency(),
						incomeDetails.getEarningPerson(), incomeDetails.getRelationWithStudent(), incomeDetails.getLastUpdatedBy(),
						incomeDetails.getLastUpdatedDate(), req.getId()) == 1)
					return Either.right(Constants.INCOME_DETAILS_UPDATED);
			}
			return Either.left(ResponseUtils.createError(ErrorConstants.INCOME_DETAILS_FAILED));

		} catch (Exception e) {

			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}
}
