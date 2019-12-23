package org.brightlife.api.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHost {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private SocialMediaService fbService;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private UserService userService;

	@Autowired
	private MonitoringService monitoringService;

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private StudentService studentService;

	@Autowired
	private MasterService masterService;

	@Autowired
	private SponsorService sponsorService;

	@Autowired
	private GuardianService guardianService;

	@Autowired
	private PaymentService paymentService;
	

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public AuthenticationService getAuthService() {
		return authService;
	}

	public PasswordService getPasswordService() {
		return passwordService;
	}

	public UserService getUserService() {
		return userService;
	}

	public MonitoringService getMonitoringService() {
		return monitoringService;
	}

	public VerificationService getVerificationService() {
		return verificationService;
	}

	public SocialMediaService getSocialMediaService() {
		return fbService;
	}

	public SessionService getSessionService() {
		return sessionService;
	}

	public S3Service getS3Service() {
		return s3Service;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public MasterService getMasterService() {
		return masterService;
	}

	public SponsorService getSponsorService() {
		return sponsorService;
	}

	public GuardianService getGuardianService() {
		return guardianService;
	}
}
