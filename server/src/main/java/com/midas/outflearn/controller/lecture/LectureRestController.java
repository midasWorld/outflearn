package com.midas.outflearn.controller.lecture;

import com.midas.outflearn.controller.ApiResponse;
import com.midas.outflearn.service.LectureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LectureRestController {

    private final LectureService lectureService;

    public LectureRestController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/api/v1/lectures")
    public ApiResponse<List<LectureDto>> lectures() {
        return ApiResponse.OK(
            lectureService.findAll().stream()
                .map(LectureDto::new)
                .collect(Collectors.toList())
        );
    }
}
