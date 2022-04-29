package com.midas.outflearn.repository;

import com.midas.outflearn.model.lecture.Lecture;
import com.midas.outflearn.util.DateTimeUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Lecture> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM lectures WHERE name LIKE :name",
            Collections.singletonMap("name", "%" + name + "%"),
            lectureRowMapper);
    }

    private final RowMapper<Lecture> lectureRowMapper = (resultSet, i) -> {
        long lectureId = resultSet.getLong("lecutre_id");
        String name = resultSet.getString("name");
        String lecturer = resultSet.getString("lecturer");
        long price = resultSet.getLong("price");
        String thumbnailImageUrl = resultSet.getString("thumbnail_image_url");
        LocalDateTime createdAt = DateTimeUtils.dateTimeOf(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = DateTimeUtils.dateTimeOf(resultSet.getTimestamp("updated_at"));
        return new Lecture(lectureId, name, lecturer, price, thumbnailImageUrl, createdAt, updatedAt);
    };
}
