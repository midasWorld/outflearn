package com.example.outflearn.controller.lecture;

import com.example.outflearn.model.lecture.Lecture;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LectureDto {
    private final Long lectureId;
    private final String name;
    private final String lecturer;
    private final long price;
    private final String thumbnail_image_url;

    public LectureDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.name = lecture.getName();
        this.lecturer = lecture.getLecturer();
        this.price = lecture.getPrice();
        this.thumbnail_image_url = lecture.getThumbnail_image_url();
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("lectureId", lectureId)
            .append("name", name)
            .append("lecturer", lecturer)
            .append("price", price)
            .append("thumbnail_image_url", thumbnail_image_url)
            .toString();
    }
}
