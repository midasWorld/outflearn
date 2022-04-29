package com.example.outflearn.repository;

import com.example.outflearn.model.lecture.Lecture;

import java.util.List;

public interface LectureRepository {
    List<Lecture> findAll();
}
