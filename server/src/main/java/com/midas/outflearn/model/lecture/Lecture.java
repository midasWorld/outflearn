package com.midas.outflearn.model.lecture;

import static com.google.common.base.Preconditions.*;
import static java.time.LocalDateTime.*;
import static org.apache.commons.lang3.ObjectUtils.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Lecture {

	private Long lectureId;
	private String name;
	private String lecturer;
	private long price;
	private String thumbnail_image_url;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Lecture(String name, String lecturer, long price) {
		this(null, name, lecturer, price, null, null, null);
	}

	public Lecture(Long lectureId, String name, String lecturer, long price, String thumbnail_image_url,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		checkArgument(isNotEmpty(name), "lecture name must be provided.");
		checkArgument(isNotEmpty(lecturer), "lecturer must be provided.");
		checkArgument(price >= 0, "price must be greater than zero.");

		this.lectureId = lectureId;
		this.name = name;
		this.lecturer = lecturer;
		this.price = price;
		this.thumbnail_image_url = thumbnail_image_url;
		this.createdAt = defaultIfNull(createdAt, now());
		this.updatedAt = defaultIfNull(updatedAt, now());
		;
	}

	//== 비지니스 로직 ==//
	public void changeThumbNailImageURL(String thumbnail_image_url) {
		checkArgument(isNotEmpty(thumbnail_image_url), "thumbnail_image must be provided.");

		this.thumbnail_image_url = thumbnail_image_url;
	}

	public Long getLectureId() {
		return lectureId;
	}

	public String getName() {
		return name;
	}

	public String getLecturer() {
		return lecturer;
	}

	public long getPrice() {
		return price;
	}

	public String getThumbnail_image_url() {
		return thumbnail_image_url;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Lecture lecture = (Lecture)o;
		return price == lecture.price && Objects.equals(lectureId, lecture.lectureId) && Objects.equals(name,
			lecture.name) && Objects.equals(lecturer, lecture.lecturer) && Objects.equals(thumbnail_image_url,
			lecture.thumbnail_image_url) && Objects.equals(createdAt, lecture.createdAt) && Objects.equals(updatedAt,
			lecture.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lectureId, name, lecturer, price, thumbnail_image_url, createdAt, updatedAt);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("lectureId", lectureId)
			.append("name", name)
			.append("lecturer", lecturer)
			.append("price", price)
			.append("thumbnail_image", thumbnail_image_url)
			.append("createdAt", createdAt)
			.append("updatedAt", updatedAt)
			.toString();
	}

	static public class Builder {
		private Long lectureId;
		private String name;
		private String lecturer;
		private long price;
		private String thumbnail_image_url;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;

		public Builder() {
		}

		public Builder(Lecture lecture) {
			this.lectureId = lecture.lectureId;
			this.name = lecture.name;
			this.lecturer = lecture.lecturer;
			this.price = lecture.price;
			this.thumbnail_image_url = lecture.thumbnail_image_url;
			this.createdAt = lecture.createdAt;
			this.updatedAt = lecture.updatedAt;
		}

		public Builder lectureId(Long lectureId) {
			this.lectureId = lectureId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder lecturer(String lecturer) {
			this.lecturer = lecturer;
			return this;
		}

		public Builder price(long price) {
			this.price = price;
			return this;
		}

		public Builder thumbnail_image_url(String thumbnail_image_url) {
			this.thumbnail_image_url = thumbnail_image_url;
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

		public Lecture build() {
			return new Lecture(lectureId, name, lecturer, price, thumbnail_image_url, createdAt, updatedAt);
		}
	}
}
