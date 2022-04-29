package com.example.outflearn.repository;

import com.example.outflearn.model.lecture.Lecture;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.outflearn.util.DateTimeUtils.dateTimeOf;

@Repository
public class LectureJdbcRepository implements LectureRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LectureJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Lecture> findAll() {
        return jdbcTemplate.query("SELECT * FROM lectures", lectureRowMapper);
    }

    private final RowMapper<Lecture> lectureRowMapper = (resultSet, i) -> {
        long lectureId = resultSet.getLong("lecutre_id");
        String name = resultSet.getString("name");
        String lecturer = resultSet.getString("lecturer");
        long price = resultSet.getLong("price");
        String thumbnailImageUrl = resultSet.getString("thumbnail_image_url");
        LocalDateTime createdAt = dateTimeOf(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = dateTimeOf(resultSet.getTimestamp("updated_at"));
        return new Lecture(lectureId, name, lecturer, price, thumbnailImageUrl, createdAt, updatedAt);
    };
}
