package com.midas.outflearn.repository.lecture;

import com.midas.outflearn.model.lecture.Lecture;

import java.util.List;

public interface LectureRepository {
    List<Lecture> findAll();
    List<Lecture> findByName(String name);
}
