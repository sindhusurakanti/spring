package org.brightlife.api.service.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.model.dto.request.studentapplication.StudentDocumentsRequest;
import org.springframework.web.multipart.MultipartFile;

public class StringUtils {

	public static boolean isNullOrEmpty(String s) {
		return (s == null) || s.isEmpty();
	}

	public static boolean isNullOrEmpty(Long s) {
		return (s == null) || s == 0;
	}
	public static boolean isNullOrEmpty(Integer s) {
		return (s == null) || s == 0;
	}
	public static boolean isNullOrEmpty(MultipartFile s) {
		return (s == null) || s.isEmpty();
	}
	public static String convertToCode(String s) {
		return s.toLowerCase().replaceAll("&", "and").replaceAll("@", "at").replaceAll(":", "").replaceAll("'", "")
				.replaceAll("[^a-zA-Z0-9]", "-").replaceAll("[-]+", "-")
				+ RandomStringUtils.randomAlphabetic(s.length());
	}

	public static long toLong(String str, long defaultValue) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}

	public static int toInteger(String str, int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}

	public static Map<String, File> getConvertedFiles(StudentDocumentsRequest req,
			String applicationId) throws IOException {

		Map<String, File> documentsMap = new HashMap<String, File>();

		documentsMap.put(Constants.PHOTO_ID_PROOF, convertToFile(req.getPhotoidProof(), applicationId,
				Constants.PHOTO_ID_PROOF + StringUtils.getExtension(req.getPhotoidProof().getOriginalFilename())));
		documentsMap.put(Constants.INCOME_PROOF, convertToFile(req.getIncomeProof(), applicationId,
				Constants.INCOME_PROOF + StringUtils.getExtension(req.getIncomeProof().getOriginalFilename())));
		documentsMap.put(Constants.ADDRESS_PROOF, convertToFile(req.getAddressProof(), applicationId,
				Constants.ADDRESS_PROOF + StringUtils.getExtension(req.getAddressProof().getOriginalFilename())));
		if (req.getOthers1() != null) {
			documentsMap.put(Constants.OTHERS1, convertToFile(req.getOthers1(), applicationId,
					Constants.OTHERS1 + StringUtils.getExtension(req.getOthers1().getOriginalFilename())));
		}
		if (req.getOthers2() != null) {
			documentsMap.put(Constants.OTHERS2, convertToFile(req.getOthers2(), applicationId,
					Constants.OTHERS2 + StringUtils.getExtension(req.getOthers2().getOriginalFilename())));
		}
		if (req.getOthers3() != null) {
			documentsMap.put(Constants.OTHERS3, convertToFile(req.getOthers3(), applicationId,
					Constants.OTHERS3 + StringUtils.getExtension(req.getOthers3().getOriginalFilename())));
		}
		return documentsMap;
	}

	public static File convertToFile(MultipartFile mutipartFile, String applicationId, String fileName)
			throws IOException {

		File convFile = new File("  " + applicationId + "_" + fileName); // leading spaces
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(mutipartFile.getBytes());
		fos.close();
		return convFile;
	}

	public static String appendString(File value) {

		String fileName = value.getName().trim();
		System.out.println(Constants.CLOUDFRONT_URL + fileName);
		return Constants.CLOUDFRONT_URL + fileName;
	}

	public static String getExtension(String file) {
		int len = file.length() - 1, index = 0;
		while (len > 0) {
			if (file.charAt(len) == '.') {
				index = len;
				break;
			}
			len--;
		}
		return file.substring(index);
	}
}
