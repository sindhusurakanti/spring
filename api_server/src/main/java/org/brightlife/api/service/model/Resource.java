package org.brightlife.api.service.model;

public class Resource {
	private String key;
	private String value;
	private String dataType;
	private String subType;

	public Resource(String key, String value, String dataType, String subType) {
		super();
		this.key = key;
		this.value = value;
		this.dataType = dataType;
		this.subType = subType;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDataType() {
		return dataType;
	}

	public String getSubType() {
		return subType;
	}

}
