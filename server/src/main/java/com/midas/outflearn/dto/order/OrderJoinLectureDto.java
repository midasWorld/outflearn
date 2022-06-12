package com.midas.outflearn.dto.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderJoinLectureDto {
	private final Long lectureId;
	private final String name;
	private final long price;
	private final String thumbnailImageUrl;

	public OrderJoinLectureDto(Long lectureId, String name, long price, String thumbnailImageUrl) {
		this.lectureId = lectureId;
		this.name = name;
		this.price = price;
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public Long getLectureId() {
		return lectureId;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("lectureId", lectureId)
			.append("name", name)
			.append("price", price)
			.append("thumbnailImageUrl", thumbnailImageUrl)
			.toString();
	}
}
