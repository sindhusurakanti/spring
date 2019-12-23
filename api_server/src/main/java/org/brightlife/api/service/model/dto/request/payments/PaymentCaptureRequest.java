package org.brightlife.api.service.model.dto.request.payments;

public class PaymentCaptureRequest extends PaymentsRequest {

	private String orderId;
	private String razorpayPaymentId;
	private String razorpayPaymentSignature;
	private String paymentMethod;
	private Long amount;
	private Long studentId;
	private String cardHolderName;
	private Long cardNumber;
	private String expMonthYear;
	private String subscriptionId;

	public String getCardHolderName() {
		return cardHolderName;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public String getExpMonthYear() {
		return expMonthYear;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public String getRazorpayPaymentSignature() {
		return razorpayPaymentSignature;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public Long getAmount() {
		return amount;
	}

	public Long getStudentId() {
		return studentId;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

}
