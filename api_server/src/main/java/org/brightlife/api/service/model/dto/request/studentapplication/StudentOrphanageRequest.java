package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentOrphanageRequest extends StudentApplicationRequest {

	private String livesWithOrIn;
	private String name;
	private Long contact;
	private String address;
	private Long postalCode;
	private boolean parentDetailsAvailable;
	private String fathersName;
	private String mothersName;
	private Long id;
	private String indicator;

	public StudentOrphanageRequest(String livesWithOrIn, String name, Long contact, String address, Long postalCode, boolean parentDetailsAvailable,
			String fathersName, String mothersName, Long id, String indicator) {
		super();
		this.livesWithOrIn = livesWithOrIn;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.postalCode = postalCode;
		this.parentDetailsAvailable = parentDetailsAvailable;
		this.fathersName = fathersName;
		this.mothersName = mothersName;
		this.id = id;
		this.indicator = indicator;
	}

	public Long getId() {
		return id;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getLivesWithOrIn() {
		return livesWithOrIn;
	}

	public String getName() {
		return name;
	}

	public Long getContact() {
		return contact;
	}

	public String getAddress() {
		return address;
	}

	public Long getPostalCode() {
		return postalCode;
	}

	public boolean isParentDetailsAvailable() {
		return parentDetailsAvailable;
	}

	public String getFathersName() {
		return fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

}
