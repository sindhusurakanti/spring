package org.brightlife.api.service.repository;

import org.brightlife.api.service.entity.PaymentDetailsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDetailsRepo extends CrudRepository<PaymentDetailsEntity, Long> {

}
