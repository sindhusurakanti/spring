package org.brightlife.api.service.service;

import javax.transaction.Transactional;

import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.UserRoleEntity;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.repository.UserRoleRepo;
import org.brightlife.api.service.utils.ResponseUtils;
import org.brightlife.api.service.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencerwi.either.Either;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRoleRepo userRoleRepo;

	public Either<ErrorData, String> assignRole(UserSession session, String role) {
		ValidationErrorData errData = new ValidationErrorData();

		if (StringUtils.isNullOrEmpty(role)) {
			errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.INVALID_ROLE));
			return Either.left(errData);
		}

		UserRoleEntity userRole = userRoleRepo.findByUserId(session.getUserId(), role);
		if (userRole != null) {
			errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.ROLE_EXISTS));
			return Either.left(errData);
		}

		userRole = new UserRoleEntity();
		userRole.setUserId(session.getUserId());
		userRole.setRoleCode(role);
		userRoleRepo.save(userRole);

		return Either.right(Constants.ROLE_ASSIGNED);
	}
}
