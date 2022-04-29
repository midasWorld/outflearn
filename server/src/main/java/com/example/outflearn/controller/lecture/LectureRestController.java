package com.example.outflearn.controller.lecture;

import com.example.outflearn.controller.ApiResponse;
import com.example.outflearn.service.LectureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.outflearn.controller.ApiResponse.OK;

@RestController
public class LectureRestController {

    private final LectureService lectureService;

    public LectureRestController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/api/v1/lectures")
    public ApiResponse<List<LectureDto>> lectures() {
        return OK(
            lectureService.findAll().stream()
                .map(LectureDto::new)
                .collect(Collectors.toList())
        );
    }
}
