package org.brightlife.api.service.model.dto.request.payments;

public class CreateOrderRequest extends PaymentsRequest {

	private Long amount;
	private String currency;
	private Long studentId;
	private String paymentMethod;

	public Long getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public Long getStudentId() {
		return studentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

}
