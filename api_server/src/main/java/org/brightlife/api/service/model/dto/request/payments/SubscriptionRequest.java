package org.brightlife.api.service.model.dto.request.payments;

public class SubscriptionRequest extends PaymentsRequest {

	private Long recurringAmount;
	private String currency;
	private String planType;
	private int interval;
	
	public String getCurrency() {
		return currency;
	}

	public String getPlanType() {
		return planType;
	}

	public Long getRecurringAmount() {
		return recurringAmount;
	}

	public int getInterval() {
		return interval;
	}


}
