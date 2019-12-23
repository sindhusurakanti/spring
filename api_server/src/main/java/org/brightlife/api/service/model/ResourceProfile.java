package org.brightlife.api.service.model;

public class ResourceProfile {

	private long id;
	private String code;
	private String name;
	private String urlPrefix;
	private String uploadPath;
	private boolean isDefault;

	public ResourceProfile(long id, String code, String name, String urlPrefix, String uploadPath, boolean isDefault) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.urlPrefix = urlPrefix;
		this.uploadPath = uploadPath;
		this.isDefault = isDefault;
	}

	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public boolean isDefault() {
		return isDefault;
	}

}
