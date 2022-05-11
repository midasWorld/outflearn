package com.midas.outflearn.dto.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderJoinLectureDto {
	private final Long lectureId;
	private final String name;
	private final long price;
	private final String thumbnail_image_url;

	public OrderJoinLectureDto(Long lectureId, String name, long price, String thumbnail_image_url) {
		this.lectureId = lectureId;
		this.name = name;
		this.price = price;
		this.thumbnail_image_url = thumbnail_image_url;
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

	public String getThumbnail_image_url() {
		return thumbnail_image_url;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("lectureId", lectureId)
			.append("name", name)
			.append("price", price)
			.append("thumbnail_image_url", thumbnail_image_url)
			.toString();
	}
}
