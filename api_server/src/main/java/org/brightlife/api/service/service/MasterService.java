package org.brightlife.api.service.service;

import java.util.List;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.repository.SchoolRepo;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencerwi.either.Either;

@Service
public class MasterService {

	@Autowired
	SchoolRepo schoolRepo;

	public Either<ErrorData, List<String>> getSchoolsList(String name) {
		List<String> schoolsList = schoolRepo.getSchoolsList(name);
			if (schoolsList.size() > 0) {
				return Either.right(schoolsList);
			} else {
				return Either.left(ResponseUtils.createError(ErrorConstants.UNABLE_TO_FETCH_SCHOOLS));
			
		}

	}
}
