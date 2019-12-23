package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.payments.AllCardsRequest;
import org.brightlife.api.service.model.dto.request.payments.CreateOrderRequest;
import org.brightlife.api.service.model.dto.request.payments.PaymentCaptureRequest;
import org.brightlife.api.service.model.dto.request.payments.SubscriptionRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service/api/v1")
public class PaymentController  extends BaseController {
	
	@Autowired
	private RequestProcessor requestProcessor;
	
	
	@PostMapping(value = "order/create")
	public APIResponse createOrder(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestBody CreateOrderRequest req) {
		
		return requestProcessor.handleRequest(sessionId, req);
	}
	
	@PostMapping(value = "payment/capture")
	public APIResponse capturePayment(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestBody PaymentCaptureRequest req) {
		
		return requestProcessor.handleRequest(sessionId, req);
	}

	@PostMapping(value = "all/cards")
	public APIResponse getAllCards(@RequestHeader(value = "Session-Id", required = true) String sessionId) {
		
		 AllCardsRequest req = new AllCardsRequest();
		 
		return requestProcessor.handleRequest(sessionId, req);
	}
	
	@PostMapping(value = "create/subscription")
	public APIResponse subsciptionPayment(@RequestHeader(value = "Session-Id", required = true) String sessionId, @RequestBody  SubscriptionRequest req ) {
		
		 
		return requestProcessor.handleRequest(sessionId, req);
	}
	
}
