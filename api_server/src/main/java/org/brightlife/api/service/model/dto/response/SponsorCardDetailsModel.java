package org.brightlife.api.service.model.dto.response;

public class SponsorCardDetailsModel {

	private Long id;

	private Long sponsorId;

	private String cardName;

	private Long cardNo;

	private int expMonth;

	private int expYear;

	private String razorpayCustomerId;

	private String razorpayTokenId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(Long sponsorId) {
		this.sponsorId = sponsorId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public String getRazorpayCustomerId() {
		return razorpayCustomerId;
	}

	public void setRazorpayCustomerId(String razorpayCustomerId) {
		this.razorpayCustomerId = razorpayCustomerId;
	}

	public String getRazorpayTokenId() {
		return razorpayTokenId;
	}

	public void setRazorpayTokenId(String razorpayTokenId) {
		this.razorpayTokenId = razorpayTokenId;
	}

}
