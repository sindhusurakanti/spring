package org.brightlife.api.service.model;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class StudentOrphanageDetails extends ResponseData {

	private Long id;
	private Long applicationId;
	private String name;
	private Long contact;
	private String address;
	private Long postalCode;
	private boolean parentDetailsAvailable;
	private String fathersName;
	private String mothersName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long application_id) {
		this.applicationId = application_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isParentDetailsAvailable() {
		return parentDetailsAvailable;
	}

	public void setParentDetailsAvailable(boolean parentDetailsAvailable) {
		this.parentDetailsAvailable = parentDetailsAvailable;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

}
