package com.midas.outflearn.repository.voucher;

import com.midas.outflearn.model.voucher.Voucher;

import java.util.Optional;

public interface VoucherRepository {
    Optional<Voucher> findByCode(String code);
}
