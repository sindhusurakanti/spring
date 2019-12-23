package org.brightlife.api.service.model.dto.request.studentapplication;

import org.brightlife.api.service.model.dto.request.studentapplication.StudentApplicationRequest;
import org.springframework.web.multipart.MultipartFile;

public class EducationDetailsRequest extends StudentApplicationRequest {

	public EducationDetailsRequest(String schoolName, String performance, String studentClass, String schoolAddress, String pincode,
			String aspirations, String achievements, MultipartFile achievementsFile, String hobbies, Long id, String indicator) {
		super();
		this.schoolName = schoolName;
		this.performance = performance;
		this.studentClass = studentClass;
		this.schoolAddress = schoolAddress;
		this.pincode = pincode;
		this.aspirations = aspirations;
		this.achievements = achievements;
		this.achievementsFile = achievementsFile;
		this.hobbies = hobbies;
		this.id = id;
		this.indicator = indicator;
	}

	private String schoolName;
	private String performance;
	private String studentClass;
	private String schoolAddress;
	private String pincode;
	private String aspirations;
	private String achievements;
	private MultipartFile achievementsFile;
	private String hobbies;
	private Long id;
	private String indicator;

	public String getSchoolName() {
		return schoolName;
	}

	public String getPerformance() {
		return performance;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public String getPincode() {
		return pincode;
	}

	public String getAspirations() {
		return aspirations;
	}

	public String getAchievements() {
		return achievements;
	}

	public String getHobbies() {
		return hobbies;
	}

	public MultipartFile getAchievementsFile() {
		return achievementsFile;
	}

	public void setAchievementsFile(MultipartFile achievementsFile) {
		this.achievementsFile = achievementsFile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

}
