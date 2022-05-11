package com.midas.outflearn.controller.voucher;

import static com.midas.outflearn.controller.ApiResponse.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.midas.outflearn.controller.ApiResponse;
import com.midas.outflearn.service.voucher.VoucherService;

@RestController
public class VoucherRestController {

	private final VoucherService voucherService;

	public VoucherRestController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	@GetMapping("/api/v1/vouchers/{code}")
	public ApiResponse<VoucherDto> voucherByCode(@PathVariable String code) {
		return OK(
			new VoucherDto(voucherService.findAvailableVoucherByCode(code))
		);
	}
}
