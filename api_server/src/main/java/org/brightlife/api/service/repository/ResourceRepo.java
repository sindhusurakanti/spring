package org.brightlife.api.service.repository;

import org.brightlife.api.service.entity.ResourceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepo extends CrudRepository<ResourceEntity, Long> {

}
