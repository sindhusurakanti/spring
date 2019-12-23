package org.brightlife.api.service.controller;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.razorpay.Order;
import com.razorpay.Plan;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;
import com.razorpay.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PaymentControllerOld extends BaseController {

	@PostMapping(value = "/pass")
	public ModelAndView pass(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "razorpay_payment_id", required = false) String paymentId, @RequestParam(value = "razorpay_order_id", required = false) String orderId,
			@RequestParam(value = "razorpay_signature", required = false) String signature) throws RazorpayException, JSONException {

		System.out.println(" err " + error);
		String amount = "100";
		ModelAndView view = new ModelAndView();
		if (error != null
				&& (error.equalsIgnoreCase("Wallet is not enabled for the merchant") || error.equalsIgnoreCase("Wallet is not supported") || error
						.equalsIgnoreCase("Bank code provided for net banking payment is invalid"))) {
			view.setViewName("invalid");
		}

		boolean validate = validateTransaction(paymentId, orderId, signature, amount);

		if (validate == true) {
			view.setViewName("result");
		} else {
			view.setViewName("invalid");
		}

		return view;
	}

	@PostMapping("/payment/choose")
	public ModelAndView choose(@RequestParam(value = "option", required = true) String option) {
		ModelAndView view = new ModelAndView();
		view.setViewName("index.html");
		return view;
	}

	@PostMapping("/payment/select")
	public ModelAndView select(@RequestParam(value = "amount", required = true) String amount,
			@RequestParam(value = "option", required = true) String choice, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email) throws RazorpayException, JSONException {

		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_caQ7OMKVvbtpc3", "mQUXwGR5ok1QTNMckTEK49x3");

		System.out.println(name);
		System.out.println(choice + " test choice");
		
		ModelAndView view = new ModelAndView();
		
		/*view.addObject("customer_id", "cust_DoSPOGUC7baXLV");
		view.setViewName("check.html");*/
		
		JSONObject options = new JSONObject();

		options.put("amount", amount);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 0);
		Order order = razorpayClient.Orders.create(options);

		view.addObject("order_id", order.get("id"));
		view.addObject("amount", amount);

		JSONObject customer = new JSONObject();
		customer.put("name", name);
		customer.put("email", email);

		view.addObject("name", name);
		view.addObject("email", email);

		if (name.equalsIgnoreCase("janet")) {
			String customerId = null;
			customerId = "cust_DVQq6QwmQ43uUX";
			view.addObject("customer_id", customerId);
			view.setViewName("savedcard.html");
			String tokenId = "token_DVQqS4zF7U8PQe";
			view.addObject("token_id", tokenId);
			return view;
		} else {

			if (choice.equalsIgnoreCase("card")) {
				// Customer cust = razorpayClient.Customers.create(customer);
				view.addObject("customer_id", "cust_DoSPOGUC7baXLV");
				view.setViewName("check.html");
				return view;
			} else if (choice.equalsIgnoreCase("Amazon Pay") || choice.equalsIgnoreCase("Phone Pe") || choice.equalsIgnoreCase("Paytm")
					|| choice.equalsIgnoreCase("freecharge") || choice.equalsIgnoreCase("mobikwik")) {

				view.setViewName("walletpay.html");
				view.addObject("amount", amount);
				view.addObject("method", "wallet");
				view.addObject("walletname", choice);
				view.addObject("order_id", order.get("id"));

				JSONObject request = new JSONObject();
				request.put("period", "daily");
				request.put("interval", 7);

				JSONObject item = new JSONObject();
				item.put("name", "Daily");
				item.put("description", "daily plan");
				item.put("amount", 1200);
				item.put("currency", "INR");
				request.put("item", item);

				Plan plan = razorpayClient.Plans.create(request);

				System.out.println(plan);

				// Customer cust = razorpayClient.Customers.create(customer);

				JSONObject request1 = new JSONObject();
				request1.put("plan_id", plan.get("id").toString());
				request1.put("customer_notify", 1);
				request1.put("total_count", 6);
				long time = (new Date().getTime() / 1000) + 46000;
				request1.put("start_at", time);

				

				Subscription subscription = razorpayClient.Subscriptions.create(request1);

				System.out.println(subscription);

				return view;
			} else if (choice.equalsIgnoreCase("SBIN") || choice.equalsIgnoreCase("HDFC") || choice.equalsIgnoreCase("axis")
					|| choice.equalsIgnoreCase("ICICI")) {
				view.setViewName("netbanking.html");
				view.addObject("amount", amount);
				view.addObject("method", "netbanking");
				view.addObject("bank", choice);
				view.addObject("order_id", order.get("id"));
				return view;
			} else {
				return view;
			}
		}
	}

	@PostMapping("/payment/option")
	public ModelAndView option(@RequestParam(value = "cardno", required = true) String cardno,
			@RequestParam(value = "expmonth", required = true) String expmonth, @RequestParam(value = "expyear", required = true) String expyear,
			@RequestParam(value = "cvv", required = true) String cvv, /*@RequestParam(value = "amount", required = true) String amount,*/
			@RequestParam(value = "name", required = true) String name, /*@RequestParam(value = "orderid", required = true) String id,
			@RequestParam(value = "customerid", required = false) String customerID,*/ @RequestParam(value = "email", required = true) String email)
			throws RazorpayException, JSONException {

		System.out.println(cardno + " cardno   " );

		ModelAndView model = new ModelAndView();
		//model.setViewName("paymentdetails.html");
		model.setViewName("wallet.html");
		model.addObject("method", "card");
	//	model.addObject("name", name);
		model.addObject("email", email);
		model.addObject("number", cardno);
		model.addObject("cvv", cvv);
		model.addObject("expirymonth", expmonth);
		model.addObject("expiryyear", expyear);
		//model.addObject("order_id", id);
		//model.addObject("customer_id", customerID);
		// model.addObject("subscription_id", "sub_DoSdkFmKQMV3uk");

		return model;
	}

	private boolean validateTransaction(String paymentId, String orderId, String signature, String amount) throws RazorpayException, JSONException {

		ModelAndView model = new ModelAndView();
		System.out.println(paymentId + " paymentid");

		JSONObject options = new JSONObject();

		options.put("razorpay_payment_id", paymentId);
		options.put("razorpay_order_id", orderId);
		options.put("razorpay_signature", signature);

		boolean isEqual = Utils.verifyPaymentSignature(options, "mQUXwGR5ok1QTNMckTEK49x3");

		if (isEqual) {
			System.out.println("success");
			JSONObject paymentDetails = new JSONObject();
			paymentDetails.put("amount", amount);

			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_caQ7OMKVvbtpc3", "mQUXwGR5ok1QTNMckTEK49x3");

			try {
				// Payment pay = razorpayClient.Payments.capture(paymentId,
				// paymentDetails);
				System.out.println("payment details razorpay_payment_id  " + paymentId + "  sign  " + signature + " order id " + orderId);
				model.setViewName("result");
			} catch (Exception e) {
				model.setViewName("result");
				if (e.getMessage().equalsIgnoreCase("BAD_REQUEST_ERROR:This payment has already been captured")) {
					System.out.println("here");
					model.setViewName("result");
				}
			}
			return isEqual;
		} else {
			return false;
		}

	}

	@PostMapping("/payment/result")
	public ModelAndView result() {
		ModelAndView view = new ModelAndView();
		view.setViewName("result");
		return view;
	}

	@RequestMapping(value = "/payment/existing", method = RequestMethod.POST)
	public ModelAndView savedCard() throws JSONException, RazorpayException {
		System.out.println(" in exis");
		String amount = "600";
		String custId = "cust_DV9Sfsb9kXXOQ5";
		String tokenId = "token_DV3IzopDJUZm0V";

		ModelAndView view = new ModelAndView();
		view.addObject("amount", amount);
		view.addObject("customer_id", custId);
		view.addObject("token_id", tokenId);
		view.addObject("order_id", "order_DUiv9kCEAlfd0z");
		view.setViewName("savedcard.html");

		return view;
	}

	@GetMapping("/service/api/v1/password/forgot")
	public ModelAndView start() {
		ModelAndView view = new ModelAndView();
		view.setViewName("index.html");
		return view;
	}

}
