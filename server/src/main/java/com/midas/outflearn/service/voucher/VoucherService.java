package com.midas.outflearn.service.voucher;

import org.springframework.stereotype.Service;

import com.midas.outflearn.model.voucher.Voucher;
import com.midas.outflearn.model.voucher.VoucherStatus;
import com.midas.outflearn.repository.voucher.VoucherRepository;

@Service
public class VoucherService {

	private final VoucherRepository voucherRepository;

	public VoucherService(VoucherRepository voucherRepository) {
		this.voucherRepository = voucherRepository;
	}

	public Voucher findAvailableVoucherByCode(String code) {
		Voucher voucher = voucherRepository.findByCode(code)
			.orElseThrow(() -> new IllegalArgumentException("Could not found available voucher with code=" + code));

		if (voucher.getStatus() == VoucherStatus.USED) {
			throw new IllegalArgumentException("Already been used voucher with code=" + code);
		}

		return voucher;
	}
}
