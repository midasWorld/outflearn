package com.midas.outflearn.controller.lecture;

import static com.midas.outflearn.controller.ApiResponse.*;
import static com.midas.outflearn.model.common.AttachedFile.*;
import static java.util.concurrent.CompletableFuture.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.midas.outflearn.aws.S3Client;
import com.midas.outflearn.controller.ApiResponse;
import com.midas.outflearn.model.common.AttachedFile;
import com.midas.outflearn.model.lecture.Lecture;
import com.midas.outflearn.service.lecture.LectureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LectureRestController {

	private final LectureService lectureService;
	private final S3Client s3Client;

	public LectureRestController(LectureService lectureService, S3Client s3Client) {
		this.lectureService = lectureService;
		this.s3Client = s3Client;
	}

	@GetMapping("/api/v1/lectures")
	public ApiResponse<List<LectureDto>> lectures() {
		return OK(
			lectureService.findAll().stream()
				.map(LectureDto::new)
				.collect(Collectors.toList())
		);
	}

	@GetMapping("/api/v1/lectures/{name}")
	public ApiResponse<List<LectureDto>> lecturesByName(@PathVariable String name) {
		return OK(
			lectureService.findByName(name).stream()
				.map(LectureDto::new)
				.collect(Collectors.toList())
		);
	}

	@GetMapping("/api/v1/lecture/{lectureId}")
	public ApiResponse<LectureDto> lectureById(@PathVariable Long lectureId) {
		return OK(
			new LectureDto(
				lectureService.findById(lectureId)
			)
		);
	}

	public Optional<String> uploadProfileImage(AttachedFile thumbnailFile) {
		String thumbnailImageUrl = null;
		if (thumbnailFile != null) {
			String key = thumbnailFile.randomName("thumbnail", "png");
			try {
				thumbnailImageUrl = s3Client.upload(thumbnailFile.inputStream(), thumbnailFile.length(), key,
					thumbnailFile.getContentType(), null);
			} catch (AmazonS3Exception e) {
				log.warn("Amazon S3 error (key: {}): {}", key, e.getMessage(), e);
			}
		}
		return Optional.ofNullable(thumbnailImageUrl);
	}

	@PostMapping(value = "/api/v1/lectures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<LectureDto> createLecture(
		@ModelAttribute LectureRequest request,
		@RequestPart(value = "images", required = false) MultipartFile file
	) {
		Lecture lecture = lectureService.create(request.newLecture());

		toAttachedFile(file).ifPresent(attachedFile ->
			supplyAsync(() ->
				uploadProfileImage(attachedFile)
			).thenAccept(opt ->
				opt.ifPresent(thumbnailImageUrl ->
					lectureService.updateThumbnailImage(lecture.getLectureId(), thumbnailImageUrl)
				)
			)
		);

		return OK(
			new LectureDto(lecture)
		);
	}
}
