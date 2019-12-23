package org.brightlife.api.service.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.brightlife.api.service.consts.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class S3Utils {

	private String accessKey = Constants.S3_ACCESS_KEY;
	
	private String secretKey = Constants.S3_SECRET_KEY;

	public S3Utils(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	Logger Log = LoggerFactory.getLogger(this.getClass());

	BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

	public void uploadFile(String bucketName, String filePathInS3, MultipartFile file) {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
				.build();
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(file.getContentType());
		try {
			s3Client.putObject(bucketName, filePathInS3, file.getInputStream(), meta);
		} catch(Exception e) {
		}
	}
	
	public void uploadFile(String bucketName, String filePathInS3, File file) {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
				.build();
		try {
			s3Client.putObject(bucketName, filePathInS3, file);
		} catch(Exception e) {
		}
	}

	public boolean alreadyExists(String bucketName, String key) {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
				.build();
		return s3Client.doesObjectExist(bucketName, key);
	}

	public void deleteFile(String bucketName, String key) {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
				.build();
		s3Client.deleteObject(bucketName, key);
	}

	public File downloadFile(String bucketName, String fileName, String targetFilePath) {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
				.build();
		return writeToFile(s3Client.getObject(bucketName, fileName).getObjectContent(), targetFilePath);
	}

	private File writeToFile(InputStream input, String path) {
		File file = new File(path);
		try {
			FileOutputStream output = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[10000];
			while ((read = input.read(bytes)) != -1) {
				output.write(bytes, 0, read);
			}
			output.close();
			Log.info("created file @ " + file.getAbsolutePath());
		} catch (Exception e) {

		}
		return file;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

}
