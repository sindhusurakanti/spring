package org.brightlife.api.service.handler;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.payments.AllCardsRequest;
import org.brightlife.api.service.model.dto.request.payments.CreateOrderRequest;
import org.brightlife.api.service.model.dto.request.payments.PaymentCaptureRequest;
import org.brightlife.api.service.model.dto.request.payments.SubscriptionRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.CardsResponse;
import org.brightlife.api.service.model.dto.response.ErrorData;
import org.brightlife.api.service.model.dto.response.OrderResponse;
import org.brightlife.api.service.model.dto.response.SubscriptionPaymemtResponse;
import org.brightlife.api.service.model.dto.response.ValidationError;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;

import com.spencerwi.either.Either;

public class PaymentsHandler extends RequestHandler {

	public PaymentsHandler(ServiceHost host) {
		super(host);
	}

	@Override
	public APIResponse process(Request req) {
		ValidationErrorData errData = new ValidationErrorData();
		if (req instanceof CreateOrderRequest) {
			return process((CreateOrderRequest) req);
		}
		if (req instanceof PaymentCaptureRequest) {
			return process((PaymentCaptureRequest) req);
		}
		if (req instanceof AllCardsRequest) {
			return process((AllCardsRequest) req);
		}
		if (req instanceof SubscriptionRequest) {
			return process((SubscriptionRequest) req);
		}
		errData.setMessage(ResponseUtils.getErrorMessage(ErrorConstants.UNKNOWN_REQUEST));
		return ResponseUtils.errorResponse(errData);
	}

	@Override
	public ValidationErrorData validate(Request req) {
		if (req instanceof CreateOrderRequest) {
			return validate((CreateOrderRequest) req);
		}
		if (req instanceof PaymentCaptureRequest) {
			return validate((PaymentCaptureRequest) req);
		}
		if (req instanceof AllCardsRequest) {
			return validate((AllCardsRequest) req);
		}
		if (req instanceof SubscriptionRequest) {
			return validate((SubscriptionRequest) req);
		}
		return new ValidationErrorData();
	}

	private ValidationErrorData validate(CreateOrderRequest req) {
		ValidationErrorData errData = new ValidationErrorData();

		if (req.getAmount() == 0L) {
			errData.addError(new ValidationError("amount", "amount can't be zero", "invalid amount"));
			errData.setMessage("amount can't be zero");
		}
		if (req.getCurrency() == null) {
			errData.addError(new ValidationError("currency", "currency can't be null", "invalid currency"));
			errData.setMessage("currency can't be null");
		}
		if (req.getStudentId() == 0L) {
			errData.addError(new ValidationError("studentID", "studentID can't be zero", "invalid studentID"));
			errData.setMessage("studentID can't be zero");
		}

		return errData;
	}

	private ValidationErrorData validate(PaymentCaptureRequest req) {
		ValidationErrorData errData = new ValidationErrorData();

		
		if (req.getRazorpayPaymentId().isEmpty()) {
			errData.addError(new ValidationError("RazorpayPaymentId", "RazorpayPaymentId can't be null", "invalid RazorpayPaymentId"));
			errData.setMessage("RazorpayPaymentId can't be null");
		}
		if (req.getRazorpayPaymentSignature().isEmpty()) {
			errData.addError(new ValidationError("RazorpayPaymentSignature", "RazorpayPaymentSignature can't be null",
					"invalid RazorpayPaymentSignature"));
			errData.setMessage("RazorpayPaymentSignature can't be null");
		}
		if (req.getPaymentMethod().isEmpty()) {
			errData.addError(new ValidationError("payment method", "payment method can't be empty", "invalid payment method"));
			errData.setMessage("payment method can't be empty");
		}
		return errData;
	}

	private ValidationErrorData validate(AllCardsRequest req) {

		ValidationErrorData errData = new ValidationErrorData();

		return errData;
	}

	private ValidationErrorData validate(SubscriptionRequest req) {

		ValidationErrorData errData = new ValidationErrorData();

		if (req.getRecurringAmount() == 0L) {
			errData.addError(new ValidationError("Recurring amount", "Recurring amount can't be zero", "invalid amount"));
			errData.setMessage("Recurring amount can't be zero");
		}

		return errData;
	}

	private APIResponse process(CreateOrderRequest req) {

		Either<ErrorData, OrderResponse> orderResponse = host.getPaymentService().createOrder(req.getAmount(), req.getCurrency(), req.getStudentId(),
				req.getSession().getUserId(), req.getPaymentMethod());

		if (orderResponse.isLeft())
			return ResponseUtils.errorResponse(orderResponse.getLeft());

		return ResponseUtils.successResponse(orderResponse.getRight());
	}

	private APIResponse process(PaymentCaptureRequest req) {

		Either<ErrorData, String> paymentResponse = host.getPaymentService().paymentDetails(req.getAmount(), req.getOrderId(),
				req.getPaymentMethod(), req.getRazorpayPaymentId(), req.getRazorpayPaymentSignature(), req.getSession().getUserId(),
				req.getStudentId(), req.getCardHolderName(), req.getCardNumber(), req.getExpMonthYear(), req.getSubscriptionId());

		if (paymentResponse.isLeft())
			return ResponseUtils.errorResponse(paymentResponse.getLeft());

		return ResponseUtils.simpleSuccessResponse(paymentResponse.getRight());

	}

	private APIResponse process(AllCardsRequest req) {

		Either<ErrorData, CardsResponse> cardsResponse = host.getPaymentService().getAllCardDetails(req.getSession().getUserId());

		if (cardsResponse.isLeft())
			return ResponseUtils.errorResponse(cardsResponse.getLeft());

		return ResponseUtils.successResponse(cardsResponse.getRight());
	}

	private APIResponse process(SubscriptionRequest req) {

		Either<ErrorData, SubscriptionPaymemtResponse> response = host.getPaymentService().createPlanAndSubscription(req.getSession().getUserId(),
				req.getRecurringAmount(), req.getCurrency(), req.getPlanType(), req.getInterval());

		if (response.isLeft())
			return ResponseUtils.errorResponse(response.getLeft());

		return ResponseUtils.successResponse(response.getRight());
	}
}
