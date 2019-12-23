package org.brightlife.api.service.repository;

import org.brightlife.api.service.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepo extends CrudRepository<UserRoleEntity, Long>{
	
	@Query(value = "SELECT * FROM user_role WHERE USER_ID = ?1 AND role_code = ?2", nativeQuery = true)
	UserRoleEntity findByUserId(@Param("ID") long id, @Param("role_code") String code);

}
