package org.brightlife.api.service.repository;

import java.util.List;

import org.brightlife.api.service.entity.SponsorCardDetailsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CardDetailsRepo extends CrudRepository<SponsorCardDetailsEntity, Long>{

	@Query(value = "select * from sponsor_card_details where razorpay_customer_id = ?1 and razorpay_token_id = ?2  ", nativeQuery = true)
	SponsorCardDetailsEntity getCardDetails(@Param("razorpay_customer_id")String customerId, @Param("razorpay_token_id")String tokenId);

	@Query(value = "select * from sponsor_card_details where sponsor_id =?1 limit 1", nativeQuery = true)
	SponsorCardDetailsEntity getCustomerDetails(@Param("sponsor_id")Long sponsorId);

	@Query(value = "select * from sponsor_card_details where sponsor_id =?1", nativeQuery = true)
	List<SponsorCardDetailsEntity> findAllCards(Long id);

}
