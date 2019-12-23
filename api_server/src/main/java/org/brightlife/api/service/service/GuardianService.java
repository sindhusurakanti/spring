package org.brightlife.api.service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.GuardianEntity;
import org.brightlife.api.service.entity.StudentApplicationEntity;
import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.GuardianProfileModel;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentIncomeDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;
import org.brightlife.api.service.model.dto.request.guardian.GuardianAppliedStudentsRequest;
import org.brightlife.api.service.model.dto.request.guardian.GuardianProfileRequest;
import org.brightlife.api.service.model.dto.response.AppliedStudents;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.repository.GuardianRepo;
import org.brightlife.api.service.repository.StudentApplicationRepo;
import org.brightlife.api.service.repository.StudentProfileRepo;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spencerwi.either.Either;

@Service
@Transactional(rollbackFor = Exception.class)
public class GuardianService {

	@Autowired
	private GuardianRepo guardianRepo;

	@Autowired
	private StudentApplicationRepo appRepo;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentProfileRepo studentProfileRepo;

	@Autowired
	private SponsorService sponsorService;

	public Either<ErrorData, String> createGuardianProfile(GuardianProfileRequest req) {

		GuardianEntity guardianEntity = new GuardianEntity();
		try {
			guardianEntity.setActive(true);
			guardianEntity.setAddress(req.getAddress());
			guardianEntity.setBrightlifeInfo(req.getInfoAbtBrightLife());
			guardianEntity.setCity(req.getCity());
			guardianEntity.setCountry(req.getCountry());
			guardianEntity.setCreatedBy("testing");
			guardianEntity.setCreatedDate(LocalDateTime.now());
			guardianEntity.setEmail(req.getEmail());
			guardianEntity.setFirstname(req.getFirstname());
			guardianEntity.setLastname(req.getLastname());
			guardianEntity.setLastUpdatedBy("testing");
			guardianEntity.setLastUpdatedDate(LocalDateTime.now());
			guardianEntity.setMobileNumber(req.getMobileNo());
			guardianEntity.setOrganisation(req.getOrganisation());
			guardianEntity.setPincode(req.getPincode());
			guardianEntity.setState(req.getState());
			guardianEntity.setUserId(req.getSession().getUserId());
			guardianEntity.setId(req.getId());

			if (req.getIndicator().equalsIgnoreCase(Constants.SAVE))
				guardianRepo.save(guardianEntity);
			else if (req.getIndicator().equalsIgnoreCase(Constants.UPDATE)) {

				if (guardianRepo.updateGuardianProfile(guardianEntity.getAddress(), guardianEntity.getBrightlifeInfo(), guardianEntity.getCity(),
						guardianEntity.getCountry(), guardianEntity.getEmail(), guardianEntity.getFirstname(), guardianEntity.getLastname(),
						guardianEntity.getMobileNumber(), guardianEntity.getOrganisation(), guardianEntity.getPincode(), guardianEntity.getState(),
						guardianEntity.getLastUpdatedBy(), guardianEntity.getLastUpdatedDate(), guardianEntity.getUserId(), guardianEntity.getId()) == 1)
					return Either.right(Constants.UPDATED_GUARDIAN_PROFILE);
				else
					return Either.left(ResponseUtils.createError(ErrorConstants.UPDATION_FAILED_GUARDIAN_PROFILE));
			}
			return Either.right(Constants.GUARDIAN_PROFILE);
		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.GUARDIAN_PROFILE_CREATION_FAILED));
		}
	}

	public Either<ErrorData, List<AppliedStudents>> getAppliedStudents(GuardianAppliedStudentsRequest req) {

		try {
			List<AppliedStudents> appliedStudentsList = new ArrayList<>();

			List<Long> ids = studentProfileRepo.getStudentProfileIds(req.getSession().getUserId());

			for (Long studentId : ids) {

				Either<ErrorData, StudentProfile> studentProfile = studentService.getStudentProfile(studentId);

				AppliedStudents appliedStudent = new AppliedStudents();
				if (studentProfile.isRight()) {
					appliedStudent.setProfile(studentProfile.getRight());
				}
				StudentApplicationEntity studentApplicationDetails = appRepo.findApplicationID(studentId);
				if (studentApplicationDetails != null) {

					StudentApplication studentApplication = sponsorService.getValues(studentApplicationDetails);
					appliedStudent.setStudentApplicationDetails(studentApplication);

					Either<ErrorData, ApplicantFundingDetails> fundingDetails = studentService.getApplicantFundingDetails(studentApplicationDetails
							.getId());

					if (fundingDetails.isRight()) {
						appliedStudent.setFundingDetails(fundingDetails.getRight());
					}
					Either<ErrorData, StudentGuardianDetails> studentGuardianDetails = studentService.getStudentGuardian(studentApplicationDetails
							.getId());
					if (studentGuardianDetails.isRight()) {
						appliedStudent.setGuardianDetails(studentGuardianDetails.getRight());
					}
					Either<ErrorData, StudentOrphanageDetails> orphangeDetails = studentService
							.getStudentOrphanage(studentApplicationDetails.getId());
					if (orphangeDetails.isRight()) {
						appliedStudent.setOrphanageDetails(orphangeDetails.getRight());
					}
					Either<ErrorData, StudentParentDetails> studentParentDetails = studentService.getStudentParent(studentApplicationDetails.getId());
					if (studentParentDetails.isRight()) {
						appliedStudent.setParentDetails(studentParentDetails.getRight());
					}
					Either<ErrorData, ApplicantDetails> applicantDetails = studentService.getApplicantDetails(studentApplicationDetails.getId());
					if (applicantDetails.isRight()) {
						appliedStudent.setApplicantDetails(applicantDetails.getRight());
					}
					Either<ErrorData, StudentIncomeDetails> incomeDetails = studentService.getApplicantIncomeDetails(studentApplicationDetails
							.getId());
					if (incomeDetails.isRight()) {
						appliedStudent.setStudentIncomeDetails(incomeDetails.getRight());
					}
				}

				appliedStudentsList.add(appliedStudent);
			}
			if (!appliedStudentsList.isEmpty())
				return Either.right(appliedStudentsList);

		} catch (Exception e) {
			return Either.left(ResponseUtils.createError(ErrorConstants.GUARDIAN_STUDENTS_FAILED));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
	}

	public Either<ErrorData, GuardianProfileModel> getGuardianProfile(long userId) {

		GuardianEntity guardianEntity = guardianRepo.findGuardian(userId);

		if (guardianEntity == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		GuardianProfileModel guardianProfile = new GuardianProfileModel();

		guardianProfile.setAddress(guardianEntity.getAddress());
		guardianProfile.setCity(guardianEntity.getCity());
		guardianProfile.setCountry(guardianEntity.getCountry());
		guardianProfile.setEmail(guardianEntity.getEmail());
		guardianProfile.setFirstname(guardianEntity.getFirstname());
		guardianProfile.setLastname(guardianEntity.getLastname());
		guardianProfile.setInfoAbtBrightLife(guardianEntity.getBrightlifeInfo());
		guardianProfile.setMobileNo(guardianEntity.getMobileNumber());
		guardianProfile.setOrganisation(guardianEntity.getOrganisation());
		guardianProfile.setPincode(guardianEntity.getPincode());
		guardianProfile.setState(guardianEntity.getState());
		guardianProfile.setUserId(guardianEntity.getUserId());
		guardianProfile.setId(guardianEntity.getId());

		return Either.right(guardianProfile);
	}
}
