package org.brightlife.api.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class ResourceEntity {
	
	@Id
	private Long id;
	private String key;
	private String value;
	private String dataType;
	private String subType;
	@Column(name = "is_active")
	private boolean isActive;

	/*
	public ResourceEntity(String key, String value, String dataType, String subType) {
		super();
		this.key = key;
		this.value = value;
		this.dataType = dataType;
		this.subType = subType;
	}*/

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

	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return isActive;
	}

}
