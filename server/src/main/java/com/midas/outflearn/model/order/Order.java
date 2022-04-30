package com.midas.outflearn.model.order;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Order {
    private Long orderId;
    private Email email;
    private Long lectureId;
    private Long voucherId;
    private PaymentType paymentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(Email email, Long lectureId, Long voucherId, PaymentType paymentType) {
        this(null, email, lectureId, voucherId, paymentType, null, null);
    }

    public Order(Long orderId, Email email, Long lectureId, Long voucherId, PaymentType paymentType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkArgument(email != null, "email must be provided.");
        checkArgument(lectureId != null, "lectureId must be provided.");
        checkArgument(paymentType != null, "paymentType must be provided.");

        this.orderId = orderId;
        this.email = email;
        this.lectureId = lectureId;
        this.voucherId = voucherId;
        this.paymentType = paymentType;
        this.createdAt = defaultIfNull(createdAt, now());
        this.updatedAt = defaultIfNull(updatedAt, now());
    }

    public Long getOrderId() {
        return orderId;
    }

    public Email getEmail() {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    static public class Builder {
        private Long orderId;
        private Email email;
        private Long lectureId;
        private Long voucherId;
        private PaymentType paymentType;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder() {
        }

        public Builder(Order order) {
            this.orderId = order.orderId;
            this.email = order.email;
            this.lectureId = order.lectureId;
            this.voucherId = order.voucherId;
            this.paymentType = order.paymentType;
            this.createdAt = order.createdAt;
            this.updatedAt = order.updatedAt;
        }

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder lectureId(Long lectureId) {
            this.lectureId = lectureId;
            return this;
        }

        public Builder voucherId(Long voucherId) {
            this.voucherId = voucherId;
            return this;
        }

        public Builder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Order build() {
            return new Order(orderId, email, lectureId, voucherId, paymentType, createdAt, updatedAt);
        }
    }
}
