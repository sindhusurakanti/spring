package org.brightlife.api.service.consts;

public class Constants {

	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!\\*_-]).{8,40})";
	public static final String NUMBER_PATTERN = "[0-9]+";

	public static final String STUDENT = "student";
	public static final String SPONSOR = "sponsor";
	public static final String GUARDIAN = "guardian";

	public static final boolean TRUE = true;

	public static final String EMAIL_VERIFIACTION = "Email Verification";
	public static final String PURPOSE = "OTP VERIFICATION";

	public static final String INCORRECT_PASSWORD = "Incorrect Password";

	public static final String INVALID_EMAIL = "Invalid Email Address";
	public static final String CODE_NAME_EXISTS = "Code Name Already exists";

	public static final String OTP_SENT = "OTP Sent Successfully -- Verify Email";
	public static final String OTP_VERIFIED = "OTP Successfully Verified";
	public static final String PASSWORD_UPDATED = "Password Updated Successfully";
	public static final String PASSWORD_VERIFIED = "Password Verified";
	public static final String FILE_UPLOAD_SUCCESS = "File uploaded successfully";

	public static final int SIGUP_ACTION_NONE = 0;
	public static final int SIGUP_ACTION_VERIFY_EMAIL_WITH_OTP = 1;
	public static final String SENDER_EMAIL = "brightlifeyupptv2019@gmail.com";
	public static final String SENDER_PASSWORD = "Bright@2019";

	public static final String LOGIN_SUCCESSFULL = "Successfully logged in";

	public static final String SOCIAL_LOGIN_SUCCESSFULL = "Successfully logged in with Social Media";

	public static final String GET_EMAIL_DETAILS = "Get Email details";

	public static final String FORGOT_PASSWORD = "Forgot Password";

	public static final String ROLE_ASSIGNED = "Role assigned successfully";

	public static final String OTP_TIMED_OUT = "OTP Timed out";

	public static final String FACEBOOK = "facebook";

	public static final String GOOGLE = "google";

	public static final String PASSWORD = "password";
	public static final String INVALID_PASSWORD = "invalid password";

	public static final String LOGIN_WITH_SOCIAL_MEDIA = "Login in with Social Media";

	public static final String ACCESS_KEY = "AKIAXUMFM5PT7L5DBG4G";
	public static final String SECRET_KEY = "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";
	public static final String PARKING_BUCKET = "parking/";
	public static final String MAIN_BUCKET = "main/";
	public static final String BUCKET_NAME = "brightlife-images/";
	public static final String CLOUDFRONT_URL = "https://d3vc9gomqzm1a6.cloudfront.net/";
	public static final String FILE_DELETED = "File deleted successfully";
	public static final String APPLICATION_PROGRESS = "In Progress";
	public static final String APPLICATION_REVIEW = "In Review";
	public static final String APPLICATION_VERIFIED = "Verified";
	public static final String APPLICATION_REJECTED = "Rejected";

	// TODO
	public static final String PHOTO_ID_PROOF = "photoIdProof";
	public static final String INCOME_PROOF = "reportCard";
	public static final String ADDRESS_PROOF = "addressProof";
	public static final String OTHERS1 = "otherDocuments";
	public static final String OTHERS3 = "otherDocuments";
	public static final String OTHERS2 = "otherDocuments";
	public static final String UPLOADED_DOCUMENTS = "Successfully Uploaded Documents";

	public static final String SUCCESS = "Succesfully retrieved schools list";

	public static final String INR = "INR";

	public static final String RESEND_OTP = "Resend OTP";

	public static final String UPLOAD_DOCUMENTS_AND_INSERT = "Uploaded documnets and inserted details";

	public static final String REASON = "reason";

	public static final String FUNDING_DETAILS_INSERT = "Funding details inserted successfully";

	public static final String BANK_DETAILS_INSERTED = "Bank details inserted successfully";

	public static final String INCOME_DETAILS_INSERTED = "Income details inserted successfully";

	public static final String APPROVED = "Approved";

	public static final String ORPHAN_DETAILS = "Orphanage details captured";

	public static final String PARENTS = "PARENTS";

	public static final String ORPHANAGE = "ORPHANAGE";

	public static final String SPONSOR_PROFILE = "Sponsor profile created successfully";

	public static final String SPONSOR_PROFILE_UPDATED = "Sponsor profile updated with student id";

	public static final String GUARDIAN_PROFILE = "Guardian profile created successfully";

	public static final String PARENTS_DETAILS = "Parent details Captured";

	public static final String GUARDIAN_DETAILS = "Student-Guardian details captured";

	public static final String STUDENT_PROFILE = "Created Student Profile Successfully";

	public static final String EDUCATION_DETAILS_SAVED = "Education Details Saved Successfully";

	public static final String ACHIEVEMENTS_FILE = "Achievements File";

	public static final String SAVE = "save";

	public static final String UPDATE = "update";

	public static final String UPDATED_SPONSOR_PROFILE = "Sponsor Profile Updated Successfully";

	public static final String UPDATED_GUARDIAN_PROFILE = "Guardian Profile Updated Successfully";

	public static final String INCOME_DETAILS_UPDATED = "Income Details Updated";

	public static final String BANK_DETAILS_UPDATED = "Bank details updated successfully";

	public static final String UPDATE_SUCCESSFULL = "Update Successfull";

	public static final String RAZORPAY_KEY_ID = "rzp_test_caQ7OMKVvbtpc3";

	public static final String RAZORPAY_SECRET_KEY = "mQUXwGR5ok1QTNMckTEK49x3";

	public static final String S3_ACCESS_KEY = "AKIAXUMFM5PT7L5DBG4G";

	public static final String S3_SECRET_KEY = "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";

	public static final String CAPTURED = "captured";

	public static final String STATUS = "status";

	public static final String CARD = "card";

	public static final String INSERTED_CARD_DETAILS = "inserted card details successfully";

	public static final String PAYMENT_DETAILS_INSERTED = "payment details inserted successfully";

}
