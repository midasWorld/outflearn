package com.midas.outflearn.service.lecture;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.midas.outflearn.model.lecture.Lecture;
import com.midas.outflearn.repository.lecture.LectureRepository;

@Service
public class LectureService {

	private final LectureRepository lectureRepository;

	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public List<Lecture> findAll() {
		return lectureRepository.findAll();
	}

	public List<Lecture> findByName(String name) {
		return lectureRepository.findByName(name);
	}

	public Lecture findById(Long lectureId) {
		checkArgument(lectureId != null, "lectureId must be provided.");

		return lectureRepository.findById(lectureId)
			.orElseThrow(() -> new IllegalArgumentException("Could not found lecture with lectureId=" + lectureId));
	}

	public Lecture create(Lecture lecture) {
		return lectureRepository.insert(lecture);
	}

	public Lecture updateThumbnailImage(Long lectureId, String thumbnailImageUrl) {
		checkArgument(lectureId != null, "lectureId must be provided.");
		checkArgument(isNotEmpty(thumbnailImageUrl), "thumbnailImageUrl must be provided.");

		Lecture lecture = lectureRepository.findById(lectureId)
			.orElseThrow(() -> new IllegalArgumentException("Could not found lecture with lectureId=" + lectureId));

		lecture.changeThumbNailImageURL(thumbnailImageUrl);
		return lectureRepository.update(lecture);
	}
}
