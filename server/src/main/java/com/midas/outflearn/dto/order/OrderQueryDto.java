package com.midas.outflearn.dto.order;

import com.midas.outflearn.model.order.PaymentType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class OrderQueryDto {

    private final Long orderId;
    private final String email;
    private final Long lectureId;
    private final String lectureName;
    private final long price;
    private final Long voucherId;
    private final String voucherName;
    private final long discountPercent;
    private final PaymentType paymentType;
    private final LocalDateTime createdAt;

    public OrderQueryDto(
        Long orderId, String email, Long lectureId, String lectureName, long price,
        Long voucherId, String voucherName, long discountPercent, PaymentType paymentType, LocalDateTime createdAt
    ) {
        this.orderId = orderId;
        this.email = email;
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.price = price;
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.discountPercent = discountPercent;
        this.paymentType = paymentType;
        this.createdAt = createdAt;
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

    public String getLectureName() {
        return lectureName;
    }

    public long getPrice() {
        return price;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public long getDiscountPercent() {
        return discountPercent;
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
            .append("lectureId", lectureId)
            .append("lectureName", lectureName)
            .append("price", price)
            .append("voucherId", voucherId)
            .append("voucherName", voucherName)
            .append("discountPercent", discountPercent)
            .append("paymentType", paymentType)
            .append("createdAt", createdAt)
            .toString();
    }
}
