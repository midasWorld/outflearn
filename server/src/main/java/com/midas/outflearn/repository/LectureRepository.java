package com.midas.outflearn.repository;

import com.midas.outflearn.model.lecture.Lecture;

import java.util.List;

public interface LectureRepository {
    List<Lecture> findAll();
}
