package org.brightlife.api.service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.brightlife.api.service.consts.Constants;
import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.entity.PaymentDetailsEntity;
import org.brightlife.api.service.entity.SponsorCardDetailsEntity;
import org.brightlife.api.service.model.SponsorProfileModel;
import org.brightlife.api.service.model.dto.response.CardsResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.OrderResponse;
import org.brightlife.api.service.model.dto.response.SponsorCardDetailsModel;
import org.brightlife.api.service.model.dto.response.SubscriptionPaymemtResponse;
import org.brightlife.api.service.repository.CardDetailsRepo;
import org.brightlife.api.service.repository.PaymentDetailsRepo;
import org.brightlife.api.service.utils.ResponseUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.Customer;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.Plan;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;
import com.razorpay.Utils;
import com.spencerwi.either.Either;

@Transactional
@Service
public class PaymentService {

	@Autowired
	SponsorService sponsorService;

	@Autowired
	PaymentDetailsRepo paymentDetailsRepo;

	@Autowired
	CardDetailsRepo cardDetailsRepo;

	private String razorpayKeyId = Constants.RAZORPAY_KEY_ID;
	private String razorpaySecretKey = Constants.RAZORPAY_SECRET_KEY;

	public Either<ErrorData, OrderResponse> createOrder(Long amount, String currency, Long studentId, Long userId, String paymentMethod) {

		try {
			Either<ErrorData, SponsorProfileModel> sponsorProfile = sponsorService.getSponsorProfile(userId);

			if (sponsorProfile.isLeft())
				return Either.left(ResponseUtils.createError(ErrorConstants.NO_SUCH_SPONSOR));

			JSONObject options = new JSONObject();
			options.put("amount", amount);
			options.put("currency", currency);

			String receipt = "rcptid #" + RandomUtils.nextInt();
			options.put("receipt", receipt);
			options.put("payment_capture", 0);

			RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpaySecretKey);
			Order order = razorpayClient.Orders.create(options);

			String orderId = order.get("id");

			if (orderId.isEmpty())
				return Either.left(ResponseUtils.createError(ErrorConstants.ORDER_CREATION_FAILED));

			OrderResponse orderResp = new OrderResponse();
			orderResp.setOrderId(orderId);

			SponsorCardDetailsEntity cardDetails = cardDetailsRepo.getCustomerDetails(sponsorProfile.getRight().getId());
			if (cardDetails != null && !cardDetails.getRazorpayCustomerId().isEmpty())
				orderResp.setCustomerId(cardDetails.getRazorpayCustomerId());

			if (paymentMethod.equalsIgnoreCase(Constants.CARD) && orderResp.getCustomerId().isEmpty()) {

				JSONObject customerOptions = new JSONObject();

				customerOptions.put("name", sponsorProfile.getRight().getFirstname());
				customerOptions.put("email", sponsorProfile.getRight().getEmail());

				Customer customer = razorpayClient.Customers.create(customerOptions);

				if (customer != null) {
					orderResp.setCustomerId(customer.get("id").toString());
				} else
					return Either.left(ResponseUtils.createError(ErrorConstants.CUSTOMER_CREATION_FAILED));

			}

			Either<ErrorData, String> updateSponsorWithStudentId = sponsorService.updateSponsorwithStudentId(userId, studentId);

			if (updateSponsorWithStudentId.isRight())
				return Either.right(orderResp);
			else
				return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_UPDATE));

		} catch (JSONException | RazorpayException e) {
			e.printStackTrace();
			return Either.left(ResponseUtils.createError(ErrorConstants.UNKNOWN_ERROR));
		}
	}

	public Either<ErrorData, String> paymentDetails(Long amount, String orderId, String paymentMethod, String razorpayPaymentId,
			String razorpayPaymentSignature, Long userId, Long studentId, String cardHolderName, Long cardNumber, String expMonthYear,
			String subscriptionId) {

		boolean validatePayment = validatePayment(orderId, razorpayPaymentId, razorpayPaymentSignature);

		if (validatePayment == false)
			return Either.left(ResponseUtils.createError(ErrorConstants.PAYMENT_CAPTURE_FAILED));

		Payment pay = capturePayment(amount, razorpayPaymentId);

		if (pay == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.PAYMENT_CAPTURE_FAILED));

		if (pay.get("status").equals(Constants.CAPTURED)) {

			Either<ErrorData, SponsorProfileModel> sponsorProfile = sponsorService.getSponsorProfile(userId);
			if (sponsorProfile.isRight()) {
				Either<ErrorData, String> insertPaymentDetails = insertPaymentDetails(amount, orderId, razorpayPaymentId, razorpayPaymentSignature,
						paymentMethod, pay, sponsorProfile.getRight().getId(), studentId, cardHolderName, cardNumber, expMonthYear, subscriptionId);

				if (insertPaymentDetails.isRight())
					return Either.right(insertPaymentDetails.getRight());
			}

			return Either.left(ResponseUtils.createError(ErrorConstants.PAYMENT_DETAILS_CAPTURE_FAILED));
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.PAYMENT_CAPTURE_FAILED));
	}

	private Either<ErrorData, String> insertPaymentDetails(Long amount, String orderId, String razorpayPaymentId, String razorpayPaymentSignature,
			String paymentMethod, Payment pay, Long sponsorId, Long studentId, String cardHolderName, Long cardNumber, String expMonthYear,
			String subscriptionId) {

		PaymentDetailsEntity paymentDetails = new PaymentDetailsEntity();
		paymentDetails.setAmount(amount);
		paymentDetails.setOrderId(orderId);
		paymentDetails.setPaymentMethod(paymentMethod);
		paymentDetails.setRazorpayPaymentSignature(razorpayPaymentSignature);
		paymentDetails.setRazorpayPaymentId(razorpayPaymentId);
		paymentDetails.setSponsorId(sponsorId);
		paymentDetails.setStudentId(studentId);
		paymentDetails.setSubscriptionId(subscriptionId);
		String payId = pay.get("id").toString();
		paymentDetails.setPayId(payId);
		paymentDetails.setActive(true);
		paymentDetails.setCreatedDate(LocalDateTime.now());
		paymentDetails.setCreatedBy("testing");
		paymentDetails.setLastUpdatedBy("testing");
		paymentDetails.setLastUpdatedDate(LocalDateTime.now());

		try {
			paymentDetailsRepo.save(paymentDetails);
			if (paymentMethod.equalsIgnoreCase(Constants.CARD) && cardNumber != 0L && !expMonthYear.isEmpty()) {
				String tokenId = pay.get("token_id").toString();
				String customerId = pay.get("customer_id").toString();

				boolean cardPresent = checkCardDetails(customerId, tokenId);

				if (cardPresent == false) {
					Either<ErrorData, String> saveCardDetails = saveCardDetails(cardHolderName, cardNumber, expMonthYear, tokenId, customerId,
							sponsorId);

					if (saveCardDetails.isRight())
						return Either.right(saveCardDetails.getRight());
					else
						return Either.left(saveCardDetails.getLeft());
				}
			}
			return Either.right(Constants.PAYMENT_DETAILS_INSERTED);
		} catch (Exception e) {
			e.printStackTrace();
			return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_INSERT));
		}
	}

	private boolean checkCardDetails(String customerId, String tokenId) {

		SponsorCardDetailsEntity cardDetails = cardDetailsRepo.getCardDetails(customerId, tokenId);

		if (cardDetails != null) {
			return true;
		}
		return false;
	}

	private Either<ErrorData, String> saveCardDetails(String cardHolderName, Long cardNumber, String expMonthYear, String tokenId, String customerId,
			Long sponsorId) {

		try {
			SponsorCardDetailsEntity cardDetails = new SponsorCardDetailsEntity();
			cardDetails.setCardName(cardHolderName);
			cardDetails.setCardNo(cardNumber);

			String[] expiry = expMonthYear.split("/");

			int expMonth = Integer.parseInt(expiry[0].toString());
			int expYear = Integer.parseInt(expiry[1].toString());

			cardDetails.setExpMonth(expMonth);
			cardDetails.setExpYear(expYear);
			cardDetails.setRazorpayCustomerId(customerId);
			cardDetails.setRazorpayTokenId(tokenId);
			cardDetails.setSponsorId(sponsorId);
			cardDetails.setActive(true);
			cardDetails.setCreatedDate(LocalDateTime.now());
			cardDetails.setCreatedBy("testing");
			cardDetails.setLastUpdatedBy("testing");
			cardDetails.setLastUpdatedDate(LocalDateTime.now());

			cardDetailsRepo.save(cardDetails);
			return Either.right(Constants.INSERTED_CARD_DETAILS);

		} catch (Exception e) {
			e.printStackTrace();
			return Either.left(ResponseUtils.createError(ErrorConstants.FAILED_TO_INSERT));
		}
	}

	private Payment capturePayment(Long amount, String razorpayPaymentId) {
		try {
			JSONObject paymentDetails = new JSONObject();
			paymentDetails.put("amount", amount);

			RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpaySecretKey);
			Payment pay = razorpayClient.Payments.capture(razorpayPaymentId, paymentDetails);

			System.out.println(pay);
			return pay;

		} catch (JSONException | RazorpayException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean validatePayment(String orderId, String razorpayPaymentId, String razorpayPaymentSignature) {

		try {
			JSONObject options = new JSONObject();
			options.put("razorpay_payment_id", razorpayPaymentId);
			options.put("razorpay_order_id", orderId);
			options.put("razorpay_signature", razorpayPaymentSignature);

			boolean isEqual = Utils.verifyPaymentSignature(options, razorpaySecretKey);

			if (isEqual)
				return true;
			else
				return false;
		} catch (JSONException | RazorpayException e) {

			e.printStackTrace();
			return false;
		}
	}

	public Either<ErrorData, CardsResponse> getAllCardDetails(Long userId) {

		Either<ErrorData, SponsorProfileModel> sponsorProfile = sponsorService.getSponsorProfile(userId);

		if (sponsorProfile.isLeft())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		List<SponsorCardDetailsEntity> cardDetailsEntityList = cardDetailsRepo.findAllCards(sponsorProfile.getRight().getId());

		if (cardDetailsEntityList.size() != 0) {
			List<SponsorCardDetailsModel> cardDetails = new ArrayList<>();

			for (SponsorCardDetailsEntity entity : cardDetailsEntityList) {

				SponsorCardDetailsModel cardDetailsModel = new SponsorCardDetailsModel();
				cardDetailsModel.setCardName(entity.getCardName());
				cardDetailsModel.setCardNo(entity.getCardNo());
				cardDetailsModel.setExpMonth(entity.getExpMonth());
				cardDetailsModel.setExpYear(entity.getExpYear());
				cardDetailsModel.setRazorpayCustomerId(entity.getRazorpayCustomerId());
				cardDetailsModel.setRazorpayTokenId(entity.getRazorpayTokenId());

				cardDetails.add(cardDetailsModel);
			}

			CardsResponse response = new CardsResponse();
			response.setSponsorCardDetails(cardDetails);
			return Either.right(response);
		}
		return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));
	}

	public Either<ErrorData, SubscriptionPaymemtResponse> createPlanAndSubscription(long userId, Long recurringAmount, String currency,
			String planType, int interval) {

		Either<ErrorData, SponsorProfileModel> sponsorProfile = sponsorService.getSponsorProfile(userId);

		if (sponsorProfile.isLeft())
			return Either.left(ResponseUtils.createError(ErrorConstants.NO_ELEMENTS_FOUND));

		Subscription subscription = createPlanAndSubscription(recurringAmount, planType, currency, interval);

		if (subscription == null)
			return Either.left(ResponseUtils.createError(ErrorConstants.CREATE_SUBSCRIPTION_FAILED));

		SubscriptionPaymemtResponse subscriptionResp = new SubscriptionPaymemtResponse();

		subscriptionResp.setSubscriptionId(subscription.get("id").toString());
		return Either.right(subscriptionResp);
	}

	private Subscription createPlanAndSubscription(Long recurringAmount, String planType, String currency, int interval) {

		try {
			JSONObject request = new JSONObject();
			request.put("period", planType);
			request.put("interval", interval);

			JSONObject item = new JSONObject();
			item.put("name", planType);
			item.put("description", "plan type is " + planType);
			item.put("amount", recurringAmount);
			item.put("currency", currency);
			request.put("item", item);

			RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpaySecretKey);
			Plan plan = razorpayClient.Plans.create(request);

			if (plan == null)
				return null;

			JSONObject subscriptionReq = new JSONObject();
			subscriptionReq.put("plan_id", plan.get("id").toString());
			subscriptionReq.put("customer_notify", 1);
			subscriptionReq.put("total_count", 6);
			long time = (new Date().getTime() / 1000) + 46000;
			subscriptionReq.put("start_at", time);

			Subscription subscription = razorpayClient.Subscriptions.create(subscriptionReq);
			return subscription;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
