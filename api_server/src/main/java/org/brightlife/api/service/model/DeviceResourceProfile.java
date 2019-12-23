package org.brightlife.api.service.model;

public class DeviceResourceProfile {

	public DeviceResourceProfile(long id, String profileCode, String clientType, long deviceType, String deviceSubType,
			String urlPrefix, String uploadPath, boolean isDefault) {
		super();
		this.id = id;
		this.profileCode = profileCode;
		this.clientType = clientType;
		this.deviceType = deviceType;
		this.deviceSubType = deviceSubType;
		this.urlPrefix = urlPrefix;
		this.uploadPath = uploadPath;
		this.isDefault = isDefault;
	}

	private long id;
	private String profileCode;
	private String clientType;
	private long deviceType;
	private String deviceSubType;
	private String urlPrefix;
	private String uploadPath;
	private boolean isDefault;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public long getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(long deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceSubType() {
		return deviceSubType;
	}

	public void setDeviceSubType(String deviceSubType) {
		this.deviceSubType = deviceSubType;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String name) {
		this.clientType = name;
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
}
