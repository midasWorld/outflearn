package com.midas.outflearn.controller.voucher;

import com.midas.outflearn.model.voucher.Voucher;

public class VoucherDto {

    private final Long voucherId;
    private final String name;
    private final String code;
    private final long percent;

    public VoucherDto(Voucher voucher) {
        this.voucherId = voucher.getVoucherId();
        this.name = voucher.getName();
        this.code = voucher.getCode();
        this.percent = voucher.getPercent();
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
}
