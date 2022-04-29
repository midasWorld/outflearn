package com.midas.outflearn.model.lecture;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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

    public Lecture(Long lectureId, String name, String lecturer, long price, String thumbnail_image_url, LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkArgument(isNotEmpty(name), "lecture name must be provided.");
        checkArgument(isNotEmpty(lecturer), "lecturer must be provided.");
        checkArgument(price >= 0, "price must be greater than zero.");

        this.lectureId = lectureId;
        this.name = name;
        this.lecturer = lecturer;
        this.price = price;
        this.thumbnail_image_url = thumbnail_image_url;
        this.createdAt = defaultIfNull(createdAt, now());
        this.updatedAt = defaultIfNull(updatedAt, now());;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return price == lecture.price && Objects.equals(lectureId, lecture.lectureId) && Objects.equals(name, lecture.name) && Objects.equals(lecturer, lecture.lecturer) && Objects.equals(thumbnail_image_url, lecture.thumbnail_image_url) && Objects.equals(createdAt, lecture.createdAt) && Objects.equals(updatedAt, lecture.updatedAt);
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
}
