package org.brightlife.api.service.controller;

import org.brightlife.api.service.model.dto.response.HealthStatus;
import org.brightlife.api.service.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MonitoringController extends BaseController {

	@Autowired
	private MonitoringService healthstatusService;

	@RequestMapping("ping")
	public String ping() {
		return "pong";
	}

	@RequestMapping("healthstatus")
	public HealthStatus checkHealthStatus() {
		return healthstatusService.getHealthStatus();
	}
}