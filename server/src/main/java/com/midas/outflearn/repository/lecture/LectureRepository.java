package com.midas.outflearn.repository.lecture;

import com.midas.outflearn.model.lecture.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    List<Lecture> findAll();
    List<Lecture> findByName(String name);
    Optional<Lecture> findById(Long lectureId);
    Lecture insert(Lecture lecture);
    Lecture update(Lecture lecture);
}
