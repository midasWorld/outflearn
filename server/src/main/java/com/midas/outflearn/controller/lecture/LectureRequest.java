package com.midas.outflearn.controller.lecture;

import com.midas.outflearn.model.lecture.Lecture;

public class LectureRequest {
    private String name;
    private String lecturer;
    private long price;

    public LectureRequest(String name, String lecturer, long price) {
        this.name = name;
        this.lecturer = lecturer;
        this.price = price;
    }

    public Lecture newLecture() {
        return new Lecture(name, lecturer, price);
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
}
