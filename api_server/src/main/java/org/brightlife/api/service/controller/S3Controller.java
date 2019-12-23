package org.brightlife.api.service.controller;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.service.S3Service;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spencerwi.either.Either;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service/api/v1")
public class S3Controller {

	@Autowired
	private S3Service s3Service;

	@RequestMapping("delete")
	public APIResponse delete(@RequestParam("name") String name) {
		Either<ErrorData, String> response = s3Service.deleteFile(name);
		if(response.isLeft()) {
			return ResponseUtils.errorResponse(response.getLeft());
		}
		return ResponseUtils.simpleSuccessResponse(response.getRight());
	}

	@RequestMapping("move")
	public APIResponse move(@RequestParam("name") String name) {

		if (!StringUtils.isNullOrEmpty(name)) {
			return ResponseUtils.errorResponse(ErrorConstants.UNKNOWN_ERROR);
		}
		Either<ErrorData, String> res = s3Service.move(name, 123456, "doc" + StringUtils.getExtension(name));
		if (res.isRight()) {
			System.out.println(Constants.CLOUDFRONT_URL + Constants.MAIN_BUCKET + name);
			return ResponseUtils.simpleSuccessResponse(res.getRight());
		}
		return ResponseUtils.errorResponse(res.getLeft());
	}

}
