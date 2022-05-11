package com.midas.outflearn.repository.voucher;

import java.util.Optional;

import com.midas.outflearn.model.voucher.Voucher;

public interface VoucherRepository {
	Optional<Voucher> findByCode(String code);
}
