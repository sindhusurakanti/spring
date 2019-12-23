package org.brightlife.api.service.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.brightlife.api.service.entity.DeviceResourceProfileEntity;
import org.brightlife.api.service.entity.ResourceEntity;
import org.brightlife.api.service.entity.ResourceProfileEntity;
import org.brightlife.api.service.model.DeviceResourceProfile;
import org.brightlife.api.service.model.Resource;
import org.brightlife.api.service.model.ResourceProfile;
import org.brightlife.api.service.repository.DeviceResourceProfileRepo;
import org.brightlife.api.service.repository.ResourceProfileRepo;
import org.brightlife.api.service.repository.ResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;

	@Autowired
	private ResourceProfileRepo resourceProfileRepo;

	@Autowired
	private DeviceResourceProfileRepo deviceResourceProfileRepo;

	private HashMap<String, Resource> resourceMap = null;
	private HashMap<String, ResourceProfile> resourceProfileMap = null;
	private HashMap<String, DeviceResourceProfile> deviceResourceProfileMap = null;

	@PostConstruct
	private void loadResources() {

		this.resourceMap = new HashMap<String, Resource>();
		List<ResourceEntity> resourceEntityList = (List<ResourceEntity>) resourceRepo.findAll();
		for (ResourceEntity resourceEntity : resourceEntityList) {

			Resource res = new Resource(resourceEntity.getKey(), resourceEntity.getValue(),
					resourceEntity.getDataType(), resourceEntity.getSubType());
			this.resourceMap.put(resourceEntity.getKey(), res);
		}

		this.resourceProfileMap = new HashMap<String, ResourceProfile>();
		List<ResourceProfileEntity> resourceProfileList = (List<ResourceProfileEntity>) resourceProfileRepo.findAll();
		for (ResourceProfileEntity resProEntity : resourceProfileList) {

			ResourceProfile resPro = new ResourceProfile(resProEntity.getId(), resProEntity.getCode(),
					resProEntity.getName(), resProEntity.getUrlPrefix(), resProEntity.getUploadPath(),
					resProEntity.isDefault());

			this.resourceProfileMap.put(resPro.getCode(), resPro);
		}

		this.deviceResourceProfileMap = new HashMap<String, DeviceResourceProfile>();
		List<DeviceResourceProfileEntity> devProEntityList = (List<DeviceResourceProfileEntity>) deviceResourceProfileRepo
				.findAll();
		for (DeviceResourceProfileEntity devProEntity : devProEntityList) {

			DeviceResourceProfile devPro = new DeviceResourceProfile(devProEntity.getId(),
					devProEntity.getProfileCode(), devProEntity.getClientType(), devProEntity.getDeviceType(),
					devProEntity.getDeviceSubType(), devProEntity.getUrlPrefix(), devProEntity.getUploadPath(),
					devProEntity.isDefault());

			this.deviceResourceProfileMap.put(this.generateKey(devProEntity.getProfileCode(),
					devProEntity.getClientType(), devProEntity.getDeviceType(), devProEntity.getDeviceSubType()),
					devPro);
		}
	}

	public void setResourceMap(HashMap<String, Resource> resourceMap) {
		this.resourceMap = resourceMap;
	}

	public Resource getResource(String key) {
		return resourceMap.get(key);
	}

	public String getResourceValue(String key) {
		Resource res = resourceMap.get(key);
		if (res != null)
			return res.getValue();
		return "UNKNOWN RESOURCE IN DB";
	}
	
	
	public void setResourceProfileMap(HashMap<String, ResourceProfile> resourceProfileMap) {
		this.resourceProfileMap = resourceProfileMap;
	}

	public ResourceProfile getResourceProfile(String key) {
		return resourceProfileMap.get(key);
	}

	/*
	public String getResourceProfileValue(String key) {
		ResourceProfile resPro = resourceProfileMap.get(key);
		if (resPro != null)
			return resPro.getUploadPath();
		return "UNKNOWN RESOURCE IN DB";
	}*/
	
	
	public void setDeviceResourceProfileMap(HashMap<String, DeviceResourceProfile> deviceResourceProfileMap) {
		this.deviceResourceProfileMap = deviceResourceProfileMap;
	}

	public DeviceResourceProfile getDeviceResourceProfile(String key) {
		return deviceResourceProfileMap.get(key);
	}

	/*
	public String getDeviceResourceProfileValue(String key) {
		DeviceResourceProfile devResPro = deviceResourceProfileMap.get(key);
		if (res != null)
			return res.getValue();
		return "UNKNOWN RESOURCE IN DB";
	}*/

	String generateKey(String profileCode, String clientType, long deviceType, String deviceSubType) {
		String key = profileCode + "_" + clientType + "_" + deviceType + "_" + deviceSubType;
		return key;
	}
}
