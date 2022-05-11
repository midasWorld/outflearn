package com.midas.outflearn.dto.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderJoinVoucherDto {
	private final Long voucherId;
	private final String name;
	private final long percent;

	public OrderJoinVoucherDto(Long voucherId, String name, long percent) {
		this.voucherId = voucherId;
		this.name = name;
		this.percent = percent;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public String getName() {
		return name;
	}

	public long getPercent() {
		return percent;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("voucherId", voucherId)
			.append("name", name)
			.append("percent", percent)
			.toString();
	}
}
