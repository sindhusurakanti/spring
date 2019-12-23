package org.brightlife.api.service.repository;

import java.util.List;

import org.brightlife.api.service.entity.SchoolEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SchoolRepo extends CrudRepository<SchoolEntity, Long>{

	@Query(value = " select concat(name, ' , ', area , '  ,', district) as name from school where name like %?1%", nativeQuery = true)
	List<String> getSchoolsList(@Param("name") String name);
}
