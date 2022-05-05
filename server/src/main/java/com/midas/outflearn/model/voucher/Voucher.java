package com.midas.outflearn.model.voucher;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.midas.outflearn.model.voucher.VoucherStatus.UNUSED;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Voucher {

    private Long voucherId;
    private String name;
    private String code;
    private long percent;
    private VoucherStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Voucher(String name, String code, long percent) {
        this(null, name, code, percent, null, null, null);
    }

    public Voucher(Long voucherId, String name, String code, long percent, VoucherStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkArgument(isNotEmpty(name), "name must be provided.");
        checkArgument(isNotEmpty(code), "code must be provided.");
        checkArgument(percent > 0 && percent <= 100, "percent must be greater than 0 and less or equals 100");

        this.voucherId = voucherId;
        this.name = name;
        this.code = code;
        this.percent = percent;
        this.status = defaultIfNull(status, UNUSED);
        this.createdAt = defaultIfNull(createdAt, now());
        this.updatedAt = defaultIfNull(updatedAt, now());
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public long getPercent() {
        return percent;
    }

    public VoucherStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("voucherId", voucherId)
            .append("name", name)
            .append("code", code)
            .append("percent", percent)
            .append("status", status)
            .append("createdAt", createdAt)
            .append("updatedAt", updatedAt)
            .toString();
    }
}