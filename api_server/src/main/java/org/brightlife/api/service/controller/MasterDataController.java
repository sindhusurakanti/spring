package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.request.master.SchoolDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("service/api/v1")
public class MasterDataController extends BaseController{

	@Autowired
	RequestProcessor requestProcessor;
	
	@RequestMapping(value = "schoolslist" , method = RequestMethod.POST)
	public APIResponse getSchoolsList(@RequestParam(value = "queryName" , required = true)  String searchName) {
		SchoolDetailsRequest req = new SchoolDetailsRequest(searchName);
		
		return requestProcessor.handleRequest(req); 
	}
}
