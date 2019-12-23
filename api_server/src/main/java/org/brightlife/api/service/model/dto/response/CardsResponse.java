package org.brightlife.api.service.model.dto.response;

import java.util.List;

public class CardsResponse extends ResponseData {

	private List<SponsorCardDetailsModel> sponsorCardDetails;

	public List<SponsorCardDetailsModel> getSponsorCardDetails() {
		return sponsorCardDetails;
	}

	public void setSponsorCardDetails(List<SponsorCardDetailsModel> sponsorCardDetails) {
		this.sponsorCardDetails = sponsorCardDetails;
	}

}
