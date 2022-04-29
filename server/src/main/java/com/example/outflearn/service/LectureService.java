package com.example.outflearn.service;

import com.example.outflearn.model.lecture.Lecture;
import com.example.outflearn.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }
}
