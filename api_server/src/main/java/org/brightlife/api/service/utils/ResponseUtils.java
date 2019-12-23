package org.brightlife.api.service.utils;

import java.util.List;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ResponseData;
import org.brightlife.api.service.model.dto.response.SimpleResponse;

public class ResponseUtils {

	public static APIResponse simpleSuccessResponse(String message) {
		APIResponse res = new APIResponse();
		res.setStatus(1);
		SimpleResponse data = new SimpleResponse(message, null);
		res.setData(data);
		return res;
	}

	public static APIResponse successResponse(ResponseData data) {
		APIResponse res = new APIResponse();
		res.setStatus(1);
		res.setData(data);
		return res;
	}

	public static APIResponse errorResponse(ErrorData err) {
		APIResponse res = new APIResponse();
		res.setStatus(0);
		res.setError(err);
		return res;
	}

	public static APIResponse errorResponse(int code) {
		return errorResponse(createError(code));
	}

	public static ErrorData createError(int code) {
		String msg = getErrorMessage(code);
		return new ErrorData(code, msg);
	}

	public static String getErrorMessage(int code) {
		switch (code) {
		case ErrorConstants.INVALID_USER_NAME_PASSWORD:
			return "Invalid username/password";
		case ErrorConstants.INVALID_EMAIL:
			return "Invalid Email";
		case ErrorConstants.INVALID_NAME:
			return "Name Cannot be Null";
		case ErrorConstants.EMAIL_EXISTS:
			return "E-Mail exists";
		case ErrorConstants.INVALID_PASSWORD:
			return "Invalid Password";
		case ErrorConstants.INVALID_OTP:
			return "Invalid OTP";
		case ErrorConstants.INVALID_SOCIAL_MEDIA_TOKEN:
			return "Invalid Socail Media Token";
		case ErrorConstants.INVALID_ROLE:
			return "Invalid role";
		case ErrorConstants.INVALID_SESSION:
			return "Invalid seession, please login again";
		case ErrorConstants.ROLE_EXISTS:
			return "Role already exists";
		case ErrorConstants.OTP_TIMED_OUT:
			return "OTP Timed Out";
		case ErrorConstants.UPDATE_PASSWORD_FAILED:
			return "Failed to update Password";
		case ErrorConstants.SEND_OTP_FAILED:
			return "Failed to send OTP";
		case ErrorConstants.CANNOT_DELETE_FILE:
			return "Cannot delete specified file";
		case ErrorConstants.INVALID_FILE:
			return "Invalid file";
		case ErrorConstants.FILE_DOESNT_EXIST:
			return "File doesn't exist";
		case ErrorConstants.FILE_ALREADY_EXISTS:
			return "File already exists";
		case ErrorConstants.REQUIRE_EMAIL_WITH_SIGNUP:
			return "Get Email details";
		case ErrorConstants.UPLOAD_FAILED:
			return "Unable to upload files";
		case ErrorConstants.UNABLE_TO_FETCH_SCHOOLS:
			return "Unable to fetch schools - Try other names";
		case ErrorConstants.VERIFY_EMAIL_WITH_OTP:
			return "Verify your email with OTP";
		case ErrorConstants.NO_ELEMENTS_FOUND:
			return "No elements found";
		case ErrorConstants.UNKNOWN_REQUEST:
			return "Unknown Request";
		case ErrorConstants.SPONSORED_STUDENTS_FAILED:
			return "Failed to get Sponsored Students for Sponsor";
		case ErrorConstants.SPONSOR_PROFILE_CREATION_FAILED:
			return "Failed to create/update Sponsor Profile";
		case ErrorConstants.GET_SPONSOR_PROFILE_FAILED:
			return "Failed to get Sponsor Profile";
		case ErrorConstants.FUNDING_DETAILS_INSERT_FAILED:
			return "Failed to insert funding details";
		case ErrorConstants.GUARDIAN_PROFILE_CREATION_FAILED:
			return "Failed to create Guardian Profile";
		case ErrorConstants.GUARDIAN_STUDENTS_FAILED:
			return "Failed to get Applied Students for Guardian";
		case ErrorConstants.NO_APPLICATION_FOUND:
			return "No Student Application found";
		case ErrorConstants.FAILED_TO_SPONSOR_STUDENT:
			return "Failed to Sponsor Student";
		case ErrorConstants.INVALID_STUDENT_ID:
			return "Invalid Student ID";
		case ErrorConstants.UPDATION_FAILED_GUARDIAN_PROFILE:
			return "Failed to Update Guardian Profile";
		case ErrorConstants.UPDATION_FAILED_SPONSOR_PROFILE:
			return "Failed to Update Sponsor Profile";
		case ErrorConstants.INCOME_DETAILS_FAILED:
			return "Failed to update income detais";
		case ErrorConstants.FAILED_TO_UPDATE:
			return "Failed to update";
		case ErrorConstants.FAILED_TO_INSERT:
			return "Failed to insert";
		case ErrorConstants.ORDER_CREATION_FAILED:
			return "Failed to create Order";
		case ErrorConstants.PAYMENT_CAPTURE_FAILED:
			return "Failed to capture Payment or Payment already captured";
		case ErrorConstants.PAYMENT_DETAILS_CAPTURE_FAILED:
			return "Failed to insert payment details";
		case ErrorConstants.CUSTOMER_CREATION_FAILED:
			return "Failed to create Customer";
		case ErrorConstants.NO_SUCH_SPONSOR:
			return "No sponsor profile found";
		case ErrorConstants.CREATE_SUBSCRIPTION_FAILED:
			return "Creation of plan/subscription failed";
		}

		return "Unknown Error";
	}

	public static APIResponse successResponse(List<String> response) {
		APIResponse res = new APIResponse();
		res.setStatus(1);
		SimpleResponse data = new SimpleResponse(Constants.SUCCESS, response);
		res.setData(data);
		return res;
	}

}
