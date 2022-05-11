package com.midas.outflearn.controller.order;

import java.time.LocalDateTime;

import com.midas.outflearn.model.order.Order;
import com.midas.outflearn.model.order.PaymentType;

public class OrderDto {
	private Long orderId;
	private String email;
	private Long lectureId;
	private Long voucherId;
	private PaymentType paymentType;
	private LocalDateTime createdAt;

	public OrderDto(Order order) {
		this.orderId = order.getOrderId();
		this.email = order.getEmail().getAddress();
		this.lectureId = order.getLectureId();
		this.voucherId = order.getVoucherId();
		this.paymentType = order.getPaymentType();
		this.createdAt = order.getCreatedAt();
	}

	public Long getOrderId() {
		return orderId;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
