package org.brightlife.api.service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.SponsorEntity;
import org.brightlife.api.service.entity.StudentApplicationEntity;
import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.SponsorProfileModel;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentIncomeDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;
import org.brightlife.api.service.model.dto.request.sponsor.SponsorProfileRequest;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.SponsoredStudentsList;
import org.brightlife.api.service.repository.SponsorRepo;
import org.brightlife.api.service.repository.StudentApplicationRepo;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spencerwi.either.Either;

@Transactional
@Service
public class SponsorService {

	@Autowired
	private SponsorRepo sponsorRepo;

	@Autowired
	private StudentApplicationRepo appRepo;

	@Autowired
	private StudentService studentService;

	public Either<ErrorData, String> createSponsorProfile(SponsorProfileRequest req) {

		SponsorEntity sponsorEntity = new SponsorEntity();
		sponsorEntity.setActive(true);
		sponsorEntity.setAddress(req.getAddress());
		sponsorEntity.setBrightlifeInfo(req.getInfoAbtBrightLife());
		sponsorEntity.setCity(req.getCity());
		sponsorEntity.setCountry(req.getCountry());
		sponsorEntity.setCreatedBy("testing");
		sponsorEntity.setCreatedDate(LocalDateTime.now());
		sponsorEntity.setEmail(req.getEmail());
		sponsorEntity.setFirstname(req.getFirstname());
		sponsorEntity.setLastname(req.getLastname());
		sponsorEntity.setLastUpdatedBy("testing");
		sponsorEntity.setLastUpdatedDate(LocalDateTime.now());
		sponsorEntity.setMobileNumber(req.getMobileNo());
		sponsorEntity.setOrganisation(req.getOrganisation());
		sponsorEntity.setPincode(req.getPincode());
		sponsorEntity.setState(req.getState());
		sponsorEntity.setUserId(req.getSession().getUserId());
		sponsorEntity.setSponsoredStudentId("");
		try {
			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE))
				sponsorRepo.save(sponsorEntity);
			else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {

				if (sponsorRepo.updateSponsorProfile(sponsorEntity.getAddress(), req.getInfoAbtBrightLife(), req.getCity(), req.getCountry(),
						req.getEmail(), req.getFirstname(), req.getLastname(), req.getMobileNo(), req.getOrganisation(), req.getPincode(),
						req.getState(), sponsorEntity.getLastUpdatedBy(), sponsorEntity.getLastUpdatedDate(), req.getUserId(), req.getId()) == 1)
					return Either.right(Constants.UPDATED_SPONSOR_PROFILE);
				else
					return Either.left(ResponseUtils.createError(ErrorConstants.UPDATION_FAILED_SPONSOR_PROFILE));
			}

			return Either.right(Constants.SPONSOR_PROFILE);
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.SPONSOR_PROFILE_CREATION_FAILED));
		}
	}

	public Either<ErrorData, String> updateSponsorwithStudentId(Long userId, Long studentId) {
		try {
			Either<ErrorData, StudentProfile> studentProfile = studentService.getStudentProfile(studentId);
			if (studentProfile.isLeft())
				return Either.left(ResponseUtils.createError(ErrorConstants.INVALID_STUDENT_ID));

			if (sponsorRepo.updateSponsorWithStudent(studentId, "testing", LocalDateTime.now(), userId) == 1)
				return Either.right(Constants.SPONSOR_PROFILE_UPDATED);
			else
				return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_SPONSOR_STUDENT));
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_SPONSOR_STUDENT));
		}
	}

	public Either<ErrorData, List<SponsoredStudentsList>> getSponsoredStudents(long userId) {
		List<SponsoredStudentsList> sponsoredStudentsList = new ArrayList<>();

		try {
			String ids = sponsorRepo.getSponsoredStudentIds(userId);

			String[] studentIds = ids.split(",");

			for (String id : studentIds) {
				if (id.isEmpty()) {
					continue;
				}

				Long studentId = Long.valueOf(id);
				Either<ErrorData, StudentProfile> studentProfile = studentService.getStudentProfile(studentId);

				SponsoredStudentsList sponsoredStudent = new SponsoredStudentsList();
				if (studentProfile.isRight()) {
					sponsoredStudent.setProfile(studentProfile.getRight());
				}
				StudentApplicationEntity studentApplicationDetails = appRepo.findApplicationID(studentId);
				if (studentApplicationDetails != null) {
					StudentApplication studentApplication = getValues(studentApplicationDetails);

					sponsoredStudent.setstudentApplicationDetails(studentApplication);

					Either<ErrorData, ApplicantFundingDetails> fundingDetails = studentService.getApplicantFundingDetails(studentApplicationDetails
							.getId());

					if (fundingDetails.isRight()) {
						sponsoredStudent.setFundingDetails(fundingDetails.getRight());
					}
					Either<ErrorData, StudentGuardianDetails> studentGuardianDetails = studentService.getStudentGuardian(studentApplicationDetails
							.getId());
					if (studentGuardianDetails.isRight()) {
						sponsoredStudent.setGuardianDetails(studentGuardianDetails.getRight());
					}
					Either<ErrorData, StudentOrphanageDetails> orphangeDetails = studentService
							.getStudentOrphanage(studentApplicationDetails.getId());
					if (orphangeDetails.isRight()) {
						sponsoredStudent.setOrphanageDetails(orphangeDetails.getRight());
					}
					Either<ErrorData, StudentParentDetails> studentParentDetails = studentService.getStudentParent(studentApplicationDetails.getId());
					if (studentParentDetails.isRight()) {
						sponsoredStudent.setParentDetails(studentParentDetails.getRight());
					}
					Either<ErrorData, ApplicantDetails> applicantDetails = studentService.getApplicantDetails(studentApplicationDetails.getId());
					if (applicantDetails.isRight()) {
						sponsoredStudent.setApplicantDetails(applicantDetails.getRight());
					}
					Either<ErrorData, StudentIncomeDetails> incomeDetails = studentService.getApplicantIncomeDetails(studentApplicationDetails
							.getId());
					if (incomeDetails.isRight()) {
						sponsoredStudent.setIncomeDetails(incomeDetails.getRight());
					}
				}

				sponsoredStudentsList.add(sponsoredStudent);
			}
			if (!sponsoredStudentsList.isEmpty())
				return Either.right(sponsoredStudentsList);

		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.SPONSORED_STUDENTS_FAILED));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

	}

	public StudentApplication getValues(StudentApplicationEntity studentApplicationDetails) {
		StudentApplication studentApplication = new StudentApplication();

		studentApplication.setId(studentApplicationDetails.getId());
		studentApplication.setAppliedBy(studentApplicationDetails.getAppliedBy());
		studentApplication.setAppliedDate(studentApplicationDetails.getAppliedDate());
		studentApplication.setApprovedBy(studentApplicationDetails.getApprovedBy());
		studentApplication.setApprovedDate(studentApplicationDetails.getApprovedDate());
		studentApplication.setStatus(studentApplicationDetails.getStatus());
		studentApplication.setStudentId(studentApplicationDetails.getStudentId());
		return studentApplication;
	}

	public Either<ErrorData, SponsorProfileModel> getSponsorProfile(Long userId) {

		SponsorEntity sponsorEntity = sponsorRepo.findSponsorProfile(userId);
		if (sponsorEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		SponsorProfileModel sponsorProfile = new SponsorProfileModel();

		sponsorProfile.setAddress(sponsorEntity.getAddress());
		sponsorProfile.setCity(sponsorEntity.getCity());
		sponsorProfile.setCountry(sponsorEntity.getCountry());
		sponsorProfile.setEmail(sponsorEntity.getEmail());
		sponsorProfile.setFirstname(sponsorEntity.getFirstname());
		sponsorProfile.setLastname(sponsorEntity.getLastname());
		sponsorProfile.setOrganisation(sponsorEntity.getOrganisation());
		sponsorProfile.setPincode(sponsorEntity.getPincode());
		sponsorProfile.setState(sponsorEntity.getState());
		sponsorProfile.setId(sponsorEntity.getId());
		sponsorProfile.setUserID(sponsorEntity.getUserId());

		return Either.right(sponsorProfile);
	}
}
