package com.midas.outflearn.controller.order;

import com.midas.outflearn.model.order.Email;
import com.midas.outflearn.model.order.Order;
import com.midas.outflearn.model.order.PaymentType;

public class OrderRequest {
	private String email;
	private Long lectureId;
	private Long voucherId;
	private PaymentType paymentType;

	public OrderRequest(String email, Long lectureId, Long voucherId, PaymentType paymentType) {
		this.email = email;
		this.lectureId = lectureId;
		this.voucherId = voucherId;
		this.paymentType = paymentType;
	}

	public Order newOrder() {
		return new Order(new Email(email), lectureId, voucherId, paymentType);
	}

	public String getEmail() {
		return email;
	}

	public Long getLectureId() {
		return lectureId;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
}
