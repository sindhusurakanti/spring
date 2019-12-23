package org.brightlife.api.service.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.S3Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.spencerwi.either.Either;

@Service
public class S3Service {

	public Either<ErrorData, String> uploadFile(MultipartFile file) {
		try {
			if (saveToFile(file))
				return Either.right(file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
			return Either.left(ResponseUtils.createError(ErrorConstants.INVALID_FILE));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
	}

	private boolean saveToFile(MultipartFile file) {
		String accessKey = Constants.ACCESS_KEY;
		String secretKey = Constants.SECRET_KEY;
		String bucketName = Constants.BUCKET_NAME + Constants.PARKING_BUCKET;
		S3Utils s3Utils = new S3Utils(accessKey, secretKey);
		s3Utils.uploadFile(bucketName, file.getOriginalFilename(), file);
		return s3Utils.alreadyExists(bucketName, file.getOriginalFilename());
	}

	public Either<ErrorData, String> deleteFile(String name) {
		String accessKey = Constants.ACCESS_KEY;
		String secretKey = Constants.SECRET_KEY;
		String bucketName = Constants.BUCKET_NAME + Constants.MAIN_BUCKET;
		S3Utils s3Utils = new S3Utils(accessKey, secretKey);
		System.out.println(s3Utils.alreadyExists(bucketName, name));
		if (!s3Utils.alreadyExists(bucketName, name)) {
			return Either.left(ResponseUtils.createError(ErrorConstants.FILE_DOESNT_EXIST));
		}
		s3Utils.deleteFile(bucketName, name);
		if (s3Utils.alreadyExists(bucketName, name)) {
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
		return Either.right(Constants.FILE_DELETED);
	}

	public Either<ErrorData, String> move(String name, long id, String docName) {
		String accessKey = Constants.ACCESS_KEY;
		String secretKey = Constants.SECRET_KEY;
		String fromBucket = Constants.BUCKET_NAME + Constants.PARKING_BUCKET;
		String toBucket = Constants.BUCKET_NAME + Constants.MAIN_BUCKET;
		String newName = id + docName;
		S3Utils s3Utils = new S3Utils(accessKey, secretKey);
		System.out.println(s3Utils.alreadyExists(fromBucket, name));
		if (!s3Utils.alreadyExists(fromBucket, name)) {
			return Either.left(ResponseUtils.createError(ErrorConstants.FILE_DOESNT_EXIST));
		}
		File file = new File(newName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
		file = s3Utils.downloadFile(fromBucket, name, name);
		if (s3Utils.alreadyExists(toBucket, file.getName())) {
			return Either.left(ResponseUtils.createError(ErrorConstants.FILE_ALREADY_EXISTS));
		}
		s3Utils.uploadFile(toBucket, id + docName, file);
		if (!s3Utils.alreadyExists(toBucket, id + docName)) {
			return Either.left(ResponseUtils.createError(ErrorConstants.INVALID_FILE));
		}
		file.delete();
		return Either.right(id + docName);
	}

	public Either<ErrorData, String> uploadDocuments(Map<String, File> convertedFiles, String applicationId) {

		try {
			AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(getCredentials())
					.withRegion(Regions.AP_SOUTH_1).build();
			TransferManager transfer = TransferManagerBuilder.standard().withS3Client(s3).build();
			
			String bucket = Constants.BUCKET_NAME;
			System.out.println(convertedFiles.get(Constants.PHOTO_ID_PROOF));
			
			List<File> filesToUpload = convertedFiles.values().stream().collect(Collectors.toList());
			MultipleFileUpload upload = transfer.uploadFileList(bucket, "", new File("."), filesToUpload);

			System.out.println("done" + upload.getProgress().getBytesTransferred() + "   " + filesToUpload.get(0).getName());

			upload.waitForCompletion();
			
			if (upload.isDone()) {
				System.out.println("donw");
				return Either.right(Constants.UPLOADED_DOCUMENTS);
			}
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (AmazonClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.UPLOAD_FAILED));

	}

	private AWSCredentialsProvider getCredentials() {
		String accessKey = Constants.ACCESS_KEY;
		String secretKey = Constants.SECRET_KEY;
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		return new AWSStaticCredentialsProvider(awsCredentials);

	}
}
