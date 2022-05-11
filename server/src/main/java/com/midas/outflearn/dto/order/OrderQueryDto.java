package com.midas.outflearn.dto.order;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.midas.outflearn.model.order.PaymentType;

public class OrderQueryDto {

	private final Long orderId;
	private final String email;
	private final OrderJoinLectureDto lecture;
	private final OrderJoinVoucherDto voucher;
	private final PaymentType paymentType;
	private final LocalDateTime createdAt;

	public OrderQueryDto(
		Long orderId, String email, OrderJoinLectureDto lecture, OrderJoinVoucherDto voucher, PaymentType paymentType,
		LocalDateTime createdAt
	) {
		this.orderId = orderId;
		this.email = email;
		this.lecture = lecture;
		this.voucher = voucher;
		this.paymentType = paymentType;
		this.createdAt = createdAt;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getEmail() {
		return email;
	}

	public OrderJoinLectureDto getLecture() {
		return lecture;
	}

	public OrderJoinVoucherDto getVoucher() {
		return voucher;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("orderId", orderId)
			.append("email", email)
			.append("lecture", lecture)
			.append("voucher", voucher)
			.append("paymentType", paymentType)
			.append("createdAt", createdAt)
			.toString();
	}
}
