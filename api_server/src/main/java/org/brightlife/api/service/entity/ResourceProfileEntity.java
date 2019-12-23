package org.brightlife.api.service.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource_profile")
public class ResourceProfileEntity {

	@Id
	private long id;
	private String code;
	private String name;

	@Column(name = "url_prefix")
	private String urlPrefix;

	@Column(name = "upload_path")
	private String uploadPath;

	@Column(name = "is_default")
	private boolean isDefault;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "last_updated_by")
	private String last_updatedBy;

	@Column(name = "last_updated_date")
	private LocalDateTime last_updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getLast_updatedBy() {
		return last_updatedBy;
	}

	public void setLast_updatedBy(String last_updatedBy) {
		this.last_updatedBy = last_updatedBy;
	}

	public LocalDateTime getLast_updatedDate() {
		return last_updatedDate;
	}

	public void setLast_updatedDate(LocalDateTime last_updatedDate) {
		this.last_updatedDate = last_updatedDate;
	}

}
